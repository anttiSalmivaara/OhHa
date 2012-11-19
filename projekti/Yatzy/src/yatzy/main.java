package yatzy;
import ui.Kayttoliittyma;
import ui.Teksti;
import gui.*;
import logiikka.*;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class main {
    
   public static void main(String[] args) {
        Peli peli = new Peli();
        Graafinen gui = new Graafinen();
        
        gui.run();
    }
}
