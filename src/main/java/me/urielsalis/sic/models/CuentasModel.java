package me.urielsalis.sic.models;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Created by urielsalis on 07/09/16.
 */
public class CuentasModel implements TableModel {
    @Override
    public int getRowCount() {
        return 0;
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
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {

    }

    @Override
    public void addTableModelListener(TableModelListener tableModelListener) {

    }

    @Override
    public void removeTableModelListener(TableModelListener tableModelListener) {

    }
}
