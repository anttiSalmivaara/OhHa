package ui;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import logiikka.Peli;
import yatzy.Kentta;
import yatzy.Pelaaja;

/**
 * Teksti-luokka on Yatzy-pelin tekstikäyttöliittymä. Se sisältää käynnistä-metodin
 * joka aloittaa pelin, sekä metodit pistetaulukon ja noppien tulostamiseen.
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Teksti implements Kayttoliittyma {

    private Scanner lukija;
    private Peli peli;
    private PiirraNopat nopat;
    private Map<Kentta, String> otsikko;

    public Teksti() {
        this.lukija = new Scanner(System.in);
        this.peli = new Peli();
        this.nopat = new PiirraNopat();
        this.otsikko = luoOtsikko();
    }

    @Override
    public void run() {

        System.out.println(nopat.alkuNopat());

        String nimi;
        int i = 1;
        while (true) {
            System.out.println("Pelaajan " + i + " nimimerkki (max 6 merkkiä)");
            System.out.print(": ");
            nimi = lukija.nextLine();
            if (nimi.isEmpty()) {
                break;
            } else {
                peli.addPelaaja(nimi);
            }
            i++;
        }

        while (!peli.peliLoppu()) {
            for (Pelaaja p : peli.getPelaajat()) {

                tulostaTaulukko();
                peli.heitaKaikkiNopat();
                tulostaNopat();

                String syote = "z";
                int q = 0;

                while (q < 2 && !syote.equals("k")) {

                    System.out.println("Uudelleenheitto vai sijoitus kenttään? (u/k)");
                    System.out.print(p.getNimi() + " : ");
                    syote = lukija.nextLine();

                    if (syote.equals("u")) {
                        System.out.println("Mitkä nopat heitetään? (1 - 5, pilkulla erotettuna)");
                        System.out.print(p.getNimi() + " : ");
                        syote = lukija.nextLine();
                        ArrayList<Integer> noppanumerot = new ArrayList<>();
                        if (syote.length() == 1) {
                            noppanumerot.add(Integer.parseInt(syote));
                        } else {
                            String[] noppasyote = syote.split(",");
                            for (String j : noppasyote) {
                                noppanumerot.add(Integer.parseInt(j.trim()));
                            }
                        }
                        peli.heitaNopat(noppanumerot);
                        tulostaNopat();
                        q++;
                    } else if (syote.equals("k")) {
                        break;
                    } else {
                        System.out.println("Virheellinen syöte! Kokeile uudelleen.");
                    }
                }
                System.out.println("Mihin kenttään?");
                System.out.print(p.getNimi() + " : ");
                syote = lukija.nextLine();
                while (true) {
                    if (getKentta(syote) == Kentta.VIRHE) {
                        System.out.println("Virheellinen syöte! Kokeile uudelleen.");
                        System.out.print(p.getNimi() + " : ");
                        syote = lukija.nextLine();
                    } else {
                        try {
                            peli.kirjaaPisteet(p, getKentta(syote), peli.getNopat().values());
                            break;
                        } catch (Exception e) {
                            System.out.println("Kenttä käytössä! Kokeile uudelleen.");
                            System.out.print(p.getNimi() + " : ");
                            syote = lukija.nextLine();
                        }
                    }
                }
            }

        }
        tulostaTaulukko();
        System.out.println(nopat.alkuNopat());
        System.out.println("Kiitos pelaamisesta!");

    }

    @Override
    public void tulostaNopat() {
        System.out.println(nopat.tulostaNopat(peli.getNopat().values()));
    }

    @Override
    public void tulostaTaulukko() {
        //StringBuilder sb = new StringBuilder();
        System.out.println(ylaviiva);
        System.out.print("│        │");
        tulostaOtsikko();
        System.out.println(valiviiva);
        for (Pelaaja p : peli.getPelaajat()) {
            System.out.print("│ ");
            System.out.print(p.getNimi());
            for (int i = 0; i < 7 - p.getNimi().length(); i++) {
                System.out.print(" ");
            }
            for (Kentta k : otsikko.keySet()) {
                System.out.print("│ ");
                if (p.getPisteet().containsKey(k)) {
                    System.out.print(p.getPisteet().get(k));
                    for (int i = 0; i < otsikko.get(k).length() - p.getPisteet().get(k).toString().length() - 1; i++) {
                        System.out.print(" ");
                    }
                } else {
                    for (int i = 0; i < otsikko.get(k).length() - 1; i++) {
                        System.out.print(" ");
                    }
                }

            }
            System.out.println("│");

        }
        System.out.println(alaviiva);
    }

    protected void tulostaOtsikko() {
        for (Kentta k : otsikko.keySet()) {
            System.out.print(otsikko.get(k) + "│");
        }
        System.out.println("");
    }
    private String ylaviiva = "┌────────┬────┬────┬────┬────┬────┬────┬─────┬───────┬─────────┬──────┬───────┬─────────┬─────────┬─────────┬─────────┬───────┬─────┐";
    private String valiviiva = "├────────┼────┼────┼────┼────┼────┼────┼─────┼───────┼─────────┼──────┼───────┼─────────┼─────────┼─────────┼─────────┼───────┼─────┤";
    private String alaviiva = "└────────┴────┴────┴────┴────┴────┴────┴─────┴───────┴─────────┴──────┴───────┴─────────┴─────────┴─────────┴─────────┴───────┴─────┘";

    protected final Map<Kentta, String> luoOtsikko() {
        EnumMap<Kentta, String> apuOtsikko;
        apuOtsikko = new EnumMap<>(Kentta.class);
        apuOtsikko.put(Kentta.YKKOSET, "1(1)");
        apuOtsikko.put(Kentta.KAKKOSET, "2(2)");
        apuOtsikko.put(Kentta.KOLMOSET, "3(3)");
        apuOtsikko.put(Kentta.NELOSET, "4(4)");
        apuOtsikko.put(Kentta.VITOSET, "5(5)");
        apuOtsikko.put(Kentta.KUTOSET, "6(6)");
        apuOtsikko.put(Kentta.BONUS, "Bonus");
        apuOtsikko.put(Kentta.PARI, "Pari(7)");
        apuOtsikko.put(Kentta.KAKSIPARIA, "2Paria(8)");
        apuOtsikko.put(Kentta.KOLMESAMAA, "3 X(9)");
        apuOtsikko.put(Kentta.NELJASAMAA, "4 X(10)");
        apuOtsikko.put(Kentta.PIENISUORA, "suora(11)");
        apuOtsikko.put(Kentta.SUURISUORA, "SUORA(12)");
        apuOtsikko.put(Kentta.TAYSKASI, "tKäsi(13)");
        apuOtsikko.put(Kentta.YATZY, "YATZY(14)");
        apuOtsikko.put(Kentta.SATTUMA, "Rnd(15)");
        apuOtsikko.put(Kentta.SUMMA, "Summa");

        return apuOtsikko;
    }

    protected Kentta getKentta(String syote) {
        switch (syote) {
            case "1":
                return Kentta.YKKOSET;
            case "2":
                return Kentta.KAKKOSET;
            case "3":
                return Kentta.KOLMOSET;
            case "4":
                return Kentta.NELOSET;
            case "5":
                return Kentta.VITOSET;
            case "6":
                return Kentta.KUTOSET;
            case "7":
                return Kentta.PARI;
            case "8":
                return Kentta.KAKSIPARIA;
            case "9":
                return Kentta.KOLMESAMAA;
            case "10":
                return Kentta.NELJASAMAA;
            case "11":
                return Kentta.PIENISUORA;
            case "12":
                return Kentta.SUURISUORA;
            case "13":
                return Kentta.TAYSKASI;
            case "14":
                return Kentta.YATZY;
            case "15":
                return Kentta.SATTUMA;
            default:
                return Kentta.VIRHE;
        }
    }
}
