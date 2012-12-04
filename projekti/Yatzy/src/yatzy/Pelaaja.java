package yatzy;

import java.util.Map;
import java.util.Set;

/**
 * Pelaaja-luokka määrittelee pelaajan. Se sisältää nimen, pistetaulukon sekä
 * metodit näiden asettamiseen ja muokkaamiseen. Pelaaja-luokka tietää myös, onko
 * peli loppu.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 * @see Pistetaulukko
 */
public class Pelaaja {
    private Pistetaulukko pistetaulukko;
    private String nimi;
    
    /**
     * Konstruktori luo pistetaulukon sekä tallentaa pelaajan nimen.
     * @param nimi Pelaajan nimi String-oliona.
     */
    public Pelaaja(String nimi) {
        this.pistetaulukko = new Pistetaulukko();
        this.nimi = nimi;
    }
    
    /**
     * Metodi käyttää Pistetaulukko-luokkaa pelaajan välityksellä.
     * 
     * @param pisteet Pistetaulukkoon talletettavat pisteet Integer-arvona.
     * @param kentta Kenttä, johon pisteet tallennetaan Kentta-arvona.
     */
    public void asetaPisteet(int pisteet, Kentta kentta) {
        pistetaulukko.asetaPisteet(pisteet, kentta);
    }
    
    /**
     * Metodi, joka palauttaa pelaajan nimen.
     * 
     * @return Nimi String-arvona.
     */
    public String getNimi() {
        return nimi;
    }
    
    /**
     * Metodi pyytää pistetaulukon Pistetaulukko-luokalta ja palauttaa
     * pisteet Kentta-Integer-kuvauksena.
     * 
     * @return Pisteet Kentta-Integer-kuvauksena.
     */
    public Map<Kentta, Integer> getPisteet() {
        return pistetaulukko.getPisteet();
    }
    
    /**
     * Palauttaa pelaajan tämänhetkisen pistetilanteen Integer-arvona.
     * 
     * @return Pelaajan kokonaispisteet Integer-arvona.
     */
    public int getSumma() {
        return pistetaulukko.getSumma();
    }
    
    /**
     * Kertoo, onko peli loppu tarkistamalla, ovatko kaikki kentät jo käytössä.
     * 
     * @return True, jos peli loppu, false muuten.
     */
    public boolean peliLoppu() {
        Set<Kentta> kentat = pistetaulukko.getPisteet().keySet();
        if ( (kentat.size() == 16 && !kentat.contains(Kentta.BONUS)) ||
                kentat.size() == 17) {
            return true;
        }
        return false;
    }
    
    /**
     * Palauttaa pelaajan ja pisteet String-arvona.
     * 
     * @return Pelaaja ja pisteet, jotka on tallennettu pistetaulukkoon.
     */
    @Override
    public String toString() {
        return nimi + ": \n" + pistetaulukko.toString();
    }
}
