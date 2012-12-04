package gui;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Luo ikonit nopille kuvista.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class NoppaIconMaker {
    
    private List<Icon> kuvat;
   
    /**
     * Konstruktori luo kuvat.
     * 
     * @throws Exception Poikkeus jos kuvien lukeminen tiedostosta epäonnistuu.
     */
    public NoppaIconMaker() throws Exception {
        this.kuvat = teeKuvat();
    }
    
    /**
     * Palauttaa halutun noppaikonin.
     * 
     * @param luku Silmäluku jota vastaava ikoni palautetaan.
     * @return Palautettava noppaikoni.
     */
    public Icon getIcon(int luku) {
        if (luku == 1) {
            return kuvat.get(0);
        } else if (luku == 2) {
            return kuvat.get(1);
        } else if (luku == 3) {
            return kuvat.get(2);
        } else if (luku == 4) {
            return kuvat.get(3);
        } else if (luku == 5) {
            return kuvat.get(4);
        } else if (luku == 6) {
            return kuvat.get(5);
        } else {
            return null;
        }
    }
    
    /**
     * Luo ikonit kuvista konstruktorin käskystä.
     * 
     * @return Ikonit listassa.
     * @throws Exception Poikkeus, jos kuvien luku tiedostosta epäonnistuu.
     */
    private List<Icon> teeKuvat() throws Exception {
        ArrayList<Icon> palaute = new ArrayList<>();
        BufferedImage img;
        InputStream is;
        
        is = getClass().getResourceAsStream("kuvat/yksi.png");
        img = ImageIO.read(is);
        palaute.add(new ImageIcon(img));
        
        is = getClass().getResourceAsStream("kuvat/kaksi.png");
        img = ImageIO.read(is);
        palaute.add(new ImageIcon(img));
        
        is = getClass().getResourceAsStream("kuvat/kolme.png");
        img = ImageIO.read(is);
        palaute.add(new ImageIcon(img));
        
        is = getClass().getResourceAsStream("kuvat/nelja.png");
        img = ImageIO.read(is);
        palaute.add(new ImageIcon(img));
        
        is = getClass().getResourceAsStream("kuvat/viisi.png");
        img = ImageIO.read(is);
        palaute.add(new ImageIcon(img));
        
        is = getClass().getResourceAsStream("kuvat/kuusi.png");
        img = ImageIO.read(is);
        palaute.add(new ImageIcon(img));
        
        return palaute;
    }
}
