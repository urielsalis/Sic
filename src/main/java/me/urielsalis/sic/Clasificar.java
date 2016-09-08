package me.urielsalis.sic;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.concurrent.Exchanger;

/**
 * Created by urielsalis on 08/09/16.
 */
public class Clasificar {
    public static void main() {
        while(true) {
            Scanner teclado = new Scanner(System.in);
            System.out.print("Cuenta: ");
            String line = teclado.nextLine();
            if(getCuenta(line) != null) {
                Cuenta cuenta = getCuenta(line);
                System.out.println("Nombre: " + cuenta.getName() + " Clasificacion: " + getTop(cuenta).getName());
            } else {
                System.out.println("Not found");
            }
        }
    }

    private static Cuenta getTop(Cuenta cuenta) {
        if(cuenta.getFullcode()<10) return new Cuenta("Estado");
        int code = (cuenta.getFullcode()-cuenta.getSubcode())/10;
        return getCuenta(code);
    }

    private static Cuenta getCuenta(int code) {
        try {
            if(code > 100) {
                int estado = Integer.parseInt(String.valueOf(code).substring(0, 1))-1;
                int rubro = Integer.parseInt(String.valueOf(code).substring(1, 2));
                int cuenta = Integer.parseInt(String.valueOf(code).substring(2))-1;
                return Main.cuentas.get(estado).cuentas.get(rubro).cuentas.get(cuenta);
            } else if(code > 10) {
                int estado = Integer.parseInt(String.valueOf(code).substring(0, 1))-1;
                int rubro = Integer.parseInt(String.valueOf(code).substring(1, 2));
                return Main.cuentas.get(estado).cuentas.get(rubro);
            } else {
                return Main.cuentas.get(code-1);
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static Cuenta getCuenta(String line) {
        int code = CuentasLoader.getCodeFromString(line);
        return getCuenta(code);
    }
}
