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
     * Konstruktori saa parametrina ikonin luontiolion.
     * 
     * @param ikonit Ikonit sisältävä olio.
     */
    public GraafNoppa(NoppaIconMaker ikonit) {
        super();
        this.ikonit = ikonit;
        super.setIcon(ikonit.getIcon(1));
        super.setMaximumSize(new Dimension(80, 80));
    }

    /**
     * Asettaa noppaan silmälukua vastaavan ikonin
     * 
     * @param silmaluku Silmäluku jota vastaava ikoni asetetaan näkyville. 
     */
    public void uusiNoppa(int silmaluku) {
        super.setIcon(ikonit.getIcon(silmaluku));
    }
    
    /**
     * Palauttaa nopan alkutilaan heiton jälkeen.
     */
    public void aktivoi() {
        super.setSelected(false);
    }
    
    /**
     * Metodi, jonka avulla noppa deaktivoidaan kun heittoja on kolme.
     */
    public void passivoi() {
        super.setEnabled(false);
    }
}
