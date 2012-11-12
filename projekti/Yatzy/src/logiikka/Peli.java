package logiikka;

import yatzy.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Peli {
    
    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Noppa> nopat;
    
    public Peli() {
        this.pelaajat = new ArrayList<Pelaaja>();
        for (int i = 0; i < 5; i++) {
            this.nopat.add(new Noppa());
        }
    }
    
    public void addPelaaja(String pelaaja) {
        pelaajat.add(new Pelaaja(pelaaja));
    }
    
    public List<Pelaaja> getPelaajat() {
        return pelaajat;
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
    
    public boolean peliLoppu() {
        boolean palaute = false;
        for (Pelaaja p : pelaajat) {
            if (p.getPisteet().size() == 17) {
                palaute = true;
        
            }
        }
        return palaute;
    }
}
