
package gui;

import java.awt.*;
import javax.swing.*;
import logiikka.*;
import yatzy.Kentta;
import yatzy.Pelaaja;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Graafinen implements Runnable {
    
    private JFrame frame;
    private Peli peli;
    
    public Graafinen(Peli peli) {
        this.peli = peli;
    }
    
    @Override
    public void run(){
        frame = new JFrame("Yatzy v.1");
        frame.setMinimumSize(new Dimension(100 + (100 * peli.getPelaajat().size()) , 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoAlkuRuutu(frame.getContentPane());
        frame.setVisible(true);
        
        luoPeliKentta(frame.getContentPane());
        
        frame.setVisible(true);
             
    }
    
    private void luoAlkuRuutu(Container container) {
        container.setLayout(new BorderLayout());
        JTextField pelaaja = 
    }
    
    private void luoPeliKentta(Container container) {
        container.setLayout(new BorderLayout());
        container.setMinimumSize(new Dimension(250, 200));
        JPanel nopat = nopat();
        container.add(nopat, BorderLayout.NORTH);
        JPanel taulukko = taulukko();
        container.add(taulukko, BorderLayout.CENTER);
        JButton heitaNopat = new JButton("Heitä!");
        container.add(heitaNopat, BorderLayout.SOUTH);
    }
    
    private JPanel nopat() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(1,5));

        JButton noppa1 = new JButton("┌───────┐\n│       │\n│   Y   │\n│       │\n└───────┘");
        JButton noppa2 = new JButton("┌───────┐\n│       │\n│   A   │\n│       │\n└───────┘");
        JButton noppa3 = new JButton("┌───────┐\n│       │\n│   T   │\n│       │\n└───────┘");
        JButton noppa4 = new JButton("┌───────┐\n│       │\n│   Z   │\n│       │\n└───────┘");
        JButton noppa5 = new JButton("┌───────┐\n│       │\n│   Y   │\n│       │\n└───────┘");
        
        noppa1.addActionListener(new NoppaListener());
        noppa2.addActionListener(new NoppaListener());
        noppa3.addActionListener(new NoppaListener());
        noppa4.addActionListener(new NoppaListener());
        noppa5.addActionListener(new NoppaListener());
        
        palaute.add(noppa1);
        palaute.add(noppa2);
        palaute.add(noppa3);
        palaute.add(noppa4);
        palaute.add(noppa5);
        
        return palaute;
    }
    
    private JPanel taulukko() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(1, 1 + peli.getPelaajat().size()));
        palaute.setMinimumSize(new Dimension(20 + (peli.getPelaajat().size() * 20), 10));
        
        palaute.add(this.luoKenttaOtsikot());
        
        for (Pelaaja p : peli.getPelaajat()) {
            palaute.add(this.luoYhdenPelaajanTaulukko(p.getNimi()));
        }
        return palaute;
    }
    
    private JPanel luoYhdenPelaajanTaulukko(String nimi) {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(18,1));
        palaute.setMinimumSize(new Dimension(25, 200));
        
        
        JTextArea nimiKentta = new JTextArea(nimi);
        JButton ykkoset = new KenttaNappi(Kentta.YKKOSET);
        JButton kakkoset = new KenttaNappi(Kentta.KAKKOSET);
        JButton kolmoset = new KenttaNappi(Kentta.KOLMOSET);
        JButton neloset = new KenttaNappi(Kentta.NELOSET);
        JButton vitoset = new KenttaNappi(Kentta.VITOSET);
        JButton kutoset = new KenttaNappi(Kentta.KUTOSET);
        JTextArea bonus = new JTextArea();
        JButton pari = new KenttaNappi(Kentta.PARI);
        JButton kaksiparia = new KenttaNappi(Kentta.KAKSIPARIA);
        JButton kolmesamaa = new KenttaNappi(Kentta.KOLMESAMAA);
        JButton neljasamaa = new KenttaNappi(Kentta.NELJASAMAA);
        JButton pienisuora = new KenttaNappi(Kentta.PIENISUORA);
        JButton suurisuora = new KenttaNappi(Kentta.SUURISUORA);
        JButton tayskasi = new KenttaNappi(Kentta.TAYSKASI);
        JButton yatzy = new KenttaNappi(Kentta.YATZY);
        JButton sattuma = new KenttaNappi(Kentta.SATTUMA);
        JTextArea summa = new JTextArea();
        
        palaute.add(nimiKentta);
        palaute.add(ykkoset);
        palaute.add(kakkoset);
        palaute.add(kolmoset);
        palaute.add(neloset);
        palaute.add(vitoset);
        palaute.add(kutoset);
        palaute.add(bonus);
        palaute.add(pari);
        palaute.add(kaksiparia);
        palaute.add(kolmesamaa);
        palaute.add(neljasamaa);
        palaute.add(pienisuora);
        palaute.add(suurisuora);
        palaute.add(tayskasi);
        palaute.add(yatzy);
        palaute.add(sattuma);
        palaute.add(summa);

        return palaute;
    }
    
    private JPanel luoKenttaOtsikot() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(18,1));
        palaute.setMinimumSize(new Dimension(25, 200));
        
        JTextArea nimiKentta = new JTextArea("Nimi");
        JTextArea ykkoset = new JTextArea("1");

        JTextArea kakkoset = new JTextArea("2");
        JTextArea kolmoset = new JTextArea("3");
        JTextArea neloset = new JTextArea("4");
        JTextArea vitoset = new JTextArea("5");
        JTextArea kutoset = new JTextArea("6");
        JTextArea bonus = new JTextArea("Bonus");
        JTextArea pari = new JTextArea("Pari");
        JTextArea kaksiparia = new JTextArea("2 paria");
        JTextArea kolmesamaa = new JTextArea("3 samaa");
        JTextArea neljasamaa = new JTextArea("4 samaa");
        JTextArea pienisuora = new JTextArea("suora");
        JTextArea suurisuora = new JTextArea("SUORA");
        JTextArea tayskasi = new JTextArea("Täyskäsi");
        JTextArea yatzy = new JTextArea("Yatzy");
        JTextArea sattuma = new JTextArea("Sattuma");
        JTextArea summa = new JTextArea("Summa");
        
        palaute.add(nimiKentta);
        palaute.add(ykkoset);
        palaute.add(kakkoset);
        palaute.add(kolmoset);
        palaute.add(neloset);
        palaute.add(vitoset);
        palaute.add(kutoset);
        palaute.add(bonus);
        palaute.add(pari);
        palaute.add(kaksiparia);
        palaute.add(kolmesamaa);
        palaute.add(neljasamaa);
        palaute.add(pienisuora);
        palaute.add(suurisuora);
        palaute.add(tayskasi);
        palaute.add(yatzy);
        palaute.add(sattuma);
        palaute.add(summa);
        
        return palaute;
    }
}
