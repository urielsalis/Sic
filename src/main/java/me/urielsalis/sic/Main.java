package me.urielsalis.sic;

import me.urielsalis.sic.gui.Clasificar;
import me.urielsalis.sic.gui.EECC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by urielsalis on 05/09/16.
 */
public class Main {
    public static ArrayList<Cuenta> cuentas = new ArrayList<>();
    public static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        loadCuentas();
        System.out.println("Cuentas cargadas");
        System.out.println("1) Clasificar");
        System.out.println("2) Crear EECC");
        int value = Integer.parseInt(teclado.nextLine());
        if(value==1)
            Clasificar.main();
        else if(value==2) {
            //TODO EECC
            EECC.main();
        }
    }

    private static void loadCuentas() {
        try (Stream<String> stream = Files.lines(Paths.get("Cuentas"))) {
            stream.forEach(CuentasLoader::parseCuenta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CuentasLoader.finishLoadCuentas();
    }


}
