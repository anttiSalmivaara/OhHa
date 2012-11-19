
package gui;

import java.awt.*;
import javax.swing.*;
import logiikka.*;
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentit(frame.getContentPane());
        
        frame.setVisible(true);
             
    }
    
    private void luoKomponentit(Container container) {
        container.setLayout(new BorderLayout());
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
        JPanel palaute = new JPanel;
        
        for (int i : peli)
        return palaute;
    }
    
    private JPanel luoYhdenPelaajanTaulukko() {
        JPanel palaute = new JPanel();
        palaute.setLayout(new GridLayout(17,1));
        
        JButton ykkoset = new JButton();
        JButton kakkoset = new JButton();
        JButton kolmoset = new JButton();
        JButton neloset = new JButton();
        JButton vitoset = new JButton();
        JButton kutoset = new JButton();
        JTextField bonus = new JTextField();
        JButton pari = new JButton();
        JButton kaksiparia = new JButton();
        JButton kolmesamaa = new JButton();
        JButton neljasamaa = new JButton();
        JButton pienisuora = new JButton();
        JButton suurisuora = new JButton();
        JButton tayskasi = new JButton();
        JButton yatzy = new JButton();
        JButton sattuma = new JButton();
        JTextField summa = new JTextField();
        
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
