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
    private JPanel nopat;

    /**
     * Konstruktori saa parametrina Peli-luokan ilmentymän.
     *
     * @param peli Parametrina annettava Yatzy-pelin ilmentymä.
     */
    public Graafinen(Peli peli) {
        this.peli = peli;

        try {
            this.nopat = nopat(new NoppaIconMaker());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "Noppaikonien luonti ei onnistunut",
                    "Iso virhe!",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
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
        final String ALKU = "alku";
        final String PELI = "peli";
        final String LOPPU = "loppu";

        JPanel alkuruutu = luoAlkuRuutu();
        JPanel pelikentta = pelikentta();
        JPanel loppuruutu = loppuRuutu();

        container.setLayout(new CardLayout());

        container.add(alkuruutu, ALKU);
        container.add(pelikentta, PELI);
        container.add(loppuruutu, LOPPU);
    }

    /**
     * Pelaajien lisäysruutu.
     *
     * @return Pelaajien lisäysruutu.
     */
    private JPanel luoAlkuRuutu() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(8, 1));

        JTextField p1 = new JTextField("Pelaaja 1");

        JTextField p2 = new JTextField("Pelaaja 2");

        JLabel info1 = new JLabel("Kolmen heiton jälkeen on pakko sijoittaa kenttään.");
        JLabel info2 = new JLabel("Valitse säilytettävät nopat klikkaamalla niitä.");

        palaute.add(new JLabel("Anna pelaajien nimet:"));
        palaute.add(p1);
        palaute.add(p2);
        palaute.add(new JLabel());
        palaute.add(info1);
        palaute.add(info2);
        JButton addPlayer = new JButton("Lisää pelaajat");
        addPlayer.addActionListener(new PelaajanLisaysListener(frame.getContentPane(), p1, p2, peli));
        palaute.add(addPlayer);
        palaute.add(new JLabel());

        return palaute;
    }

    /**
     * Sisältää kenttäotsikot, nopat ja pelaajien pistetaulukot.
     *
     * @return Pelikenttä JPanel-oliossa.
     */
    private JPanel pelikentta() throws Exception {
        JPanel palaute = new JPanel();
        palaute.setMaximumSize(new Dimension(400, 80));
        palaute.setLayout(new BorderLayout());

        JButton heitaNappi = new JButton("Heitä!");
        heitaNappi.addActionListener(new HeitaListener(nopat, peli));
        heitaNappi.setMaximumSize(new Dimension(400, 20));

        palaute.add(nopat, BorderLayout.NORTH);
        palaute.add(taulukko(), BorderLayout.CENTER);
        palaute.add(heitaNappi, BorderLayout.SOUTH);

        return palaute;
    }

    private JPanel taulukko() throws Exception {
        JPanel palaute = new JPanel();
        palaute.setMinimumSize(new Dimension(400, 400));
        palaute.setMaximumSize(new Dimension(400, 400));
        palaute.setLayout(new GridLayout(1, 3));
        palaute.add(this.luoKenttaOtsikot());
        palaute.add(this.luoYhdenPelaajanTaulukko(1));
        palaute.add(this.luoYhdenPelaajanTaulukko(2));

        return palaute;
    }

    /**
     * Nopat lisätään nappeina JPanel-olioon.
     *
     * @return Nopat JPanel-oliossa.
     * @throws Exception
     */
    private JPanel nopat(NoppaIconMaker ikonit) throws Exception {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(1, 5));

        for (int i = 1; i < 6; i++) {
            palaute.add(new GraafNoppa(ikonit));
        }

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
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.YKKOSET));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.KAKKOSET));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.KOLMOSET));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.NELOSET));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.VITOSET));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.KUTOSET));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.BONUS));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.PARI));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.KAKSIPARIA));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.KOLMESAMAA));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.NELJASAMAA));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.PIENISUORA));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.SUURISUORA));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.TAYSKASI));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.YATZY));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.SATTUMA));
        palaute.add(new KenttaNappi(i, nopat, palaute, peli, Kentta.SUMMA));

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

    /**
     * Muodostaa pelin loppumisen jälkeen loppuruudun.
     *
     * @return Palauttaa loppuruudn JPanel-oliona.
     */
    private JPanel loppuRuutu() {
        JPanel palaute = new JPanel();

        JTextPane loppuviesti = new JTextPane();
        loppuviesti.setEnabled(false);

        palaute.add(loppuviesti);
        return palaute;

    }
}
