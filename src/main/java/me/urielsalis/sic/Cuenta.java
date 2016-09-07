package me.urielsalis.sic;

import java.util.ArrayList;

/**
 * Created by urielsalis on 06/09/16.
 */
public class Cuenta {
    private int fullcode;
    private String name;
    private int subcode;
    private boolean substract;
    private int similarTo;
    private ArrayList<Cuenta> subCuentas;

    public Cuenta(int fullcode, String name, int subcode, boolean substract, int similarTo) {
        this.fullcode = fullcode;
        this.name = name;
        this.subcode = subcode;
        this.substract = substract;
        this.similarTo = similarTo;
        this.subCuentas = new ArrayList<Cuenta>();
    }

    public Cuenta(int fullcode, String name, int subcode, boolean substract, int similarTo, ArrayList<Cuenta> subCuentas) {
        this.fullcode = fullcode;
        this.name = name;
        this.subcode = subcode;
        this.substract = substract;
        this.similarTo = similarTo;
        this.subCuentas = subCuentas;
    }

    public ArrayList<Cuenta> getSubCuentas() {
        return subCuentas;
    }

    public void setSubCuentas(ArrayList<Cuenta> subCuentas) {
        this.subCuentas = subCuentas;
    }

    public void addSubCuenta(Cuenta cuenta) {
        subCuentas.add(cuenta);
    }

    public Cuenta getSubCuenta(int subcode) {
        for(Cuenta cuenta: subCuentas) {
            if(cuenta.getSubcode()==subcode) return cuenta;
        }
        return null;
    }

    public int getFullcode() {

        return fullcode;
    }

    public void setFullcode(int fullcode) {
        this.fullcode = fullcode;
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

    public boolean isSubstract() {
        return substract;
    }

    public void setSubstract(boolean substract) {
        this.substract = substract;
    }

    public int getSimilarTo() {
        return similarTo;
    }

    public void setSimilarTo(int similarTo) {
        this.similarTo = similarTo;
    }
}
