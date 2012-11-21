package yatzy;

import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Pistetaulukko {

    private EnumMap<Kentta, Integer> pistetaulukko;

    public Pistetaulukko() {
        this.pistetaulukko = new EnumMap<>(Kentta.class);
    }

    public void asetaPisteet(int pisteet, Kentta kentta) {
        pistetaulukko.put(kentta, pisteet);
    }

    public Map<Kentta, Integer> getPisteet() {
        return pistetaulukko;
    }

    public int getSumma() {
        int palaute = 0;
        for (Kentta i : pistetaulukko.keySet()) {
            if (i != Kentta.VIRHE && i != Kentta.BONUS && i != Kentta.SUMMA) {
                palaute += pistetaulukko.get(i);
            }
        }
        return palaute;
    }

    @Override
    public String toString() {
        StringBuilder palaute = new StringBuilder();
        for (Kentta k : pistetaulukko.keySet()) {
            palaute.append(k);
            palaute.append(": ");
            palaute.append(pistetaulukko.get(k));
            palaute.append("\n");
        }
        return palaute.toString();
    }
}
