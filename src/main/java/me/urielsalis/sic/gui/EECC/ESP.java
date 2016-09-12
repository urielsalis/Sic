package me.urielsalis.sic.gui.EECC;

import me.urielsalis.sic.Util;

import java.util.ArrayList;

/**
 * Created by urielsalis on 11/09/16.
 */
public class ESP {
    public static void main(NotasComplementariasResult data, float patrimonioNeto) {
        ArrayList<FinishedNota> activoC = new ArrayList<>();
        ArrayList<FinishedNota> activoNC = new ArrayList<>();
        ArrayList<FinishedNota> pasivoC = new ArrayList<>();
        ArrayList<FinishedNota> pasivoNC = new ArrayList<>();

        for (FinishedNota nota : data.result) {
            if (nota.type == 0) {
                if (nota.isCorriente) activoC.add(nota);
                else activoNC.add(nota);
            } else if (nota.type == 1) {
                if (nota.isCorriente) pasivoC.add(nota);
                else pasivoNC.add(nota);
            }
        }
        Util.print("Estado de situacion patrimonial al " + EECC.fecha);
        Util.print("Activo Corriente");
        float totalAC = show(activoC);
        Util.print("  Total Activo Corriente  " + totalAC);
        Util.print("Activo No Corriente");
        float totalANC = show(activoNC);
        Util.print("  Total Activo No Corriente  " + totalANC);
        Util.print("Pasivo Corriente");
        Util.print("");
        Util.print("Total Activo " + totalAC + totalANC);
        float totalPC = show(pasivoC);
        Util.print("  Total Pasivo Corriente  " + totalPC);
        Util.print("Pasivo No Corriente");
        float totalPNC = show(pasivoNC);
        Util.print("  Total Pasivo No Corriente  " + totalPNC);
        Util.print("");
        Util.print("Total Pasivo " + totalPC + totalPNC);
        Util.print("Patrimonio neto " + patrimonioNeto);
        Util.print("Total P+PN " + totalPC + totalPNC + patrimonioNeto);

    }

    private static float show(ArrayList<FinishedNota> activoC) {
        float total = 0;
        for (FinishedNota nota : activoC) {
            Util.print(nota.total < 0 ? nota.name + " (Nota " + nota.id + ")  (" + -nota.total + ")" : nota.name + " (Nota " + nota.id + ")  " + nota.total);
            total += nota.total;
        }
        return total;
    }
}
