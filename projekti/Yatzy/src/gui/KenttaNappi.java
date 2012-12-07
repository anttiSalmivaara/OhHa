package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import logiikka.Peli;
import yatzy.Kentta;

/**
 * Luokka lisää JButton-luokkaan toiminnallisuutta.
 * 
 * Luokka toimii tulosnäyttönä pistekortilla.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class KenttaNappi extends JButton {

    private Kentta kentta;

    /**
     * Konstruktori saa argumenttina kentän tarvitsemat tiedot.
     * 
     * Konstruktori saa pelaajanumeron välitettäväksi pistetaulukon
     * kuuntelijalle, graafiset nopat JPanel-oliona, pelaajan koko
     * pistetaulukon, Peli-olion ilmentymän sekä tiedon siitä, mitä kenttää
     * nappi edustaa.
     * 
     * @param pelaajaNro Pelaajanumero KenttaNapin kuuntelijalle.
     * @param nopat Graafiset Nopat JPanel-oliossa.
     * @param taulukko Pelaajan pistetaulukko.
     * @param peli Peli-luokan ilmentymä.
     * @param kentta Tieto siitä, mitä kenttää nappi edustaa.
     */
    public KenttaNappi(int pelaajaNro, JPanel nopat, JPanel taulukko, Peli peli, Kentta kentta) {
        super();
        this.kentta = kentta;
        this.addActionListener(new ScoreCardListener(pelaajaNro, nopat, taulukko, peli, this.kentta));
        if (this.kentta == Kentta.BONUS || this.kentta == Kentta.SUMMA) {
            super.setEnabled(false);
            super.setText("0");
        }
    }

    /**
     * Asettaa kentän aktiviseksi.
     */
    public void setActive() {
        if (super.getText().isEmpty()) {
            super.setEnabled(true);
        }
    }

    /**
     * Asettaa kentän passiiviseksi.
     */
    public void setPassive() {
        super.setEnabled(false);
    }
    
    /**
     * Asettaa pistemäärän kenttään.
     * @param text Pistemäärä String-oliona.
     */
    @Override
    public final void setText(String text) {
        super.setText(text);
        super.repaint();
    }
}
