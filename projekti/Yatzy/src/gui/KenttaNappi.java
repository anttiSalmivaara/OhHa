package gui;

import javax.swing.JButton;
import yatzy.Kentta;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class KenttaNappi extends JButton {
    
    private Kentta kentta;
    
    public KenttaNappi(Kentta kentta) {
        super();
        this.kentta = kentta;
    }
    
    public Kentta getKentta() {
        return kentta;
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
}
