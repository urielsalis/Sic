package me.urielsalis.sic.gui;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthAbsoluteEven;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import me.urielsalis.sic.Cuenta;
import me.urielsalis.sic.Main;
import me.urielsalis.sic.Util;

import java.util.ArrayList;

/**
 * Created by urielsalis on 08/09/16.
 */
public class EECC {
    public static void main() {
        ArrayList<EECCData> data = new ArrayList<EECCData>();
        while (true) {
            System.out.print("Codigo ");
            String line = Main.teclado.nextLine();
            if (line.equals("0")) {
                break;
            }
            Cuenta cuenta = Util.getCuenta(line);
            System.out.print("Saldo corriente ");
            int saldoCorriente = Integer.parseInt(Main.teclado.nextLine()) * 100;
            System.out.print("Saldo no corriente ");
            int saldoNoCorriente = Integer.parseInt(Main.teclado.nextLine()) * 100;
            data.add(new EECCData(cuenta, saldoCorriente, saldoNoCorriente));
        }
        V2_AsciiTable at = new V2_AsciiTable();
        at.addRule();
        at.addRow("Codigo", "Nombre", "Saldo Corriente", "Saldo No Corriente", "Rubro");
        for (EECCData eeccData : data) {
            if (eeccData.cuenta != null) {
                at.addRule();
                at.addRow(Util.stringFromCode(eeccData.cuenta.getFullcode()), eeccData.cuenta.getName(), eeccData.saldoCorriente / 100, eeccData.saldoNoCorriente / 100, Util.getRubro(eeccData.cuenta));
            }
        }
        at.addRule();
        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
        rend.setWidth(new WidthAbsoluteEven(76));
        RenderedTable rt = rend.render(at);
        Util.print(rt.toString());
        //TODO Notas complementarias
        NotasComplementarias.main(data);
        //TODO ESP
        //TODO EEPN
        //TODO ER
        Util.closePrint();
    }
}

class EECCData {
    public Cuenta cuenta;
    public int saldoCorriente;
    public int saldoNoCorriente;

    public EECCData(Cuenta cuenta, int saldoCorriente, int saldoNoCorriente) {
        this.cuenta = cuenta;
        this.saldoCorriente = saldoCorriente;
        this.saldoNoCorriente = saldoNoCorriente;
    }
}

class Nota {
    private static int counter = 1;
    public boolean reverse = false;
    public int id;
    public String name;
    public ArrayList<EECCData> data = new ArrayList<>();
    private int reverseThre = 0;

    public Nota(String name) {
        this.name = name;
        this.id = counter;
        counter++;
    }

    public void addCuenta(EECCData a) {
        data.add(a);
        if (!reverse) {
            if (a.cuenta.isReverse()) reverseThre++;
            if (reverseThre > 3) reverse = true;
        }
    }
}

class NotasComplementarias {
    public static void main(ArrayList<EECCData> data) {
        /*ArrayList<Nota> notas = new ArrayList<Nota>(); TODO CHANGE IT SO ONLY RUBROS IN ORDER WITH ACCOUNTS ARE ADDED
        Cuenta activo = Main.cuentas.get(0);
        Cuenta pasivo = Main.cuentas.get(1);
        Cuenta resultados = Main.cuentas.get(3);
        for (Cuenta cuenta : activo.cuentas) for (Cuenta rubro : cuenta.cuentas) notas.add(new Nota(rubro.getName()));
        for (Cuenta cuenta : pasivo.cuentas) for (Cuenta rubro : cuenta.cuentas) notas.add(new Nota(rubro.getName()));
        for (Cuenta cuenta : resultados.cuentas) for (Cuenta rubro : cuenta.cuentas) notas.add(new Nota(rubro.getName()));
        *//*for(EECCData eeccData: data) {
            if(eeccData.cuenta==null) continue;
            for(Nota rubro: notas) {
                if(rubro.name.equals(Util.getRubro(eeccData.cuenta)))
                    rubro.addCuenta(eeccData);
            }
        }
        for(Nota nota: notas) {
            System.out.println("Nota " + nota.id + ": "+nota.name);
            for(EECCData eeccData: nota.data) {
                boolean reverse = false;
                if(!nota.reverse) {
                    if(eeccData.cuenta.isReverse()) reverse = true;
                }
                if(reverse) {
                    System.out.println("  " + eeccData.cuenta.getName() + "  " + eeccData.saldoCorriente);
                } else {

                }
            }
        }*/

    }

}
