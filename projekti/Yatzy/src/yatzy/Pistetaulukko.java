package yatzy;

import java.util.EnumMap;
import java.util.Map;

/**
 * Pistetaulukko on yleinen taulukkoluokka pisteiden säilömiseen enumeraatioiden
 * avulla. Pistetaulukkoon voidaan asettaa arvoja ja kysyä niitä. Se tietää myös
 * summa-kentän.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class Pistetaulukko {

    private EnumMap<Kentta, Integer> pistetaulukko;

    public Pistetaulukko() {
        this.pistetaulukko = new EnumMap<>(Kentta.class);
    }

    /**
     * Metodi asettaa parametrina tulleet pisteet parametrina
     * saatavaan kenttään
     * 
     * @param pisteet Tallennettavat pisteet.
     * @param kentta Kenttä, johon pisteet tallennetaan.
     */
    public void asetaPisteet(int pisteet, Kentta kentta) {
        pistetaulukko.put(kentta, pisteet);
    }

    /**
     * Metodi palauttaa pistetaulukon Kentta - pisteet -kuvauksena.
     * 
     * @return Pistetaulukko Kentta-Integer-kuvauksena.
     */
    public Map<Kentta, Integer> getPisteet() {
        return pistetaulukko;
    }

    /**
     * Apumetodi, joka palauttaa pelkästään pisteiden yhteissumman.
     * 
     * @return summa integer-arvona.
     */
    public int getSumma() {
        int palaute = 0;
        for (Kentta i : pistetaulukko.keySet()) {
            if (i != Kentta.VIRHE && i != Kentta.BONUS && i != Kentta.SUMMA) {
                palaute += pistetaulukko.get(i);
            }
        }
        return palaute;
    }

    /**
     * Tulostaa pisteet Kentta: pisteet -muodossa.
     * 
     * @return Pisteet tekstinä.
     */
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
