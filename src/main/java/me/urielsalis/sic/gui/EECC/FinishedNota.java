package me.urielsalis.sic.gui.EECC;

/**
 * Created by Gustavo on 11/9/2016.
 */
public class FinishedNota {
    String name;
    int id;
    boolean isCorriente;
    float total;
    int type; //0 activo, 1 pasivo, 2 resultados

    public FinishedNota(String name, int id, boolean isCorriente, float total, int type) {
        this.name = name;
        this.id = id;
        this.isCorriente = isCorriente;
        this.total = total;
        this.type = type;
    }
}
