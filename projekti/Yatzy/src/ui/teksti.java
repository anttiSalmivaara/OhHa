
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
    Map<Kentta, String> otsikko;
    
    public teksti() {
        lukija = new Scanner(System.in);
        peli = new Peli();
        otsikko = luoOtsikko();
    }
        
    @Override
    public void kaynnista() {
        
        System.out.println(nopat.alkuNopat());
        
        String nimi;
        int i = 1;
        while(true) {
            System.out.println("Pelaajan " + i + " nimimerkki (max 6 merkkiä): ");
            nimi = lukija.nextLine();
            if (nimi.isEmpty()) {
                break;
            }
            else {
                peli.addPelaaja(nimi);
            }
            i++;
        }
        //System.out.flush();

        while (!peli.peliLoppu()) {
            
            tulostaTaulukko();
            nopat.tulostaNopat(peli.getNopat());
            
            ArrayList<Integer> noppanumerot = new ArrayList<Integer>();
            String[] noppasyote;
            String syote = "z";
            int q = 1;
            while (q < 4) {
                while (!(syote.equals("u") || syote.equals("k"))) {
                    System.out.println("Uudelleenheitto vai sijoitus kenttään? (u/k)");
                    syote = lukija.nextLine();
                
                    if (syote.equals("u")) {
                        System.out.println("Mitkä nopat? (1 - 5, pilkulla erotettuna)");
                        syote = lukija.nextLine();
                        noppasyote = syote.split(",");
                        for (String j : noppasyote) {
                            noppanumerot.add(Integer.parseInt(j.trim()));
                        }
                        peli.heitaNopat(noppanumerot);
                        tulostaNopat();
                    }
                }    
                q++;
                
            }
            System.out.println("Mihin kenttään?");
            syote = lukija.nextLine();
            peli.kirjaaPisteet(peli.getVuorossaOleva(), Integer.parseInt(syote), peli.getNopat());
            
            peli.uusiKierros();
            
        }
        System.out.println(nopat.alkuNopat());
        System.out.println("Kiitos pelaamisesta!");
        
    }
    @Override
    public void tulostaNopat() {
        System.out.println(nopat.tulostaNopat(peli.getNopat()));
    }
    
    @Override
    public void tulostaTaulukko() {
        //StringBuilder sb = new StringBuilder();
        System.out.println(valiviiva);
        System.out.print("|        |");
        luoOtsikko();
        System.out.println(valiviiva);
        for (Pelaaja p : peli.getPelaajat()) {
            System.out.print("| ");
            System.out.print(p.getNimi());
            for (int i = 0; i < 7 - p.getNimi().length(); i++) {
                System.out.print(" ");
            }
            for (Kentta k : otsikko.keySet()) {
                System.out.print("| ");
                if (p.getPisteet().containsKey(k)) {
                    System.out.print(p.getPisteet().get(k));
                    for (int i = 0; i < otsikko.get(k).length() - p.getPisteet().get(k).toString().length() - 1; i++) {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("|");

        }
    }
    private String valiviiva = "----------------------------------------------------------------------------------------------------------------------------------- ";
    
    private Map<Kentta,String> luoOtsikko() {
        EnumMap <Kentta, String> otsikko;
        otsikko = new EnumMap<>(Kentta.class);
        otsikko.put(Kentta.YKKOSET,    " 1 (1) ");
        otsikko.put(Kentta.KAKKOSET,   " 2 (2) ");
        otsikko.put(Kentta.KOLMOSET,   " 3 (3) ");
        otsikko.put(Kentta.NELOSET,    " 4 (4) ");
        otsikko.put(Kentta.VITOSET,    " 5 (5) ");
        otsikko.put(Kentta.KUTOSET,    " 6 (6) ");
        otsikko.put(Kentta.BONUS,      " Bonus ");
        otsikko.put(Kentta.PARI,       " Pari      (7)");
        otsikko.put(Kentta.KAKSIPARIA, " 2 Paria  (8) ");
        otsikko.put(Kentta.KOLMESAMAA, " 3 Samaa  (9) ");
        otsikko.put(Kentta.NELJASAMAA, " 4 Samaa (10) ");
        otsikko.put(Kentta.PIENISUORA, " suora   (11) ");
        otsikko.put(Kentta.SUURISUORA, " SUORA   (12) ");
        otsikko.put(Kentta.TAYSKASI,   " tKäsi   (13) ");
        otsikko.put(Kentta.SATTUMA,    " Sattuma (14) ");
        otsikko.put(Kentta.YATZY,      " YATZY   (15) ");
        otsikko.put(Kentta.SUMMA,      " Summa        ");
        
        return otsikko;
    }
    public void tulostaOtsikko() {
        for (Kentta k : otsikko.keySet()) {
            System.out.println(otsikko.get(k) + "|");
        }
    }
}
