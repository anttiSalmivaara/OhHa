package yatzy;

import java.util.Map;

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
    
    public Map getPisteet() {
        return pistetaulukko.getPisteet();
    }
    
    public int getSumma() {
        return pistetaulukko.getSumma();
    }
    
    public String toString() {
        return nimi + ": \n" + pistetaulukko.toString();
    }
}
