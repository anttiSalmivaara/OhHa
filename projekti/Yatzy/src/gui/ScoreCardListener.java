
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import logiikka.Peli;
import yatzy.Kentta;


/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class ScoreCardListener implements ActionListener {
    
    private Kentta kentta;
    private Peli peli;

    public ScoreCardListener(JFrame frame, Peli peli, Kentta kentta) {
        this.peli = peli;
        this.kentta = kentta;
    }
    
    
    public void setPisteet(int pisteet) {
        super.setText(""+ pisteet);
    }
    
    public void setActive() {
        super.setEnabled(true);
    }
    
    public void setPassive() {
        super.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         
    }
}
