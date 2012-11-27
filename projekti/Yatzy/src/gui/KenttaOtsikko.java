package gui;

import java.awt.Color;
import javax.swing.JTextField;

/**
 * Otsikkokenttä
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class KenttaOtsikko extends JTextField {
    
    public KenttaOtsikko(String teksti) {
        super(teksti);
        super.setDisabledTextColor(Color.BLACK);
        super.setEnabled(false);
        super.setHorizontalAlignment(JTextField.RIGHT);
    }
}
