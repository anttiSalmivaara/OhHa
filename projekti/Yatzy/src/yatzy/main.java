package yatzy;
import ui.Kayttoliittyma;
import ui.Teksti;
import gui.*;
import logiikka.*;
/**
 * Luokka, joka käynnistää pelin.
 * 
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class main {
    
   public static void main(String[] args) {
        Peli peli = new Peli();
        Teksti ui = new Teksti();
        
        ui.kaynnista();
    }
}
