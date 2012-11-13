package logiikka;

import java.util.*;
import yatzy.Kentta;
import yatzy.Pelaaja;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Peli {
    
    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Noppa> nopat;
    private Pelaaja vuorossaOleva;
    
    public Peli() {
        this.pelaajat = new ArrayList<>();
        this.nopat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.nopat.add(new Noppa());
        }
    }
    
    public void aloita() {
        while(true) {
            uusiKierros();
            if (peliLoppu()) {
                break;
            }
        }
    }
    
    public void uusiKierros() {
        for (Pelaaja p : pelaajat) {
            // Pelaajan vuoron aloitus            
            vuorossaOleva = p;
            heitaNopat(Arrays.asList(1,2,3,4,5));
            // Valitaan heitettävät nopat tai kenttä johon tallennetaan
            
            
            //
            
        }
        
    }
    
    public List<Noppa> heitaNopat(List<Integer> heitettavat) {
        for (int n : heitettavat) {
            nopat.get(n - 1).heita();
        }
        return nopat;
    }
    
    public List<Noppa> heitaKaikkiNopat() {
        return heitaNopat(Arrays.asList(1,2,3,4,5));
    }
    
    public void kirjaaPisteet(Pelaaja nytVuorossa, Kentta mihinKenttaan, List<Noppa> kirjattavat) {
        int pisteet = 0;
        if (mihinKenttaan == Kentta.YKKOSET) {       // ykköset
            pisteet = laskeSumma(kirjattavat, 1);
            nytVuorossa.asetaPisteet(pisteet, Kentta.YKKOSET);
        } else if (mihinKenttaan == Kentta.KAKKOSET) {  // kakkoset
            pisteet = laskeSumma(kirjattavat, 2);
            nytVuorossa.asetaPisteet(pisteet, Kentta.KAKKOSET);
        } else if (mihinKenttaan == Kentta.KOLMOSET) {   // kolmoset
            pisteet = laskeSumma(kirjattavat, 3);
            nytVuorossa.asetaPisteet(pisteet, Kentta.KOLMOSET);
        } else if (mihinKenttaan ==  Kentta.NELOSET) {  // neloset
            pisteet = laskeSumma(kirjattavat, 4);
            nytVuorossa.asetaPisteet(pisteet, Kentta.NELOSET);
        } else if (mihinKenttaan == Kentta.VITOSET) {  // vitoset
            pisteet = laskeSumma(kirjattavat, 5); 
            nytVuorossa.asetaPisteet(pisteet, Kentta.VITOSET);
        } else if (mihinKenttaan == Kentta.KUTOSET) {  // kutoset
            pisteet = laskeSumma(kirjattavat, 6);
            nytVuorossa.asetaPisteet(pisteet, Kentta.KUTOSET);
        } else if (mihinKenttaan == Kentta.PARI) {  // pari
            pisteet = laskeSamojenSumma(kirjattavat, 2);
            nytVuorossa.asetaPisteet(pisteet, Kentta.PARI);
        } else if (mihinKenttaan == Kentta.KAKSIPARIA) {  // kaksi paria
            Map<Integer, Integer> yhdistelmat = ryhmitteleLuvunMukaan(kirjattavat);
            int laskuri = 0;
            for (int i : yhdistelmat.keySet()) {
                if (yhdistelmat.get(i) == 2) {
                    laskuri++;
                    pisteet += i * yhdistelmat.get(i);
                }
            }
            if (laskuri < 2) {
                pisteet = 0;
            }
            nytVuorossa.asetaPisteet(pisteet, Kentta.KAKSIPARIA);
            
        } else if (mihinKenttaan == Kentta.KOLMESAMAA) { // kolme samaa
            pisteet = laskeSamojenSumma(kirjattavat, 3);
            nytVuorossa.asetaPisteet(pisteet, Kentta.KOLMESAMAA);
        } else if (mihinKenttaan == Kentta.NELJASAMAA) { // neljä samaa
            pisteet = laskeSamojenSumma(kirjattavat, 4);
            nytVuorossa.asetaPisteet(pisteet, Kentta.NELJASAMAA);
        } else if (mihinKenttaan == Kentta.PIENISUORA) {  // pieni suora
            pisteet = laskeSuora(kirjattavat, 1, 5);
            nytVuorossa.asetaPisteet(pisteet, Kentta.PIENISUORA);
        } else if (mihinKenttaan == Kentta.SUURISUORA) {  // suuri suora
            pisteet = laskeSuora(kirjattavat, 2, 6);
            nytVuorossa.asetaPisteet(pisteet, Kentta.SUURISUORA);
        } else if (mihinKenttaan == Kentta.TAYSKASI) {  // täyskäsi
            pisteet = laskeSamojenSumma(kirjattavat, 3);
 
            Map<Integer,Integer> yhdistelmat = ryhmitteleLuvunMukaan(kirjattavat);
            if (yhdistelmat.values().contains(2)) {
                for (int i : yhdistelmat.keySet()) {
                    if (yhdistelmat.get(i) == 2) {
                        pisteet += i * 2;
                    }
                }
            }
            nytVuorossa.asetaPisteet(pisteet, Kentta.TAYSKASI);
        } else if (mihinKenttaan == Kentta.SATTUMA) {  // sattuma
            for (Noppa n : kirjattavat) {
                pisteet += n.getLuku();
            }
            nytVuorossa.asetaPisteet(pisteet, Kentta.SATTUMA);
        } else if (mihinKenttaan == Kentta.YATZY) {  // yahtzee
            pisteet = laskeSamojenSumma(kirjattavat, 5);
            if (pisteet > 0) {
                pisteet = 50;
            }
            nytVuorossa.asetaPisteet(pisteet, Kentta.YATZY);
        }
        laskeBonusPisteet(nytVuorossa);
        
        
    }
    
    public boolean peliLoppu() {
        boolean palaute = false;
        for (Pelaaja p : pelaajat) {
            if (p.getPisteet().size() == 17) {
                palaute = true;
        
            }
        }
        return palaute;
    }
    
    public void addPelaaja(String pelaaja) {
        pelaajat.add(new Pelaaja(pelaaja));
    }
    
    public List<Pelaaja> getPelaajat() {
        return pelaajat;
    }
    
    public List<Noppa> getNopat() {
        return nopat;
    }
    public List<Integer> getSilmaluvut() {
        ArrayList<Integer> palaute = new ArrayList<>();
        for (Noppa n : nopat) {
            palaute.add(n.getLuku());
        }
        return palaute;
    }
    
    public Pelaaja getVuorossaOleva() {
        return vuorossaOleva;
    }
    
    protected int laskeSumma(List<Noppa> nList, int mitaHaetaan) {
        int palaute = 0;
        for (Noppa n : nList) {
            if (n.getLuku() == mitaHaetaan) {
                palaute += mitaHaetaan;
            }
        }
        return palaute;
    }
    
    protected int laskeSamojenSumma(List<Noppa> nList, int montakoSamaa) {
        Map<Integer,Integer> ryhmitelty = ryhmitteleLuvunMukaan(nList);
        
        ArrayList<Integer> vaihtoehdot = new ArrayList<>();
        vaihtoehdot.add(0);
        for (int i : ryhmitelty.keySet()) {
            if (ryhmitelty.get(i) >=  montakoSamaa) {
                vaihtoehdot.add(i * montakoSamaa);
            }
        }
        return Collections.max(vaihtoehdot);

    }
    
    protected Map<Integer, Integer> ryhmitteleLuvunMukaan(List<Noppa> nList) {
        ArrayList<Integer> silmaluvut = new ArrayList<>();
        
        for (Noppa n : nList) {
            silmaluvut.add(n.getLuku());
        }
        
        HashMap<Integer, Integer> palaute = new HashMap<>();
        int apulainen;
        
        for (int i = 1; i < 7; i++) {
            palaute.put(i, 0);
            while (silmaluvut.contains(i)) {
                apulainen = palaute.get(1);
                palaute.put(1, apulainen + 1);
                silmaluvut.remove(i);
            }
        }
        return palaute;
    }
    
    protected int laskeSuora(List<Noppa> nList, int mista, int mihin) {
        Map<Integer,Integer> vaihtoehdot = ryhmitteleLuvunMukaan(nList);
        ArrayList<Integer> silmaluvut = new ArrayList<>();
        for (int i : vaihtoehdot.keySet()) {
            if (vaihtoehdot.get(i) > 1) {
                return 0;
            } else if (vaihtoehdot.get(i) == 1) {
                silmaluvut.add(i);
            } else {
                silmaluvut.add(-1);
            }
        }
        if (Collections.min(silmaluvut) > mihin ||
                Collections.max(silmaluvut) < mista) {
            return 0;
        }
        
        
        int palaute = 0;
        for (int i = mista; i <= mihin; i++) {
            palaute += i;
        }
        return palaute;
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
