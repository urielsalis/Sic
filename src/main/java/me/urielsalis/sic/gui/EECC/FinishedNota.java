package me.urielsalis.sic.gui.EECC;

/**
 * Created by Gustavo on 11/9/2016.
 */
public class FinishedNota {
    String name;
    int id;
    boolean isCorriente;
    float total;

    public FinishedNota(String name, int id, boolean isCorriente, float total) {
        this.name = name;
        this.id = id;
        this.isCorriente = isCorriente;
        this.total = total;
    }
}
