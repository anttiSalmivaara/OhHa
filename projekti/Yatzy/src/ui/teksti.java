
package ui;
import logiikka.*;
import yatzy.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class teksti implements Kayttoliittyma {
    
    private Scanner lukija;
    private Peli peli;
    private PiirraNopat nopat;
    
    public teksti() {
        lukija = new Scanner(System.in);
        peli = new Peli();
    }
        
    @Override
    public void kaynnista() {
        String nimi;
        while(true) {
            System.out.println("Pelaajan 1 nimimerkki (max 6 merkkiä): ");
            nimi = lukija.nextLine();
            if (nimi.isEmpty()) {
                break;
            }
            else {
                peli.addPelaaja(nimi);
            }
        }
        while (!peli.peliLoppu()) {
            
            tulostaTaulukko();
            tulostaNopat();
            peli.uusiKierros();
        }
        
    }
    @Override
    public void tulostaNopat() {
        
    }
    @Override
    public void tulostaTaulukko() {
        StringBuilder sb = new StringBuilder();
        System.out.println(valiviiva);
        System.out.print("|        |");
        luoOtsikko();
        System.out.println(valiviiva);
        for (Pelaaja p : peli.getPelaajat()) {
            if (p.getNimi().length() < 6) {
                System.out.print("");
            }
            System.out.println(p.getNimi());
            for (Enum e : Kentta.values()) {
                
            }

        }
    }
    private String valiviiva = "----------------------------------------------------------------------------------------------------------------------------------- ";
    
    private void luoOtsikko() {
        HashMap <Kentta, String> otsikko = new HashMap<Kentta, String>();
        otsikko.put(Kentta.YKKOSET, "  1  ");
        otsikko.put(Kentta.KAKKOSET, "   2  ");
        otsikko.put(Kentta.KOLMOSET, "  3  ");
        otsikko.put(Kentta.NELOSET, "  4  ");
        otsikko.put(Kentta.VITOSET, "  5  ");
        otsikko.put(Kentta.KUTOSET, "  6  ");
        otsikko.put(Kentta.BONUS, " Bonus ");
        otsikko.put(Kentta.PARI, " Pari ");
        otsikko.put(Kentta.KAKSIPARIA, " 2 Paria ");
        otsikko.put(Kentta.KOLMESAMAA, " 3 Samaa ");
        otsikko.put(Kentta.NELJASAMAA, " 4 Samaa ");
        otsikko.put(Kentta.PIENISUORA, " suora ");
        otsikko.put(Kentta.SUURISUORA, " SUORA ");
        otsikko.put(Kentta.TAYSKASI, " Täyskäsi ");
        otsikko.put(Kentta.SATTUMA, " Sattuma ");
        otsikko.put(Kentta.YATZY, " YATZY ");
        otsikko.put(Kentta.SUMMA, " Summa ");
        
        for (Kentta k : otsikko.keySet()) {
            System.out.println(otsikko.get(k) + "|");
        }
        
        
    }
}
