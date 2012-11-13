package logiikka;

import yatzy.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Peli {
    
    private ArrayList<Pelaaja> pelaajat;
    private ArrayList<Noppa> nopat;
    private Pelaaja vuorossaOleva;
    
    public Peli() {
        this.pelaajat = new ArrayList<Pelaaja>();
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
    
    public void kirjaaPisteet(Pelaaja nytVuorossa, int mihinKenttaan, List<Noppa> kirjattavat) {
        int pisteet = 0;
        if (mihinKenttaan == 1) {
            pisteet = laskeSumma(kirjattavat, 1);
        } else if (mihinKenttaan == 2) {
            pisteet = laskeSumma(kirjattavat, 2);
        } else if (mihinKenttaan == 3) {
            pisteet = laskeSumma(kirjattavat, 3);
        } else if (mihinKenttaan == 4) {
            pisteet = laskeSumma(kirjattavat, 4);
        } else if (mihinKenttaan == 5) {
            pisteet = laskeSumma(kirjattavat, 5);
        } else if (mihinKenttaan == 6) {
            pisteet = laskeSumma(kirjattavat, 6);
        } else if (mihinKenttaan == 7) {
            pisteet = laskeSamojenSumma(kirjattavat, 2);
        } else if (mihinKenttaan == 8) {
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
            
        } else if (mihinKenttaan == 9) {
            pisteet = laskeSamojenSumma(kirjattavat, 3);
        } else if (mihinKenttaan == 10) {
            pisteet = laskeSamojenSumma(kirjattavat, 4);
        } else if (mihinKenttaan == 11) {
            pisteet = laskeSuora(kirjattavat, 1, 5);
        } else if (mihinKenttaan == 12) {
            pisteet = laskeSuora(kirjattavat, 2, 6);
        }
        otsikko.put(Kentta.TAYSKASI,   " tKäsi   (13) ");
        otsikko.put(Kentta.SATTUMA,    " Sattuma (14) ");
        otsikko.put(Kentta.YATZY,      " YATZY   (15) ");
        otsikko.put(Kentta.SUMMA,      " Summa        ");
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
            if (ryhmitelty.get(i) == montakoSamaa) {
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
}
