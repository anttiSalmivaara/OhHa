package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import logiikka.Peli;
import yatzy.Kentta;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class KenttaNappi extends JButton {
    
    private Kentta kentta;
    
    public KenttaNappi(JFrame frame, Peli peli, Kentta kentta) {
        super();
        this.kentta = kentta;
        super.addActionListener(new ScoreCardListener(frame, peli, this.kentta));
    }
}
