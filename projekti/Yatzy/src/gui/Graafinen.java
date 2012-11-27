package gui;

import java.awt.*;
import javax.swing.*;
import logiikka.*;
import yatzy.Kentta;

/**
 * Luokka pitää huolen Yatzy-pelin graafisesta käyttöliittymästä.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class Graafinen implements Runnable {

    private JFrame frame;
    private Peli peli;
    private JPanel taulukko;
    private JPanel alaOsa;
    private JPanel nopat;
    private NoppaIconMaker ikonit;
    private PelaajaVuoro pv;
    
    /**
     * Konstruktori saa parametrina Peli-luokan ilmentymän.
     * 
     * @param peli Parametrina annettava Yatzy-pelin ilmentymä.
     */    
    public Graafinen(Peli peli) {
        this.peli = peli;
        this.taulukko = taulukko();
        this.alaOsa = alaOsa();
        this.pv = new PelaajaVuoro(taulukko, peli);
        try {
            this.ikonit = new NoppaIconMaker();
            this.nopat = nopat();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Käynnistää pelin.
     */
    @Override
    public void run() {
        frame = new JFrame("Yatzy v.1");
        frame.setMinimumSize(new Dimension(400, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            luoRuudut(frame.getContentPane());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        frame.setVisible(true);
    }

    /**
     * Luo graafisen käyttäliittymän muotoilun.
     * 
     * Container-olioon lisätään Nopat yläreunaan, pelaajien lisäys ja pelialue
     * lisätään keskelle. Alaosaan lisätään noppien heittonappi.
     * 
     * @param container Container-olio, johon alueet liitetään.
     * @throws Exception 
     */
    private void luoRuudut(Container container) throws Exception {
        container.setLayout(new BorderLayout());
        container.add(nopat, BorderLayout.NORTH);
        container.add(alaOsa, BorderLayout.CENTER);

        JButton heita = new JButton("Heitä!");
        heita.addActionListener(new HeitaListener(pv, nopat, peli));

        container.add(heita, BorderLayout.SOUTH);
    }

    /**
     * Nopat lisätään nappeina JPanel-olioon.
     * 
     * @return Nopat JPanel-oliossa.
     * @throws Exception 
     */
    private JPanel nopat() throws Exception {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(1, 5));

        for (int i = 0; i < 5; i++) {
            palaute.add(new GraafNoppa(ikonit.getIcon(i + 1)));
        }

        return palaute;

    }

    /**
     * Sisältää pelaajien lisäyksen ja varsinaisen pelikentän.
     * 
     * @return Pelaajien lisäys ja pelkikenttä JPanel-oliossa.
     */
    private JPanel alaOsa() {
        final String ALKU = "alku";
        final String PELI = "peli";

        JPanel palaute = new JPanel();
        palaute.setLayout(new CardLayout());

        JPanel alkuruutu = luoAlkuRuutu();
        JPanel peliruutu = taulukko;

        palaute.add(alkuruutu, ALKU);
        palaute.add(peliruutu, PELI);

        return palaute;
    }

    /**
     * Pelaajien lisäysruutu.
     * 
     * @return Pelaajien lisäysruutu.
     */
    private JPanel luoAlkuRuutu() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(8, 1));

        JTextField pelaaja1 = new JTextField("Pelaaja 1");

        JTextField pelaaja2 = new JTextField("Pelaaja 2");
        
        JButton addPlayer = new JButton("Lisää pelaajat");

        palaute.add(new JLabel());
        palaute.add(pelaaja1);
        palaute.add(new JLabel());
        palaute.add(pelaaja2);
        palaute.add(new JLabel());
        addPlayer.addActionListener(new PelaajanLisaysListener(palaute, alaOsa, pv, taulukko, peli));
        palaute.add(addPlayer);
        palaute.add(new JLabel());

        return palaute;
    }

    /**
     * Sisältää kenttäotsikot ja pelaajien pistetaulukot.
     * 
     * @return Pelikenttä JPanel-oliossa.
     */
    private JPanel taulukko() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(1, 3));

        palaute.add(this.luoKenttaOtsikot());
        palaute.add(this.luoYhdenPelaajanTaulukko(1));
        palaute.add(this.luoYhdenPelaajanTaulukko(2));

        return palaute;
    }
    
    /**
     * Luo graafisen pistetaulukon yhdelle pelajaalle.
     * 
     * @param i Saa arvon 1 tai 2 pelaajan mukaan.
     * @return Yhden pelaajan pistetaulukko JPanel-oliossa.
     */
    private JPanel luoYhdenPelaajanTaulukko(int i) {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(18, 1));

        palaute.add(new KenttaOtsikko("Pelaaja " + i));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.YKKOSET));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.KAKKOSET));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.KOLMOSET));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.NELOSET));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.VITOSET));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.KUTOSET));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.BONUS));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.PARI));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.KAKSIPARIA));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.KOLMESAMAA));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.NELJASAMAA));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.PIENISUORA));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.SUURISUORA));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.TAYSKASI));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.YATZY));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.SATTUMA));
        palaute.add(new KenttaNappi(pv, palaute, peli, Kentta.SUMMA));

        return palaute;
    }

    /**
     * Luo otsikot pistetaulukoille.
     * 
     * @return Otsikot JPanel-oliossa.
     */
    private JPanel luoKenttaOtsikot() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(18, 1));

        palaute.add(new KenttaOtsikko("Nimi"));
        palaute.add(new KenttaOtsikko("1"));

        palaute.add(new KenttaOtsikko("2"));
        palaute.add(new KenttaOtsikko("3"));
        palaute.add(new KenttaOtsikko("4"));
        palaute.add(new KenttaOtsikko("5"));
        palaute.add(new KenttaOtsikko("6"));
        palaute.add(new KenttaOtsikko("Bonus"));
        palaute.add(new KenttaOtsikko("Pari"));
        palaute.add(new KenttaOtsikko("2 paria"));
        palaute.add(new KenttaOtsikko("3 samaa"));
        palaute.add(new KenttaOtsikko("4 samaa"));
        palaute.add(new KenttaOtsikko("suora"));
        palaute.add(new KenttaOtsikko("SUORA"));
        palaute.add(new KenttaOtsikko("Täyskäsi"));
        palaute.add(new KenttaOtsikko("Yatzy"));
        palaute.add(new KenttaOtsikko("Sattuma"));
        palaute.add(new KenttaOtsikko("Summa"));

        return palaute;
    }
}
