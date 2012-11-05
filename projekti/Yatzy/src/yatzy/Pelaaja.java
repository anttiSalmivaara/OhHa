package yatzy;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Pelaaja {
    private int[] pistetaulukko;
    private String nimi;
    
    public Pelaaja(String nimi) {
        this.pistetaulukko = new int[16];
        this.nimi = nimi;
    }
    
    public void asetaPisteet(int pisteet, int kentta) {
        pistetaulukko[kentta] = pisteet;
        if (kentta < 6) {
            laskeBonus();
        }
    }
    
    public 
    
    private void laskeBonus() {
        int apuSumma = 0;
        for (int i = 0; i < 6; i++) {
            apuSumma += pistetaulukko[i];
        }
        if (apuSumma >= 63) {
            pistetaulukko[6] = 50;
        } 
    }
}
