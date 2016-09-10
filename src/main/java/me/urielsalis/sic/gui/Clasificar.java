package me.urielsalis.sic.gui;

import me.urielsalis.sic.Cuenta;
import me.urielsalis.sic.Main;

import static me.urielsalis.sic.Util.getCuenta;
import static me.urielsalis.sic.Util.getTop;

/**
 * Created by urielsalis on 08/09/16.
 */
public class Clasificar {
    public static void main() {
        while(true) {
            System.out.print("Cuenta: ");
            String line = Main.teclado.nextLine();
            if(getCuenta(line) != null) {
                Cuenta cuenta = getCuenta(line);
                System.out.println("Nombre: " + cuenta.getName() + " Clasificacion: " + getTop(cuenta).getName());
            } else {
                System.out.println("Not found");
            }
        }
    }
}
