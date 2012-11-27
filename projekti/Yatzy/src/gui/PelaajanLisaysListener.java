/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logiikka.Peli;

/**
 *
 * @author antti
 */
public class PelaajanLisaysListener implements ActionListener {
    
    private Peli peli;
    private JTextField p1;
    private JTextField p2;
    private JPanel alaOsa;
    private JPanel taulukko;
    private PelaajaVuoro pv;
    
    public PelaajanLisaysListener(JPanel pelaajaKentat, JPanel alaOsa, PelaajaVuoro pv, JPanel taulukko, Peli peli) {
        this.peli = peli;
        this.p1 = (JTextField) pelaajaKentat.getComponent(1);
        this.p2 = (JTextField) pelaajaKentat.getComponent(3);
        this.taulukko = taulukko;
        this.pv = pv;
        this.alaOsa = alaOsa;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        peli.addPelaaja(p1.getText());
        peli.addPelaaja(p2.getText());
        p1.setEnabled(false);
        p2.setEnabled(false);
        ((JButton) ae.getSource()).setEnabled(false);
        ((KenttaOtsikko) ((JPanel) taulukko.getComponent(1)).getComponent(0)).setText(p1.getText());
        ((KenttaOtsikko) ((JPanel) taulukko.getComponent(2)).getComponent(0)).setText(p2.getText());
        ((CardLayout) alaOsa.getLayout()).show(alaOsa,"peli");
    }
}
