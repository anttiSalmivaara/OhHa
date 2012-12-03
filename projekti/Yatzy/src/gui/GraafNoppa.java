package gui;

import java.awt.Dimension;
import javax.swing.JToggleButton;

/**
 * Luokka esittää graafisen nopan laajennettuna JButtonina.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class GraafNoppa extends JToggleButton {
    
    private NoppaIconMaker ikonit;

    /**
     * Konstruktori saa parametrina nopassa aluksi näkyvän ikonin.
     * 
     * @param icon Aluksi näytettävä ikoni.
     */
    public GraafNoppa(NoppaIconMaker ikonit, int alkuSilma) {
        super();
        this.ikonit = ikonit;
        super.setIcon(ikonit.getIcon(alkuSilma));
        super.setMaximumSize(new Dimension(80, 80));
    }

    /**
     * Asettaa noppaan parametrina annetun ikonin.
     * 
     * @param icon 
     */
    public void uusiNoppa(int silmaluku) {
        super.setIcon(ikonit.getIcon(silmaluku));
    }
    
    public void aktivoi() {
        super.setSelected(false);
    }
}
