package logiikka;

import yatzy.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Peli {
    
    private List<Pelaaja> pelaajat;
    private ArrayList<Noppa> nopat;
    
    public Peli(List<Pelaaja> pelaajat) {
        this.pelaajat = pelaajat;
        for (int i = 0; i < 5; i++) {
            this.nopat.add(new Noppa());
        }
    }
    
    public void aloita() {
        while(true) {
            uusiKierros();
            if (peliLoppu()) {
                break;
            }
        }
    }
    
    public void uusiKierros() {
        for (Pelaaja p : pelaajat) {
            
        }
        
    }
    
    public List<Noppa> heitaNopat(List<Noppa> heitettavat) {
        for (Noppa n : heitettavat) {
            n.heita();
        }
        return heitettavat;
    }
    
    protected boolean peliLoppu() {
        boolean palaute = false;
        for (Pelaaja p : pelaajat) {
            if (p.getPisteet().size() == 17) {
                palaute = true;
        
            }
        }
        return palaute;
    }
}
