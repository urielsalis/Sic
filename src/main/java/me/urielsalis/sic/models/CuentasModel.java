package me.urielsalis.sic.models;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by urielsalis on 07/09/16.
 */
public class CuentasModel implements TableModel {
    ArrayList<String> codigos = new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> corriente = new ArrayList<>();
    ArrayList<String> nocorriente = new ArrayList<>();
    ArrayList<String> saldo = new ArrayList<>();
    ArrayList<String> clasificacion = new ArrayList<>();
    int numRows = 0;

    public void addRow() {
        codigos.add("");
        name.add("");
        corriente.add("");
        nocorriente.add("");
        saldo.add("");
        clasificacion.add("");

    }

    @Override
    public int getRowCount() {
        return numRows;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int i) {
        switch (i) {
            case 0:
                return "Codigo";
            case 1:
                return "Name";
            case 2:
                return "Corriente";
            case 3:
                return "No Corriente";
            case 4:
                return "Saldo";
            case 5:
                return "Clasificacion";
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return (i==0 || i==2 || i==3 || i==4);
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i) {
            case 0:
                return codigos.get(i1);
            case 1:
                return name.get(i1);
            case 2:
                return corriente.get(i1);
            case 3:
                return nocorriente.get(i1);
            case 4:
                return saldo.get(i1);
            case 5:
                return clasificacion.get(i1);
            default:
                return "";
        }
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        switch (i) {
            case 0:
                codigos.set(i1, String.valueOf(o));
                break;
            case 1:
                name.set(i1, String.valueOf(o));
                break;
            case 2:
                corriente.set(i1, String.valueOf(o));
                break;
            case 3:
                nocorriente.set(i1, String.valueOf(o));
                break;
            case 4:
                saldo.set(i1, String.valueOf(o));
                break;
            case 5:
                clasificacion.set(i1, String.valueOf(o));
                break;
        }
    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }
}
