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
    
    /**
     * Konstruktori lisäysnapin kuuntelijalle.
     * 
     * Kuuntelija saa argumenttina Container-olion, joka sisältää koko
     * käyttöliittymän toiminnallisuuden ja asioita helpottaakseen erikseen
     * kentät, joihin pelajaien nimet syötetään, sekä Peli-olion ilmentymän.
     * 
     * @param container Koko käyttöliittymä.
     * @param p1 Pelaajan 1 nimikenttä.
     * @param p2 Pelaajan 2 nimikenttä.
     * @param peli Peli-olion ilmentymä.
     */
    public PelaajanLisaysListener(Container container, JTextField p1, JTextField p2, Peli peli) {
        this.container = container;
        this.p1 = p1;
        this.p2 = p2;
        this.peli = peli;
    }
    
    /**
     * Lisää pelaajat peliin.
     * 
     * Kertoo Peli-oliolle, että peli alkaa, tulostaa ensimmäiset nopat,
     * deaktivoi järjestyksessä toisen pelaajan kentät, hakee pelaajien nimet.
     * Vaihtaa käyttöliittymän keskipaneelin näkymän pelikentäksi.
     * @param ae Pelaajan lisäysnapin event.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        peli.addPelaaja(p1.getText());
        peli.addPelaaja(p2.getText());
        peli.aloita();
        this.piirraUudetNopat( (JButton) ae.getSource());
        this.deAktivoiKentat();
        ((JTextField) ((JPanel) ((JPanel) ((JPanel) container.getComponent(1)).getComponent(1)).getComponent(1)).getComponent(0)).setText(p1.getText());
        ((JTextField) ((JPanel) ((JPanel) ((JPanel) container.getComponent(1)).getComponent(1)).getComponent(2)).getComponent(0)).setText(p2.getText());        
        ((CardLayout) container.getLayout()).show(container, "peli");
    }
    
    /**
     * Piirtää nopat kun peli alkaa.
     * 
     * @param lisaaPelaaja Nappi jonka avulla haetaan noppapaneeli.
     */
    private void piirraUudetNopat(JButton pelaajanLisaavaNappi) {
        JPanel alkuRuutu = (JPanel) pelaajanLisaavaNappi.getParent();
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
    
    /**
     * Deaktivoi pelin alkaessa järjestyksessä toisen pelaajan kentät.
     */
    private void deAktivoiKentat() {
        Container pelikentta = (Container) container.getComponent(1);
        Container taulukko = (Container) pelikentta.getComponent(1);
        Container yhdenPelaajanTaulukko = (Container) taulukko.getComponent(2);
        for (int i = 1; i < 17; i++) {
            if (((KenttaNappi) yhdenPelaajanTaulukko.getComponents()[i]).isEnabled()) {
                ((KenttaNappi) yhdenPelaajanTaulukko.getComponents()[i]).setEnabled(false);
            }
        }
    }
    
}
