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
    private ArrayList<GraafNoppa> nopat;

    public HeitaListener(JPanel nopat, Peli peli) {
        this.peli = peli;
        this.nopat = new ArrayList<>();
        for (Component c : nopat.getComponents()) {
            this.nopat.add((GraafNoppa) c);
        }
    }

    /**
     * Heittää nopat napin painalluksesta.
     *
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Map<Integer, Noppa> uudet = peli.heitaNopat(getHeitettavat());
        uudetSilmaluvut(uudet);
        for (GraafNoppa n : nopat) {
            n.aktivoi();
        }
        if (peli.getHeittojenMaara() == 3) {
            ((JButton) ae.getSource()).setEnabled(false);
        }
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
            if (!nopat.get(i).isSelected()) {
                palaute.add(i + 1);
            }
        }
        return palaute;
    }
    
    private void uudetSilmaluvut(Map<Integer, Noppa> uudet) {
        for (int i = 1; i < 6; i++) {
            nopat.get(i - 1).uusiNoppa(uudet.get(i).getLuku());
        }
    }
}
