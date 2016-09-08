package me.urielsalis.sic;

import java.util.ArrayList;

/**
 * Created by urielsalis on 07/09/16.
 */
public class Cuenta {
    private boolean reverse = false;
    private String name;
    private int subcode;
    private int fullcode;
    public ArrayList<Cuenta> cuentas = new ArrayList<>();
    private int similarTo = 0;

    public Cuenta(boolean reverse, String name, int subcode, int fullcode, int similarTo) {
        this.reverse = reverse;
        this.name = name;
        this.subcode = subcode;
        this.fullcode = fullcode;
        this.similarTo = similarTo;
    }

    public Cuenta(boolean reverse, String name, int subcode, int fullcode, ArrayList<Cuenta> cuentas, int similarTo) {

        this.reverse = reverse;
        this.name = name;
        this.subcode = subcode;
        this.fullcode = fullcode;
        this.cuentas = cuentas;
        this.similarTo = similarTo;

    }

    public Cuenta(String estado) {
        this.name = estado;
        //invalid cuenta!!
    }

    public int getSimilarTo() {
        return similarTo;
    }

    public void setSimilarTo(int similarTo) {
        this.similarTo = similarTo;
    }

    public boolean isReverse() {

        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubcode() {
        return subcode;
    }

    public void setSubcode(int subcode) {
        this.subcode = subcode;
    }

    public int getFullcode() {
        return fullcode;
    }

    public void setFullcode(int fullcode) {
        this.fullcode = fullcode;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void addCuenta(Cuenta rubroActual) {
        cuentas.add(rubroActual);
    }
}
