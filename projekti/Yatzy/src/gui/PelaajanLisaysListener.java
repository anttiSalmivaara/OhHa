/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logiikka.Peli;

/**
 * Pelaajat tallentavan napin kuuntelija.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class PelaajanLisaysListener implements ActionListener {
    
    private Container container;
    private JTextField p1;
    private JTextField p2;
    private Peli peli;
    
    public PelaajanLisaysListener(Container container, JTextField p1, JTextField p2, Peli peli) {
        this.container = container;
        this.p1 = p1;
        this.p2 = p2;
        this.peli = peli;
    }
    
    /**
     * Lisää pelaajat peliin.
     * 
     * Vaihtaa käyttöliittymän keskipaneelin näkymän pelikentäksi.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        peli.addPelaaja(p1.getText());
        peli.addPelaaja(p2.getText());
        peli.heitaKaikkiNopat();
        peli.aloita();
        piirraUudetNopat( (JButton) ae.getSource());
        ((CardLayout) container.getLayout()).show(container, "peli");
    }
    
    private void piirraUudetNopat(JButton lisaaPelaaja) {
        JPanel alkuRuutu = (JPanel) lisaaPelaaja.getParent();
        JPanel cardLayoutRuutu = (JPanel) alkuRuutu.getParent();
        JPanel peliKentta = (JPanel) cardLayoutRuutu.getComponent(1);
        JPanel noppaPanel = (JPanel) peliKentta.getComponent(0);
        
        ArrayList<GraafNoppa> gNopat = new ArrayList<>();
        for (Component c : noppaPanel.getComponents()) {
            gNopat.add( (GraafNoppa) c);
        }
        
        for (int i = 0; i < 5; i++) {
            gNopat.get(i).uusiNoppa(peli.getNopat().get(i + 1).getLuku());
        }
    }
    
}
