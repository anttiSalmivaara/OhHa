package yatzy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Pistetaulukko {
    private HashMap<Kentta, Integer> pistetaulukko;
    
    public Pistetaulukko() {
        this.pistetaulukko = new HashMap<Kentta, Integer>();
    }
    
    public void asetaPisteet(int pisteet, Kentta kentta) {
        pistetaulukko.put(kentta, pisteet);
        laskeBonus();
    }
    
    public Map<Kentta, Integer> getPisteet() {
        return pistetaulukko;
    }
    
    public int getSumma() {
        int palaute = 0;
        for (Kentta i : pistetaulukko.keySet()) {
            palaute += pistetaulukko.get(i);
        }
        return palaute;
    }
    
    @Override
    public String toString() {
        StringBuilder palaute = new StringBuilder();
        for (Kentta k : pistetaulukko.keySet()) {
            palaute.append(k);
            palaute.append(" ");
            palaute.append(pistetaulukko.get(k));
            palaute.append("\n");
        }
        return palaute.toString();
    }
    
    private void laskeBonus() {
        Kentta[] ylakerta = {Kentta.YKKOSET, Kentta.KAKKOSET, Kentta.KOLMOSET,
                            Kentta.NELOSET, Kentta.VITOSET, Kentta.KUTOSET};
        int apusumma = 0;
        
        for (Kentta i : ylakerta) {
            try {
                apusumma += pistetaulukko.get(i);
            } catch (Exception e) {}
        }
        if (apusumma >= 63) {
            pistetaulukko.put(Kentta.BONUS, 50);
        }
    }
    
}
