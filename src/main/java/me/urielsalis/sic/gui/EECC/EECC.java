package me.urielsalis.sic.gui.EECC;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestWord;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import me.urielsalis.sic.Cuenta;
import me.urielsalis.sic.Main;
import me.urielsalis.sic.Util;

import java.util.ArrayList;

/**
 * Created by urielsalis on 08/09/16.
 */
public class EECC {
    public static String fecha = "../../..";
    public static void main() {
        ArrayList<EECCData> data = new ArrayList<>();
        System.out.print("Fecha: ");
        fecha = Main.teclado.nextLine();
        try {
            while (true) {
                System.out.print("Codigo ");
                String line = Main.teclado.nextLine();
                if (line.equals("0")) {
                    break;
                }
                Cuenta cuenta = (Cuenta) Util.getCuenta(line).clone();
                if (cuenta.getName().contains("xx")) {
                    System.out.print("xx: ");
                    String fullName = Main.teclado.nextLine();
                    cuenta.setName(cuenta.getName().replace("xx", fullName));
                }
                System.out.print("Saldo corriente ");
                int saldoCorriente = (int) (Float.parseFloat(Main.teclado.nextLine()) * 100);
                System.out.print("Saldo no corriente ");
                int saldoNoCorriente = (int) (Float.parseFloat(Main.teclado.nextLine()) * 100);
                data.add(new EECCData(cuenta, saldoCorriente, saldoNoCorriente));
            }
            V2_AsciiTable at = new V2_AsciiTable();
            at.addRule();
            at.addRow("Codigo", "Nombre", "Saldo Corriente", "Saldo No Corriente", "Rubro");
            at.addRule();
            for (EECCData eeccData : data) {
                if (eeccData.cuenta != null) {
                    at.addRow(Util.stringFromCode(eeccData.cuenta.getFullcode()), eeccData.cuenta.getName(), String.valueOf(((float) eeccData.saldoCorriente) / 100), String.valueOf(((float) eeccData.saldoNoCorriente) / 100), Util.getRubro(eeccData.cuenta));
                    at.addRule();
                }
            }
            V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
            rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
            rend.setWidth(new WidthLongestWord());
            RenderedTable rt = rend.render(at);
            Util.print(rt.toString());
            NotasComplementariasResult notas = NotasComplementarias.main(data);
            float rdoEjercicio = ER.main(notas);
            //TODO EEPN
            //TODO ESP
            Util.closePrint();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

