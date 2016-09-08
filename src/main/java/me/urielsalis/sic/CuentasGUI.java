package me.urielsalis.sic;

import me.urielsalis.sic.models.CuentasModel;

import javax.swing.*;

/**
 * Created by urielsalis on 07/09/16.
 */
public class CuentasGUI {
    private JPanel panel1;
    private JTable table1;

    public static void main() {
        JFrame frame = new JFrame("CuentasGUI");
        frame.setContentPane(new CuentasGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 = new JTable(new CuentasModel());
    }
}
