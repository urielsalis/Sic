package me.urielsalis.sic;

import static me.urielsalis.sic.Main.cuentas;

/**
 * Created by urielsalis on 07/09/16.
 */
public class CuentasLoader {
    private static boolean[] reverse = {false, false, false, false};
    private static Cuenta[] topCuentas = {null, null, null, null};
    private static int lastLevel = 0;

    public static void parseCuenta(String line) {
        line = line.trim();
        String code = line.substring(0,line.indexOf(' '));
        String name = line.substring(line.indexOf(' ')+1).trim();
        int similarTo = 0;
        int level = 0;
        if(name.contains("|")) {
            String t = name.split("|")[0];
            level = t.length()-t.replace(".", "").length();
            if(t.endsWith("0")) level--;
        } else {
            level = name.length()-name.replace(".", "").length();
            if(name.endsWith("0")) level--;
        }
        if(name.contains("|")) {
            String[] temp = name.split("|");
            name = temp[0];
            String temp2 = temp[1];
            if(temp2.equals("-")) {
                reverse[level] = !reverse[level];
                for (int i = level; i < 3; i++) {
                    reverse[i] = !reverse[i];
                }
            }
            else if(temp2.contains(".")) similarTo = getCodeFromString(temp2);
        }

        Cuenta cuenta = new Cuenta(getCodeFromString(code), name, Integer.valueOf(code.split(".")[code.split(".").length-1]), getReverse(level), similarTo);
        if(level==lastLevel) reverse[lastLevel] = false;
        if(level < lastLevel) {
            switch (lastLevel) {
                case 3:
                    if (level == 2) {
                        reverse[2] = false;
                    } else if (level == 1) {
                        reverse[2] = false;
                        reverse[1] = false;
                    } else if (level == 0) {
                        reverse[2] = false;
                        reverse[1] = false;
                        reverse[0] = false;
                    }
                    break;
                case 2:
                    if(level==1) {
                        reverse[3] = false;
                        reverse[2] = false;
                        reverse[1] = false;
                    } else if(level==0) {
                        reverse[3] = false;
                        reverse[2] = false;
                        reverse[1] = false;
                        reverse[0] = false;
                    }
                    break;
                case 1:
                    if(level==0) {
                        reverse[3] = false;
                        reverse[2] = false;
                        reverse[1] = false;
                        reverse[0] = false;
                    }
            }
        }
        if(level==0) {
            if (topCuentas[0] != null) {
                cuentas.add(topCuentas[0]);
            }
            if(topCuentas[1] != null) {
                cuentas.add((topCuentas[1]));
            }
            if(topCuentas[2] != null) {
                cuentas.add((topCuentas[2]));
            }
            if(topCuentas[3] != null) {
                cuentas.add((topCuentas[3]));
            }
            topCuentas[0] = cuenta;
            topCuentas[1] = cuenta;
            topCuentas[2] = cuenta;
            topCuentas[3] = cuenta;
        } else {
            switch (level) {
                case 3:
                    if(lastLevel==3) {
                        cuentas.add(topCuentas[3]);
                        topCuentas[3] = null;
                    }
                    topCuentas[3] = cuenta;
                    break;
                case 2:
                    if(lastLevel==3) {
                        cuentas.add(topCuentas[3]);
                        cuentas.add(topCuentas[2]);
                        topCuentas[3] = null;
                        topCuentas[2] = null;
                    } else if(lastLevel==2) {
                        cuentas.add(topCuentas[2]);
                        topCuentas[2] = null;
                    }
                    topCuentas[2] = cuenta;

                    break;
                case 1:
                    if(lastLevel==3) {
                        cuentas.add(topCuentas[3]);
                        cuentas.add(topCuentas[2]);
                        cuentas.add(topCuentas[1]);
                        topCuentas[3] = null;
                        topCuentas[2] = null;
                        topCuentas[1] = null;
                    } else if(lastLevel==2) {
                        cuentas.add(topCuentas[2]);
                        cuentas.add(topCuentas[1]);
                        topCuentas[2] = null;
                        topCuentas[1] = null;
                    } else if(lastLevel==1) {
                        cuentas.add(topCuentas[1]);
                        topCuentas[1] = null;
                    }
                    topCuentas[1] = cuenta;
                    break;
            }
        }
        lastLevel = level;
    }

    private static boolean getReverse(int level) {
        boolean t = false;
        switch (level) {
            case 3:
                if(reverse[3]) t = true;
            case 2:
                if(reverse[2]) t = !t;
            case 1:
                if(reverse[1]) t = !t;
            case 0:
                if(reverse[0]) t = !t;
        }
        return t;
    }

    private static int getCodeFromString(String code) {
        if(code.endsWith("0")) {
            code = code.substring(0, code.length()-2);
        }
        return Integer.valueOf(code.replace(".", ""));
    }
}
