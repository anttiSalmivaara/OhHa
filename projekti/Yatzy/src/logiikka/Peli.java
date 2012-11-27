package logiikka;

import java.util.*;
import yatzy.Kentta;
import yatzy.Pelaaja;

/**
 * Peli-luokka sisältää Yatzy-pelin toiminallisuuden.
 * 
 * Luokka huolehtii pelaajien lisäämisestä, pisteiden kirjaamisesta ja 
 * välittää nopanheittokäskyt nopille.
 * 
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class Peli {

    private ArrayList<Pelaaja> pelaajat;
    private HashMap<Integer, Noppa> nopat;

    public Peli() {
        this.pelaajat = new ArrayList<>();
        this.nopat = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            this.nopat.put(i, new Noppa());
        }
    }
    
    /**
     * Metodi heittää nopat.
     * 
     * Metodi ottaa vastaan listan kokonaislukuja, jotka vastaavat noppa-olioita.
     * Metodi palauttaa kuitenkin kaikki nopat Integer-Noppa-kuvauksena, jossa
     * Integer vastaa nopan järjestysnumeroa.
     * 
     * @param heitettavat Lista kokonaislukuja, joka kertoo, mitkä nopat heitetään.
     * @return Integer-Noppa-kuvaus, joka sisältää kaikki nopat.
     */
    public Map<Integer, Noppa> heitaNopat(List<Integer> heitettavat) {
        for (int n : heitettavat) {
            nopat.get(n).heita();
        }
        return nopat;
    }

    /**
     * Apumetodi joka heittää suoraan kaikki nopat.
     * 
     * @return Integer-Noppa-kuvaus, joka sisältää kaikki nopat.
     */
    public Map<Integer, Noppa> heitaKaikkiNopat() {
        return heitaNopat(Arrays.asList(1, 2, 3, 4, 5));
    }

    /**
     * Metodi kirjaa pisteet vuorossa olevalle pelaajalle.
     * 
     * Metodi tarkistaa, mihin kenttään pelaaja haluaa pisteet tallentaa. Sen
     * jälkeen metodi käy läpi nopat, ja huolehtii oikean pistemäärän
     * muodostamisesta, jonka jälkeen arvo tallennetaan pelaajan pistetaulukkoon,
     * mikäli kenttä ei ole jo käytössä. Sen jälkeen katsotaan, täyttyykö 
     * yläkerran bonuspiste-ehto ja lopuksi pisteiden summa tallennetaan
     * pistetaulukkoon.
     * 
     * @param nytVuorossa Pelaaja-olio joka kertoo vuorossa olevan pelaajan.
     * @param mihinKenttaan Pelaajan valitsema kenttä, johon pisteet tallennetaan.
     * @param kirjattavatNopat Collection-kokoelma Noppia, joista arvo lasketaan. 
     * @throws Exception Metodi heittää poikkeuksen, jos valittu kenttä on jo käytössä.
     */
    public void kirjaaPisteet(Pelaaja nytVuorossa,
            Kentta mihinKenttaan,
            Collection<Noppa> kirjattavatNopat) throws Exception {
        int pisteet = 0;   
        if (mihinKenttaan == Kentta.YKKOSET) {       // ykköset
            pisteet = laskeSumma(kirjattavatNopat, 1);
        } else if (mihinKenttaan == Kentta.KAKKOSET) {  // kakkoset
            pisteet = laskeSumma(kirjattavatNopat, 2);
        } else if (mihinKenttaan == Kentta.KOLMOSET) {   // kolmoset
            pisteet = laskeSumma(kirjattavatNopat, 3);
        } else if (mihinKenttaan == Kentta.NELOSET) {  // neloset
            pisteet = laskeSumma(kirjattavatNopat, 4);
        } else if (mihinKenttaan == Kentta.VITOSET) {  // vitoset
            pisteet = laskeSumma(kirjattavatNopat, 5);
        } else if (mihinKenttaan == Kentta.KUTOSET) {  // kutoset
            pisteet = laskeSumma(kirjattavatNopat, 6);
        } else if (mihinKenttaan == Kentta.PARI) {  // pari
            pisteet = laskeSamojenSumma(kirjattavatNopat, 2);
        } else if (mihinKenttaan == Kentta.KAKSIPARIA) {  // kaksi paria
            
            int pisteetEkaKierros = laskeSamojenSumma(kirjattavatNopat, 2);
            int muistiSilmaLuku = pisteetEkaKierros / 2;
            
            ArrayList<Noppa> toinenKierros = new ArrayList<>();
            int laskuri = 0;
            
            for (Noppa n : kirjattavatNopat) {
                if (n.getLuku() == muistiSilmaLuku && laskuri < 2) {
                    laskuri++;
                } else {
                    toinenKierros.add(n);
                }
            }
            int pisteetTokaKierros = laskeSamojenSumma(toinenKierros, 2);
            
            if (pisteetEkaKierros > 0 && pisteetTokaKierros > 0) {
                pisteet = pisteetEkaKierros + pisteetTokaKierros;
            }
        } else if (mihinKenttaan == Kentta.KOLMESAMAA) { // kolme samaa
            pisteet = laskeSamojenSumma(kirjattavatNopat, 3);
        } else if (mihinKenttaan == Kentta.NELJASAMAA) { // neljä samaa
            pisteet = laskeSamojenSumma(kirjattavatNopat, 4);
        } else if (mihinKenttaan == Kentta.PIENISUORA) {  // pieni suora
            pisteet = laskeSuora(kirjattavatNopat, 1, 5);
        } else if (mihinKenttaan == Kentta.SUURISUORA) {  // suuri suora
            pisteet = laskeSuora(kirjattavatNopat, 2, 6);
        } else if (mihinKenttaan == Kentta.TAYSKASI) {  // täyskäsi
            int pisteetEkaKierros = laskeSamojenSumma(kirjattavatNopat, 3);
            int muistiSilmaLuku = pisteetEkaKierros / 3;
            ArrayList<Noppa> toinenKierros = new ArrayList<>();
            int laskuri = 0;
            for (Noppa n : kirjattavatNopat) {
                if (n.getLuku() == muistiSilmaLuku && laskuri < 3) {
                    laskuri++;
                } else {
                    toinenKierros.add(n);
                }
            }
            int pisteetTokaKierros = laskeSamojenSumma(toinenKierros, 2);
            if (pisteetEkaKierros > 0 && pisteetTokaKierros > 0) {
                pisteet = pisteetEkaKierros + pisteetTokaKierros;
            }
        } else if (mihinKenttaan == Kentta.SATTUMA) {  // sattuma
            for (Noppa n : kirjattavatNopat) {
                pisteet += n.getLuku();
            }
        } else if (mihinKenttaan == Kentta.YATZY) {  // yahtzee
            pisteet = laskeSamojenSumma(kirjattavatNopat, 5);
            if (pisteet > 0) {
                pisteet = 50;
            }
        }
        if (nytVuorossa.getPisteet().keySet().contains(mihinKenttaan)) {
            throw new IllegalArgumentException("Kenttä käytössä!");
        }
        nytVuorossa.asetaPisteet(pisteet, mihinKenttaan);
        laskeBonusPisteet(nytVuorossa);
        nytVuorossa.asetaPisteet(nytVuorossa.getSumma(), Kentta.SUMMA);


    }
    
    /**
     * Metodi palauttaa pelitilanteen.
     * 
     * Jos molempien pelaajien kaikki kentät ovat käytössä, palauttaa metodi true, 
     * muuten false.
     * 
     * @return Boolean-arvo joka kertoo, onko peli loppu.
     */
    public boolean peliLoppu() {
        for (Pelaaja p : pelaajat) {
            if (!p.peliLoppu()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodi lisää pelaajan.
     * 
     * @param pelaaja Lisättävä pelaaja.
     */
    public void addPelaaja(String pelaaja) {
        pelaajat.add(new Pelaaja(pelaaja));
    }

    /**
     * Palauttaa listan pelaajista.
     * 
     * @return Pelaajat listana.
     */
    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }

    /**
     * Palauttaa nopat.
     * 
     * @return Nopat Integer-Noppa-kuvauksena.
     */
    public Map<Integer, Noppa> getNopat() {
        return nopat;
    }

    /**
     * Apumetodi kirjaaPisteet-metodille.
     * 
     * Metodi käy läpi nopat ja laskee summan halutuista silmäluvuista.
     * Jos silmälukuja ei ole, palautetaan nolla.
     * 
     * @param noppaColl Nopat Collection-kokoelmana.
     * @param mitaHaetaan Haluttu silmäluku.
     * @return Summa niistä nopista, joissa on haluttu silmäluku.
     */
    protected int laskeSumma(Collection<Noppa> noppaColl, int mitaHaetaan) {
        int palaute = 0;
        for (Noppa n : noppaColl) {
            if (n.getLuku() == mitaHaetaan) {
                palaute += mitaHaetaan;
            }
        }
        return palaute;
    }

    /**
     * Metodi etsii samoja silmälukuja ja palauttaa niiden summan.
     * 
     * 
     * @param noppaColl
     * @param montakoSamaa
     * @return 
     */
    protected int laskeSamojenSumma(Collection<Noppa> noppaColl, int montakoSamaa) {
        Map<Integer, Integer> ryhmitelty = ryhmitteleLuvunMukaan(noppaColl);

        ArrayList<Integer> vaihtoehdot = new ArrayList<>();
        vaihtoehdot.add(0);
        for (int i : ryhmitelty.keySet()) {
            if (ryhmitelty.get(i) >= montakoSamaa) {
                vaihtoehdot.add(i * montakoSamaa);
            }
        }
        Collections.sort(vaihtoehdot);
        Collections.reverse(vaihtoehdot);
        return vaihtoehdot.get(0);

    }

    protected Map<Integer, Integer> ryhmitteleLuvunMukaan(Collection<Noppa> nList) {
        ArrayList<Integer> silmaluvut = new ArrayList<>();

        for (Noppa n : nList) {
            silmaluvut.add(n.getLuku());
        }

        HashMap<Integer, Integer> palaute = new HashMap<>();
        int apulainen;

        for (int i = 1; i < 7; i++) {
            palaute.put(i, 0);
            while (silmaluvut.contains(i)) {
                apulainen = palaute.get(i);
                palaute.put(i, apulainen + 1);
                silmaluvut.remove(new Integer(i));
            }
        }
        return palaute;
    }

    protected int laskeSuora(Collection<Noppa> nList, int mista, int mihin) {
        Map<Integer, Integer> ryhmitelty = ryhmitteleLuvunMukaan(nList);
        ArrayList<Integer> silmaluvut = new ArrayList<>();
        for (int i : ryhmitelty.keySet()) {
            if (ryhmitelty.get(i) > 1) {
                return 0;
            } else if (ryhmitelty.get(i) == 1) {
                silmaluvut.add(i);
            }
        }
        if (Collections.min(silmaluvut) > mista) {
            return 0;
        } else if (Collections.max(silmaluvut) < mihin) {
            return 0;
        } else {
            int palaute = 0;
            for (int i : silmaluvut) {
                palaute += i;
            }
            return palaute;
        }
    }

    protected void laskeBonusPisteet(Pelaaja vuorossa) {
        Map<Kentta, Integer> bonusPisteet = vuorossa.getPisteet();
        int bPisteet = 0;
        Kentta[] ylakerta = {Kentta.YKKOSET, Kentta.KAKKOSET, Kentta.KOLMOSET,
            Kentta.NELOSET, Kentta.VITOSET, Kentta.KUTOSET};
        for (Kentta k : ylakerta) {
            if (bonusPisteet.keySet().contains(k)) {
                bPisteet += bonusPisteet.get(k);
            }
        }
        if (bPisteet >= 63) {
            vuorossa.asetaPisteet(50, Kentta.BONUS);
        }
    }
}
