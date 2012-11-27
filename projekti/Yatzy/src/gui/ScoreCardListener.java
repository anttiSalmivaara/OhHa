package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import logiikka.Peli;
import yatzy.Kentta;

/**
 * Kuuntelee pisteet tallentavaa kenttää.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class ScoreCardListener implements ActionListener {

    private Kentta kentta;
    private Peli peli;
    private JPanel taulukko;
    private PelaajaVuoro pv;

    public ScoreCardListener(PelaajaVuoro pv, JPanel taulukko, Peli peli, Kentta kentta) {
        this.peli = peli;
        this.kentta = kentta;
        this.taulukko = taulukko;
    }

    /**
     * Tallentaa arvon kenttään ja vaihtaa vuoroa.
     * 
     * Deaktivoi sen pelaajan kentät, joka ei ole vuorossa.
     * 
     * @param ae Nappievent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            peli.kirjaaPisteet(pv.getVuorossa(), kentta, peli.getNopat().values());
        } catch (Exception e) {
            System.out.println("plöh");
        }
        ((KenttaNappi) ae.getSource()).setEnabled(false);
        laskeBonusPisteet();
        laskeSumma();
        deAktivoiKentat();
        pv.setSeuraava();
    }

    /**
     * Mikäli bonuspisteet on myönnetty, näytetään ne bonuskentässä.
     */
    private void laskeBonusPisteet() {
        if (pv.getVuorossa().getPisteet().containsKey(Kentta.BONUS)) {
            ((KenttaOtsikko) taulukko.getComponent(7)).setText(pv.getVuorossa().getPisteet().get(Kentta.BONUS) + "");
        }
    }
    
    /**
     * Päivittää summan summakenttään.
     */
    private void laskeSumma() {
        ((KenttaOtsikko) taulukko.getComponent(17)).setText(pv.getVuorossa().getSumma() + "");
    }
    
    /**
     * Deaktivoi sen pelaajan kentät, joka oli vuorossa.
     */
    private void deAktivoiKentat() {
        for (Component c : taulukko.getComponents()) {
            if (c.isEnabled()) {
                c.setEnabled(false);
            }
        }
    }
}
