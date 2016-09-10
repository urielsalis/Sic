package me.urielsalis.sic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by urielsalis on 08/09/16.
 */
public class Util {
    static PrintWriter writer = null;


    public static Cuenta getTop(Cuenta cuenta) {
        if (cuenta.getFullcode() < 10) return new Cuenta("Estado");
        int code = (cuenta.getFullcode() - cuenta.getSubcode()) / 10;
        return getCuenta(code);
    }

    public static Cuenta getCuenta(int code) {
        try {
            if (code > 100) {
                int estado = Integer.parseInt(String.valueOf(code).substring(0, 1)) - 1;
                int rubro = Integer.parseInt(String.valueOf(code).substring(1, 2));
                int cuenta = Integer.parseInt(String.valueOf(code).substring(2)) - 1;
                return Main.cuentas.get(estado).cuentas.get(rubro).cuentas.get(cuenta);
            } else if (code > 10) {
                int estado = Integer.parseInt(String.valueOf(code).substring(0, 1)) - 1;
                int rubro = Integer.parseInt(String.valueOf(code).substring(1, 2));
                return Main.cuentas.get(estado).cuentas.get(rubro);
            } else {
                return Main.cuentas.get(code - 1);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Cuenta getCuenta(String line) {
        int code = CuentasLoader.getCodeFromString(line);
        return getCuenta(code);
    }

    public static void print(String str) {
        if (writer == null) {
            try {
                writer = new PrintWriter("result.txt", "UTF-8");
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        writer.println(str);
        System.out.println(str);
    }

    public static String getRubro(Cuenta cuenta) {
        return getTop(cuenta).getName();
    }

    public static String stringFromCode(int code) {
        if (code > 100) {
            int estado = Integer.parseInt(String.valueOf(code).substring(0, 1));
            int rubro = Integer.parseInt(String.valueOf(code).substring(1, 2));
            int cuenta = Integer.parseInt(String.valueOf(code).substring(2));
            return estado + "." + rubro + "." + cuenta;
        } else if (code > 10) {
            int estado = Integer.parseInt(String.valueOf(code).substring(0, 1));
            int rubro = Integer.parseInt(String.valueOf(code).substring(1, 2));
            return estado + "." + rubro;
        } else {
            return String.valueOf(code);
        }
    }

    public static void closePrint() {
        writer.close();
    }
}
