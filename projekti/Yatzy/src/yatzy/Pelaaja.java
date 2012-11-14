package yatzy;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Pelaaja {
    private Pistetaulukko pistetaulukko;
    private String nimi;
    
    public Pelaaja(String nimi) {
        this.pistetaulukko = new Pistetaulukko();
        this.nimi = nimi;
    }
    
    public void asetaPisteet(int pisteet, Kentta kentta) {
        pistetaulukko.asetaPisteet(pisteet, kentta);
    }

    public String getNimi() {
        return nimi;
    }
    public Map getPisteet() {
        return pistetaulukko.getPisteet();
    }
    
    public int getSumma() {
        return pistetaulukko.getSumma();
    }
    
    public boolean peliLoppu() {
        Set<Kentta> kentat = pistetaulukko.getPisteet().keySet();
        if ( (kentat.size() == 15 && !kentat.contains(Kentta.BONUS)) ||
                kentat.size() == 16) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return nimi + ": \n" + pistetaulukko.toString();
    }
}
