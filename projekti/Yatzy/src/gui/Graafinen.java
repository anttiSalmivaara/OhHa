package gui;

import java.awt.*;
import javax.swing.*;
import logiikka.*;
import yatzy.Kentta;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Graafinen implements Runnable {

    private JFrame frame;
    private Peli peli;
    private CardLayout c;
    private NoppaIconMaker ikonit;

    public Graafinen(Peli peli) {
        this.peli = peli;
        this.c = new CardLayout();
        try {
            this.ikonit = new NoppaIconMaker();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

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

    private void luoRuudut(Container container) throws Exception {
        container.setLayout(new BorderLayout());
        container.add(this.nopat(), BorderLayout.NORTH);
        container.add(this.alaOsa(), BorderLayout.CENTER);

        JButton heita = new JButton("Heitä!");
        heita.addActionListener(new HeitaListener(peli, container));

        container.add(heita, BorderLayout.SOUTH);
    }

    private JPanel nopat() throws Exception {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(1, 5));

        for (int i = 0; i < 5; i++) {
            palaute.add(new GraafNoppa(ikonit.getIcon(i + 1)));
        }

        return palaute;

    }

    private JPanel alaOsa() {
        final String ALKU = "Pelaajan lisäys";
        final String PELI = "Varsinainen peli";

        JPanel palaute = new JPanel();
        palaute.setLayout(c);

        JPanel alkuruutu = luoAlkuRuutu();
        JPanel peliruutu = taulukko();

        palaute.add(alkuruutu, ALKU);
        palaute.add(peliruutu, PELI);

        return palaute;
    }

    private JPanel luoAlkuRuutu() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(8, 1));

        JLabel p1 = new JLabel("Pelaaja 1");
        JTextField pelaaja1 = new JTextField();

        JLabel p2 = new JLabel("Pelaaja 2");
        JTextField pelaaja2 = new JTextField();

        palaute.add(p1);
        palaute.add(pelaaja1);
        palaute.add(p2);
        palaute.add(pelaaja2);
        palaute.add(new JLabel());
        palaute.add(new JLabel());
        palaute.add(new JLabel());

        return palaute;
    }

    private JPanel taulukko() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(1, 3));

        palaute.add(this.luoKenttaOtsikot());
        palaute.add(this.luoYhdenPelaajanTaulukko(1));
        palaute.add(this.luoYhdenPelaajanTaulukko(2));

        return palaute;
    }

    private JPanel luoYhdenPelaajanTaulukko(int i) {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(18, 1));

        palaute.add(new KenttaOtsikko("Pelaaja " + i));
        palaute.add(new KenttaNappi(frame, peli, Kentta.YKKOSET));
        palaute.add(new KenttaNappi(frame, peli, Kentta.KAKKOSET));
        palaute.add(new KenttaNappi(frame, peli, Kentta.KOLMOSET));
        palaute.add(new KenttaNappi(frame, peli, Kentta.NELOSET));
        palaute.add(new KenttaNappi(frame, peli, Kentta.VITOSET));
        palaute.add(new KenttaNappi(frame, peli, Kentta.KUTOSET));
        palaute.add(new KenttaOtsikko(""));
        palaute.add(new KenttaNappi(frame, peli, Kentta.PARI));
        palaute.add(new KenttaNappi(frame, peli, Kentta.KAKSIPARIA));
        palaute.add(new KenttaNappi(frame, peli, Kentta.KOLMESAMAA));
        palaute.add(new KenttaNappi(frame, peli, Kentta.NELJASAMAA));
        palaute.add(new KenttaNappi(frame, peli, Kentta.PIENISUORA));
        palaute.add(new KenttaNappi(frame, peli, Kentta.SUURISUORA));
        palaute.add(new KenttaNappi(frame, peli, Kentta.TAYSKASI));
        palaute.add(new KenttaNappi(frame, peli, Kentta.YATZY));
        palaute.add(new KenttaNappi(frame, peli, Kentta.SATTUMA));
        palaute.add(new KenttaOtsikko(""));

        return palaute;
    }

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
