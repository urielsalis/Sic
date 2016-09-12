package me.urielsalis.sic.gui.EECC;

import de.vandermeer.asciitable.v2.RenderedTable;
import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestWord;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import me.urielsalis.sic.Util;

/**
 * Created by urielsalis on 11/9/2016.
 */
public class ER {
    public static float main(NotasComplementariasResult data) {
        FinishedNota ventas = getVentas(data);
        if (ventas == null) {
            ventas = new FinishedNota("Ventas netas", 0, true, 0, 2);
        }
        float resultadoBruto = ventas.total - data.costoDeVentas;

        V2_AsciiTable at = new V2_AsciiTable();
        at.addRule();
        at.addRow("Estado de resultados al", EECC.fecha);
        at.addStrongRule();
        at.addRow("Ventas netas de bienes y servicios (Nota " + ventas.id + ")", ventas.total);
        at.addRow("Costo de los bienes vendidos y servicios prestados", "(" + data.costoDeVentas + ")");
        at.addRule();
        if (resultadoBruto > 0)
            at.addRow("    Resultado bruto", resultadoBruto);
        else
            at.addRow("    Resultado bruto", "(" + resultadoBruto + ")");

        float total = resultadoBruto;
        for (FinishedNota nota : data.result) {
            if (nota.name.equals("Ventas netas")) continue;
            if (nota.type == 2) {
                if (nota.total < 0)
                    at.addRow(nota.name + " (Nota " + nota.id + ")", "(" + nota.total + ")");
                else
                    at.addRow(nota.name + " (Nota " + nota.id + ")", nota.total);
                total += nota.total;
            }
        }
        at.addRule();
        at.addRow("Resultado del ejercico", total);
        at.addRule();
        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.UTF_LIGHT.get());
        rend.setWidth(new WidthLongestWord());
        RenderedTable rt = rend.render(at);
        Util.print(rt.toString());
        return total;
    }

    private static FinishedNota getVentas(NotasComplementariasResult data) {
        for (FinishedNota nota : data.result) {
            if (nota.name.equals("Ventas netas"))
                return nota;
        }
        return null;
    }
}
