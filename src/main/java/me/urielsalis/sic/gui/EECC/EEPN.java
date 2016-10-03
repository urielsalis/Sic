package me.urielsalis.sic.gui.EECC;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestWord;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import me.urielsalis.sic.Main;
import me.urielsalis.sic.Util;

/**
 * Created by urielsalis on 11/09/16.
 */
public class EEPN {
    public static float main(float rdoEjercicio) {
        System.out.print("Capital: ");
        float capital = Float.parseFloat(Main.teclado.nextLine());
        System.out.print("Prima de emision: ");
        float prima = Float.parseFloat(Main.teclado.nextLine());
        System.out.print("Reserva legal: ");
        float reservaLegal = Float.parseFloat(Main.teclado.nextLine());
        System.out.print("Reserva facultativa: ");
        float reservaFacultativa = Float.parseFloat(Main.teclado.nextLine());
        System.out.print("RNA anterior: ");
        float rna = Float.parseFloat(Main.teclado.nextLine());
        V2_AsciiTable at = new V2_AsciiTable();
        at.addStrongRule();
        at.addRow(null, null, null, null, null, null, null, "Estado de evolucion del patrimonio neto al " + EECC.fecha);
        at.addStrongRule();
        at.addRow("Rubro", "Capital", "Prima de emision", "Total", "Reserva legal", "Reserva facultativa", "RNA", "Total");
        at.addRule();
        at.addRow("Saldo inicial", capital, prima, capital + prima, reservaLegal, reservaFacultativa, rna, capital + prima + reservaLegal + reservaFacultativa + rna);
        at.addRule();
        at.addRow("Res ej", "-", "-", "-", 0, 0, rdoEjercicio, rdoEjercicio);
        at.addRule();
        at.addRow("Saldo final", capital, prima, capital + prima, reservaLegal, reservaFacultativa, rdoEjercicio + rna, capital + prima + rdoEjercicio + reservaLegal + reservaFacultativa + rna);
        at.addStrongRule();
        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
        rend.setWidth(new WidthLongestWord());
        RenderedTable rt = rend.render(at);
        Util.print(rt.toString());
        return capital + prima + rdoEjercicio;

    }
}
