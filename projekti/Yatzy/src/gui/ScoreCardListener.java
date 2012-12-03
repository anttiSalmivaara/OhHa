package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    private ArrayList<GraafNoppa> gNopat;
    private int vastaPelaajaNro;

    public ScoreCardListener(int pelaajaNro, JPanel noppaPanel, JPanel taulukko, Peli peli, Kentta kentta) {
        this.gNopat = new ArrayList<>();
        
        this.taulukko = taulukko;
        this.peli = peli;
        this.kentta = kentta;
        
        for (Component c : noppaPanel.getComponents()) {
            gNopat.add((GraafNoppa) c);
        }
        
        if (pelaajaNro == 1) {
            this.vastaPelaajaNro = 2;
        } else {
            this.vastaPelaajaNro = 1;
        }
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
            peli.kirjaaPisteet(peli.getVuorossa(), kentta, peli.getNopat().values());
        } catch (Exception e) {
            System.out.println("Kenttä käytössä!");
        }
        ((KenttaNappi) ae.getSource()).setEnabled(false);
        ((KenttaNappi) ae.getSource()).setText("" + peli.getVuorossa().getPisteet().get(kentta));
        laskeBonusPisteet();
        laskeSumma();
        deAktivoiKentat();
        aktivoiVastapelaajanKentat();
        peli.vuoroVaihtuu();
        peli.heitaKaikkiNopat();
        uudetNopat();
        ((JButton) ((JPanel) ((JPanel) taulukko.getParent()).getParent()).getComponent(2)).setEnabled(true);
        if (peli.peliLoppu()) {
            vaihdaLopetusRuutu();
        }
    }

    /**
     * Mikäli bonuspisteet on myönnetty, näytetään ne bonuskentässä.
     */
    private void laskeBonusPisteet() {
        if (peli.getVuorossa().getPisteet().containsKey(Kentta.BONUS)) {
            ((KenttaNappi) taulukko.getComponent(7)).setText(peli.getVuorossa().getPisteet().get(Kentta.BONUS) + "");
        }
    }
    
    /**
     * Päivittää summan summakenttään.
     */
    private void laskeSumma() {
        ((KenttaNappi) taulukko.getComponent(17)).setText(peli.getVuorossa().getSumma() + "");
    }
    
    /**
     * Deaktivoi sen pelaajan kentät, joka oli vuorossa.
     */
    private void deAktivoiKentat() {
        for (int i = 1; i < 17; i++) {
            if (((KenttaNappi) taulukko.getComponent(i)).isEnabled()) {
                ((KenttaNappi) taulukko.getComponent(i)).setEnabled(false);
            }
        }
    }
    
    private void aktivoiVastapelaajanKentat() {
        JPanel vastustaja = (JPanel) ((JPanel) taulukko.getParent()).getComponent(vastaPelaajaNro);
        for (int i = 1; i < 17; i++) {
            KenttaNappi kn = (KenttaNappi) vastustaja.getComponent(i);
            if (kn.getText().isEmpty()) {
                kn.setEnabled(true);
            }
        }
    }
    
    private void uudetNopat() {
        for (int i = 0; i < 5; i++) {
            gNopat.get(i).uusiNoppa(peli.getNopat().get(i + 1).getLuku());
        }
    }
    
    private void vaihdaLopetusRuutu() {
        Container pelisarakkeet = taulukko.getParent();
        Container pelikentta = pelisarakkeet.getParent();
        Container luoRuudut = pelikentta.getParent();
        CardLayout luoRuudutLayout = (CardLayout) luoRuudut.getLayout();
        ((JLabel) ((JPanel) luoRuudut.getComponent(2)).getComponent(0)).setText(lopetusRuudunTeksti());
        luoRuudutLayout.show(luoRuudut, "loppu");
    }
    
    private String lopetusRuudunTeksti() {
        StringBuilder sb = new StringBuilder();
        sb.append("Peli loppu!");
        sb.append("\n");
        sb.append("Voittaja: ");
        sb.append(peli.getVoittaja().getNimi());
        sb.append("\n");
        sb.append("Tulos: ");
        sb.append(peli.getVoittaja().getSumma());
        
        return sb.toString();
    }
}
