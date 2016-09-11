package me.urielsalis.sic.gui.EECC;

import me.urielsalis.sic.Cuenta;
import me.urielsalis.sic.Main;
import me.urielsalis.sic.Util;

import java.util.ArrayList;

/**
 * Created by Gustavo on 11/9/2016.
 */
public class NotasComplementarias {
    public static ArrayList<FinishedNota> main(ArrayList<EECCData> data) {
        ArrayList<Nota> tempCorriente = new ArrayList<Nota>();
        ArrayList<Nota> tempNoCorriente = new ArrayList<Nota>();
        ArrayList<Nota> notas = new ArrayList<Nota>();
        ArrayList<FinishedNota> output = new ArrayList<>();
        Cuenta activo = Main.cuentas.get(0);
        Cuenta pasivo = Main.cuentas.get(1);
        Cuenta resultados = Main.cuentas.get(3);

        for (Cuenta rubro : activo.cuentas) {
            if(rubro==null) continue;
            tempCorriente.add(new Nota(rubro.getName(), rubro.isReverse(), true));
        }
        for (Cuenta rubro : pasivo.cuentas) {
            if(rubro==null) continue;
            tempCorriente.add(new Nota(rubro.getName(), rubro.isReverse(), true));
        }
        for (Cuenta rubro : resultados.cuentas) {
            if(rubro==null) continue;
            tempCorriente.add(new Nota(rubro.getName(), rubro.isReverse(), true));
        }
        for (Cuenta rubro : activo.cuentas) {
            if(rubro==null) continue;
            tempNoCorriente.add(new Nota(rubro.getName(), rubro.isReverse(), false));
        }
        for (Cuenta rubro : pasivo.cuentas) {
            if(rubro==null) continue;
            tempNoCorriente.add(new Nota(rubro.getName(), rubro.isReverse(), false));
        }
        for (Cuenta rubro : resultados.cuentas) {
            if(rubro==null) continue;
            tempNoCorriente.add(new Nota(rubro.getName(), rubro.isReverse(), false));
        }

        for(EECCData eeccData: data) {
            if(eeccData.cuenta==null) continue;
            if(eeccData.saldoCorriente > 0) {
                for (Nota rubro : tempCorriente) {
                    if (rubro == null) continue;
                    if (rubro.name.equals(Util.getRubro(eeccData.cuenta)))
                        rubro.addCuenta(eeccData);
                }
            }
            if(eeccData.saldoNoCorriente > 0) {
                for (Nota rubro : tempNoCorriente) {
                    if (rubro == null) continue;
                    if (rubro.name.equals(Util.getRubro(eeccData.cuenta)))
                        rubro.addCuenta(eeccData);
                }
            }
        }
        int counter = 1;
        for(Nota nota: tempCorriente) {
            if(!nota.data.isEmpty()) {
                nota.id = counter;
                counter++;
                notas.add(nota);
            }
        }
        for(Nota nota: tempNoCorriente) {
            if(!nota.data.isEmpty()) {
                nota.id = counter;
                counter++;
                notas.add(nota);
            }
        }
        for(Nota nota: notas) {
            System.out.println("Nota " + nota.id + ": "+nota.name);
            int total = 0;
            for(EECCData eeccData: nota.data) {
                boolean reverse = nota.reverse;
                if (eeccData.cuenta.isReverse()) reverse = !reverse;
                System.out.println(nota.isCorriente ? reverse ? "  " + eeccData.cuenta.getName() + " (" + ((float) eeccData.saldoCorriente) / 100 + ")" : "  " + eeccData.cuenta.getName() + " " + ((float) eeccData.saldoCorriente) / 100 + "" : reverse ? "  " + eeccData.cuenta.getName() + " (" + ((float) eeccData.saldoNoCorriente) / 100 + ")" : "  " + eeccData.cuenta.getName() + " " + ((float) eeccData.saldoNoCorriente) / 100 + "");
                if(nota.isCorriente) if (reverse) total -= eeccData.saldoCorriente; else total += eeccData.saldoCorriente;
                else if (reverse) total -= eeccData.saldoNoCorriente; else total += eeccData.saldoNoCorriente;
            }
            System.out.println("Total: " + ((float) total)/100);
            output.add(new FinishedNota(nota.name, nota.id, nota.isCorriente, ((float) total)/100));
        }
        return output;
    }

}
