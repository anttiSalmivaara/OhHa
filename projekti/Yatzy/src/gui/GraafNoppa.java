package gui;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JToggleButton;

/**
 * Luokka esittää graafisen nopan laajennettuna JButtonina.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class GraafNoppa extends JToggleButton {

    /**
     * Konstruktori saa parametrina nopassa aluksi näkyvän ikonin.
     * 
     * @param icon Aluksi näytettävä ikoni.
     */
    public GraafNoppa(Icon icon) {
        super(icon);
        super.setPreferredSize(new Dimension(80, 80));
    }

    /**
     * Asettaa noppaan parametrina annetun ikonin.
     * 
     * @param icon 
     */
    public void uusiNoppa(Icon icon) {
        super.setIcon(icon);
    }
}
