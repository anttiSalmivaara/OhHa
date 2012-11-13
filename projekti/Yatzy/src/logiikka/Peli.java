package logiikka;

import yatzy.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Peli {
    
    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Noppa> nopat;
    private Pelaaja vuorossaOleva;
    
    public Peli() {
        this.pelaajat = new ArrayList<Pelaaja>();
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
            // Pelaajan vuoron aloitus            
            vuorossaOleva = p;
            heitaNopat(Arrays.asList(1,2,3,4,5));
            // Valitaan heitettävät nopat tai kenttä johon tallennetaan
            
            
            //
            
        }
        
    }
    
    public List<Noppa> heitaNopat(List<Integer> heitettavat) {
        for (int n : heitettavat) {
            nopat.get(n - 1).heita();
        }
        return nopat;
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
    
    public void addPelaaja(String pelaaja) {
        pelaajat.add(new Pelaaja(pelaaja));
    }
    
    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }
    
    public List<Noppa> getNopat() {
        return nopat;
    }
    
    public Pelaaja getVuorossaOleva() {
        return vuorossaOleva;
    }
}
