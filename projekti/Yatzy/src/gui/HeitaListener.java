package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import logiikka.Noppa;
import logiikka.Peli;

/**
 * Luokka kuuntelee nopanheitto-nappia.
 *
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class HeitaListener implements ActionListener {

    private Peli peli;
    private ArrayList<GraafNoppa> gNopat;
    private int heittoLaskuri;

    /**
     * Konstruktori saa argumentteina nopat ja pelin.
     * 
     * Nopat ovat noppien graafinen esitys JButton-laajennuksina JPanel-oliossa
     * ja kuuntelija tarvitsee myös Peli-olion ilmentymän jotta se voi käsitellä
     * varsinaisia Noppa-olioita.
     * 
     * @param nopat Graafiset nopat JPanel-oliona.
     * @param peli Peli-luokan ilmentymä.
     */
    public HeitaListener(JPanel nopat, Peli peli) {
        this.peli = peli;
        this.gNopat = new ArrayList<>();
        for (Component c : nopat.getComponents()) {
            this.gNopat.add((GraafNoppa) c);
        }
        this.heittoLaskuri = 0;
    }

    /**
     * Heittää nopat napin painalluksesta.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        uudetSilmaluvut(peli.heitaNopat(getHeitettavat()));
        for (GraafNoppa n : gNopat) {
            n.aktivoi();
        }
        heittoLaskuri++;
        if (heittoLaskuri == 2) {
           ((JButton) ae.getSource()).setEnabled(false);
        }
    }
    
    /**
     * Nollaa noppien heittokertojen laskurin.
     */
    public void nollaaLaskuri() {
        heittoLaskuri = 0;
    }

    /**
     * Palauttaa heitettävät nopat.
     *
     * Palauttaa heitettävät nopat GraafNoppien valinnan perusteella.
     *
     * @return Lista heitettävistä nopista järjestysluvun mukaan.
     */
    private List<Integer> getHeitettavat() {
        ArrayList<Integer> palaute = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (!gNopat.get(i).isSelected()) {
                palaute.add(i + 1);
            }
        }
        return palaute;
    }
    
    /**
     * Päivittää nopan ikonit vastaamaan noppien silmälukua.
     * 
     * @param uudet Noppien tilanne Inteer-Noppa-kuvauksena.
     */
    private void uudetSilmaluvut(Map<Integer, Noppa> uudet) {
        for (int i = 1; i < 6; i++) {
            gNopat.get(i - 1).uusiNoppa(uudet.get(i).getLuku());
        }
    }
}
