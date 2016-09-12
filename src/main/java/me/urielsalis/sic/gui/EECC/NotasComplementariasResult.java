package me.urielsalis.sic.gui.EECC;

import java.util.ArrayList;

/**
 * Created by urielsalis on 11/09/16.
 */
public class NotasComplementariasResult {
    ArrayList<FinishedNota> result;
    float costoDeVentas = 0;

    public NotasComplementariasResult(ArrayList<FinishedNota> result, float costoDeVentas) {
        this.result = result;
        this.costoDeVentas = costoDeVentas;
    }

    public NotasComplementariasResult(ArrayList<FinishedNota> result) {
        this.result = result;
    }
}
