package me.urielsalis.sic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by urielsalis on 05/09/16.
 */
public class Main {
    public static ArrayList<Cuenta> cuentas = new ArrayList<>();

    public static void main(String[] args) {
        loadCuentas();
        System.out.println("Cuentas cargadas");
        //CuentasGUI.main();
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
