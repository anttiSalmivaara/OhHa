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
    }
    
    public Map<Kentta, Integer> getPisteet() {
        return pistetaulukko;
    }
    
    @Override
    public String toString() {
        
    }
    
}
