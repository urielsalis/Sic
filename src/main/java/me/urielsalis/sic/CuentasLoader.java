package me.urielsalis.sic;

import java.text.ParseException;

import static me.urielsalis.sic.CuentasLoader.Nivel.*;

/**
 * Created by urielsalis on 07/09/16.
 */
public class CuentasLoader {
    private static Cuenta estadoActual = null;
    private static Cuenta rubroActual = null;
    private static Cuenta cuentaActual = null;
    private static Nivel lastLevel = NONE;

    public static void parseCuenta(String line) {
        try {
            line = line.trim();

            String code = line.substring(0, line.indexOf(' '));
            String name = line.substring(line.indexOf(' ') + 1).trim();
            int t = code.length() - code.replace(".", "").length();
            int subcode;
            String[] temps = code.split("\\.");
            if (Integer.parseInt(temps[temps.length-1]) == 0) {
                t--;
                subcode = Integer.parseInt(temps[temps.length-2]);
            } else {
                subcode = Integer.parseInt(temps[temps.length-1]);
            }
            Nivel level = toEnum(t);
            boolean reverse = false;
            int similarTo = 0;
            if (name.contains("|")) {
                String[] temp = name.split("\\|");
                name = temp[0];
                reverse = temp[1].contains("-");
                if (temp[1].contains(".")) {
                    similarTo = getCodeFromString(temp[1]);
                }
            }
            Cuenta cuenta = new Cuenta(getReverse(level, reverse), name, subcode, getCodeFromString(code), similarTo);
            if (lastLevel == NONE) {
                estadoActual = cuenta;
            } else {
                switch (lastLevel) {
                    case ESTADO:
                        if (level != null) {
                            switch (level) {
                                case ESTADO:
                                    Main.cuentas.add(estadoActual);
                                    estadoActual = cuenta;
                                    break;
                                case RUBRO:
                                    estadoActual.addCuenta(rubroActual);
                                    rubroActual = cuenta;
                                    break;
                                case CUENTA:
                                    throw new ParseException("Cuenta debajo de estado", 0);
                            }
                        }
                        break;
                    case RUBRO:
                        if (level != null) {
                            switch (level) {
                                case ESTADO:
                                    throw new ParseException("Rubro vacio", 0);
                                case RUBRO:
                                    estadoActual.addCuenta(rubroActual);
                                    rubroActual = cuenta;
                                    break;
                                case CUENTA:
                                    cuentaActual = cuenta;
                            }
                        }
                        break;
                    case CUENTA:
                        if (level != null) {
                            switch (level) {
                                case ESTADO:
                                    Main.cuentas.add(estadoActual);
                                    estadoActual = cuenta;
                                    rubroActual = null;
                                    cuentaActual = null;
                                    break;
                                case RUBRO:
                                    estadoActual.addCuenta(rubroActual);
                                    rubroActual = cuenta;
                                    cuentaActual = null;
                                    break;
                                case CUENTA:
                                    rubroActual.addCuenta(cuentaActual);
                                    cuentaActual = cuenta;
                                    break;
                            }
                        }
                        break;
                }
            }
            lastLevel = level;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void finishLoadCuentas() {
        Main.cuentas.add(estadoActual);
        estadoActual = null;
        rubroActual = null;
        cuentaActual = null;
        lastLevel = NONE;
    }

    private static boolean getReverse(Nivel level, boolean reverse) {
        switch (level) {
            case ESTADO:
                if(estadoActual!=null && estadoActual.isReverse()) reverse = !reverse;
            case RUBRO:
                if(rubroActual!=null && rubroActual.isReverse()) reverse = !reverse;
            case CUENTA:
                if(cuentaActual!=null && cuentaActual.isReverse()) reverse = !reverse;
        }
        return reverse;
    }

    private static Nivel toEnum(int level) {
        switch (level) {
            case 0: return ESTADO;
            case 1: return RUBRO;
            case 2: return CUENTA;
        }
        return null;
    }

    public static int getCodeFromString(String code) {
        if(code.endsWith("0")) {
            code = code.substring(0, code.length()-2);
        }
        return Integer.valueOf(code.replace(".", ""));
    }


    static enum Nivel {ESTADO, RUBRO, CUENTA, NONE}
}
