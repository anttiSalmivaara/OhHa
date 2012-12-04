package gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import logiikka.Noppa;
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

    /**
     * Konstruktori saa argumenttina pistetaulukon kenttiä edustavan napin tiedot.
     * 
     * Konstruktori lisää graafiset nopat JPanelista listaan ja tallentaa
     * tiedot, kuten tiedon siitä, mikä on vastapelaajan numero.
     * 
     * @param pelaajaNro Pelaajan numero.
     * @param noppaPanel Nopat JPanel-oliossa.
     * @param taulukko Pelaajan pistetaulukko.
     * @param peli Peli-luokan ilmentymä.
     * @param kentta Tieto siitä, mitä kenttää kuuntelija kuuntelee.
     */
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
     * Metodi kirjaa pisteet pelaajan pistetaulukkoon. Poistaa pistekenttää
     * edustavan KenttaNapin käytöstä, sekä asettaa näkyviin pisteet. Sen
     * jälkeen tarkistetaan, tuleeko bonuskenttään pisteitä, päivitetään summa
     * summakenttään. Sitten deaktivoidaan vuoron päättäneen pelaajan
     * pistetaulukko ja aktivoidaan vastapelaajan. Tämän jälkeen ilmoitetaan
     * Peli-oliolle, että vuoro vaihtuu. Seuraavaksi heitetään uudet nopat ja 
     * asetetaan ne näkyville. Jos noppia on heitetty kolme kertaa edellisellä
     * vuorolla täytyy ne aktivoida uudelleen, samoin kuin heitä-nappi.
     * Heittolaskuri nollataan.
     * 
     * Viimeiseksi tarkistetaan onko peli loppu, ja jos on, näytetään
     * lopetusruutu.
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
        this.laskeBonusPisteet();
        this.laskeSumma();
        this.deAktivoiKentat();
        this.aktivoiVastapelaajanKentat();
        peli.vuoroVaihtuu();
        this.piirraUudetNopat(peli.heitaKaikkiNopat());
        for (GraafNoppa gn : gNopat) {
            gn.setEnabled(true);
        }
        
        //aktivoidaan heitä-nappi:
        ((JButton) ((JPanel) ((JPanel) taulukko.getParent()).getParent()).getComponent(2)).setEnabled(true);
        
        ((HeitaListener) ((JButton) ((JPanel) ((JPanel) taulukko.getParent()).getParent()).getComponent(2)).getActionListeners()[0]).nollaaLaskuri();
        if (peli.peliLoppu()) {
            vaihdaLopetusRuutu();
        }
    }

    /**
     * Mikäli bonuspisteet on myönnetty, näytetään ne bonuskentässä.
     */
    private void laskeBonusPisteet() {
        if (peli.getVuorossa().getPisteet().containsKey(Kentta.BONUS)) {
            ((KenttaNappi) taulukko.getComponent(7)).setText("" + peli.getVuorossa().getPisteet().get(Kentta.BONUS));
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
    
    /**
     * Aktivoi vuoron jälkeen vastapelaajan kentät.
     * 
     */
    private void aktivoiVastapelaajanKentat() {
        JPanel vastustaja = (JPanel) ((JPanel) taulukko.getParent()).getComponent(vastaPelaajaNro);
        for (int i = 1; i < 17; i++) {
            KenttaNappi kn = (KenttaNappi) vastustaja.getComponent(i);
            if (kn.getText().isEmpty()) {
                kn.setEnabled(true);
            }
        }
    }
    
    /**
     * Päivittää noppien ikonit pisteiden tallennuksen jälkeen.
     * 
     */
    private void piirraUudetNopat(Map<Integer, Noppa> nopat) {
        for (int i = 0; i < 5; i++) {
            gNopat.get(i).uusiNoppa(nopat.get(i + 1).getLuku());
            gNopat.get(i).aktivoi();
        }
    }
    
    /**
     * Kun Peli on loppu, vaihdetaan loppuruutu.
     * 
     */
    private void vaihdaLopetusRuutu() {
        Container pelisarakkeet = taulukko.getParent();
        Container pelikentta = pelisarakkeet.getParent();
        Container luoRuudut = pelikentta.getParent();
        CardLayout luoRuudutLayout = (CardLayout) luoRuudut.getLayout();
        ((JTextPane) ((JPanel) luoRuudut.getComponent(2)).getComponent(0)).setText(lopetusRuudunTeksti());
        luoRuudutLayout.show(luoRuudut, "loppu");
    }
    
    /**
     * Palauttaa tekstin lopetusruutuun.
     * 
     * Ilmoittaa voittajan ja voittajan pistemäärän.
     * 
     * @return Lopetusruudun teksti.
     */
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
