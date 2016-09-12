package me.urielsalis.sic.gui.EECC;

import java.util.ArrayList;

/**
 * Created by Gustavo on 11/9/2016.
 */
public class Nota {
    public boolean reverse = false;
    public boolean isCorriente;
    public int id;
    public String name;
    public ArrayList<EECCData> data = new ArrayList<>();
    int type;

    public Nota(String name, boolean reverse, boolean isCorriente, int type) {
        this.name = name;
        this.id = -1;
        this.reverse = reverse;
        this.isCorriente = isCorriente;
        this.type = type;
    }

    public void addCuenta(EECCData a) {
        data.add(a);
        if(a.cuenta.isReverse()) reverse = !reverse;
    }
}
