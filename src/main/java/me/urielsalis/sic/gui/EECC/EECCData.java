package me.urielsalis.sic.gui.EECC;

import me.urielsalis.sic.Cuenta;

/**
 * Created by Gustavo on 11/9/2016.
 */
public class EECCData {
    public Cuenta cuenta;
    public int saldoCorriente;
    public int saldoNoCorriente;

    public EECCData(Cuenta cuenta, int saldoCorriente, int saldoNoCorriente) {
        this.cuenta = cuenta;
        this.saldoCorriente = saldoCorriente;
        this.saldoNoCorriente = saldoNoCorriente;
    }
}
