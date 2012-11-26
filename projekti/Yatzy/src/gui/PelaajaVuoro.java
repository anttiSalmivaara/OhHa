/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Component;
import javax.swing.JPanel;
import logiikka.Peli;
import yatzy.Pelaaja;

/**
 *
 * @author antti
 */
public class PelaajaVuoro {

    private Pelaaja pelaaja;
    private Peli peli;
    private JPanel taulukko;

    public PelaajaVuoro(JPanel taulukko, Peli peli) {
        this.peli = peli;
        this.taulukko = taulukko;
    }

    public void alusta() {
        pelaaja = peli.getPelaajat().get(0);
        for (Component c : ((JPanel) taulukko.getComponent(2)).getComponents()) {
            if (c.isEnabled()) {
                c.setEnabled(false);
            }
        }
    }

    public Pelaaja getVuorossa() {
        return pelaaja;
    }

    public void setSeuraava() {
        if (pelaaja.hashCode() == peli.getPelaajat().get(0).hashCode()) {
            pelaaja = peli.getPelaajat().get(1);
            aktivoiSeuraava(2);
        } else {
            pelaaja = peli.getPelaajat().get(0);
            aktivoiSeuraava(1);
        }
    }
    
    private void aktivoiSeuraava(int i) {
        if (i == 1) {
            for (Component c : ((JPanel) taulukko.getComponent(i)).getComponents()) {
                if (c.isEnabled()) {
                    ((KenttaNappi) c).setActive();
                }
            }
        }
    }
}
