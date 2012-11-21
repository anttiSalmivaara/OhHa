/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import yatzy.Kentta;
import yatzy.Pelaaja;
import yatzy.Pistetaulukko;

/**
 *
 * @author antti
 */
public class PeliTest {

    private Peli peli;

    public PeliTest() {
    }

    @Before
    public void setUp() {
        this.peli = new Peli();
    }

    /**
     * Test of heitaNopat method, of class Peli.
     */
    @Test
    public void testHeitaNopat() {
        ArrayList<Integer> heitettavat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            heitettavat.add(i);
        }
        Map result = peli.heitaNopat(heitettavat);
        boolean b = true;
        for (Iterator it = result.values().iterator(); it.hasNext();) {
            Noppa n = (Noppa) it;
            b = 0 < n.getLuku() && 7 > n.getLuku();
        }
        assertEquals(true, b);
    }

    @Test
    public void testHeitaNopat1000Kertaa() {
        boolean b = true;
        for (int j = 0; 1 < 1000; j++) {
            ArrayList<Integer> heitettavat = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                heitettavat.add(i);
            }
            Map result = peli.heitaNopat(heitettavat);
            for (Iterator it = result.values().iterator(); it.hasNext();) {
                Noppa n = (Noppa) it;
                b = 0 < n.getLuku() && 7 > n.getLuku();
            }
            assertEquals(true, b);
        }
    }

    /**
     * Test of heitaKaikkiNopat method, of class Peli.
     */
    @Test
    public void testHeitaKaikkiNopat() {

        Map<Integer, Noppa> result = peli.heitaKaikkiNopat();
        boolean b = true;
        for (int i = 1; i < 6; i++) {
            b = b && result.get(i).getLuku() < 7 && result.get(i).getLuku() > 0;
        }
        assertEquals(true, b);
    }

    @Test
    public void testHeitaKaikkiNopat1000Kertaa() {
        boolean b = true;
        for (int j = 0; 1 < 1000; j++) {
            Map<Integer, Noppa> result = peli.heitaKaikkiNopat();
            for (int i = 1; i < 6; i++) {
                b = b && result.get(i).getLuku() < 7 && result.get(i).getLuku() > 0;
            }
        }
        assertTrue(b);
    }

    /**
     * Test of kirjaaPisteet method, of class Peli.
     */
    @Test
    public void testKirjaaPisteet() throws Exception {
        peli.addPelaaja("testPelaaja");
        Pelaaja pelaaja = peli.getPelaajat().get(0);
        peli.heitaKaikkiNopat();
        Collection<Noppa> kirjattavat = peli.getNopat().values();
        peli.kirjaaPisteet(pelaaja, Kentta.SATTUMA, kirjattavat);
        // TODO review the generated test code and remove the default call to fail.
        int summa = 0;
        for (Noppa n : kirjattavat) {
            summa += n.getLuku();
        }
        assertEquals(peli.getPelaajat().get(0).getPisteet().get(Kentta.SATTUMA), summa);
    }

    @Test
    public void testKirjaaPisteetKaikkiinKenttiin() throws Exception {
        peli.addPelaaja("testPelaaja");
        Pelaaja pelaaja = peli.getPelaajat().get(0);
        Collection<Noppa> kirjattavat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            kirjattavat.add(new HuonoNoppa(6));
        }

        for (Kentta k : Kentta.values()) {
            if (k != Kentta.VIRHE && k != Kentta.SUMMA && k != Kentta.BONUS) {
                peli.kirjaaPisteet(pelaaja, k, kirjattavat);
            }
        }
        assertEquals(peli.getPelaajat().get(0).getSumma(), 218);
    }

    /**
     * Test of peliLoppu method, of class Peli.
     */
    @Test
    public void testPeliLoppu() throws Exception {
        peli.addPelaaja("testPelaaja");
        Pelaaja pelaaja = peli.getPelaajat().get(0);
        Collection<Noppa> kirjattavat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            kirjattavat.add(new HuonoNoppa(6));
        }

        for (Kentta k : Kentta.values()) {
            if (k != Kentta.VIRHE && k != Kentta.SUMMA && k != Kentta.BONUS) {
                peli.kirjaaPisteet(pelaaja, k, kirjattavat);
            }
        }
        assertTrue(peli.peliLoppu());
    }

    /**
     * Test of addPelaaja method, of class Peli.
     */
    @Test
    public void testAddPelaaja() {
        peli.addPelaaja("pelaaja");
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(!peli.getPelaajat().isEmpty() && peli.getPelaajat().get(0).getNimi().equals("pelaaja"));
    }

    @Test
    public void testAdd1000Pelaajaa() {
        for (int i = 0; i < 1000; i++) {
            peli.addPelaaja("" + i);
        }
        assertEquals(peli.getPelaajat().size(), 1000);
    }

    /**
     * Test of getPelaajat method, of class Peli.
     */
    @Test
    public void testGetPelaajat() {
        peli.addPelaaja("yksi");
        assertEquals(peli.getPelaajat().get(0).getNimi(), "yksi");
        // TODO review the generated test code and remove the default call to fail.
    }

    @Test
    public void testGet1000Pelaajaa() {
        for (int i = 0; i < 1000; i++) {
            peli.addPelaaja("" + i);
        }
        assertEquals(peli.getPelaajat().size(), 1000);
    }

    @Test
    public void testAdd1000PelaajaaGetNimiOikein() {
        for (int i = 0; i < 1000; i++) {
            peli.addPelaaja("" + i);
        }
        assertEquals(peli.getPelaajat().get(999).getNimi(), "999");
    }

    /**
     * Test of getNopat method, of class Peli.
     */
    @Test
    public void testGetNopatViisiNoppaa() {
        Map result = peli.getNopat();
        assertTrue(result.size() == 5);
    }

    @Test
    public void testGetNopaEiSilmalukuaEnnenHeittoa() {
        Map result = peli.getNopat();
        boolean b = true;
        for (Object o : result.values()) {
            Noppa n = (Noppa) o;
            b = b && n.getLuku() == 0;
        }
        assertTrue(b);
    }

    /**
     * Test of laskeSumma method, of class Peli.
     */
    @Test
    public void testLaskeSumma() throws Exception {
        Collection<Noppa> kirjattavat = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            kirjattavat.add(new HuonoNoppa(6));
        }
        assertEquals(peli.laskeSumma(kirjattavat, 6), 30);
    }

    /**
     * Test of laskeSamojenSumma method, of class Peli.
     */
    @Test
    public void testLaskeSamojenSumma() {
        Collection<Noppa> nList = new ArrayList<>();
        int summa = 0;
        for (int i = 1; i < 6; i++) {
            nList.add(new HuonoNoppa(i));
        }
        for (int j = 1; j < 6; j++) {
            summa += peli.laskeSamojenSumma(nList, j);
            nList.add(new HuonoNoppa(5));
        }
        assertEquals(summa, 75);
    }

    /**
     * Test of ryhmitteleLuvunMukaan method, of class Peli.
     */
//    @Test
//    public void testRyhmitteleLuvunMukaan() {
//        System.out.println("ryhmitteleLuvunMukaan");
//        Collection<Noppa> nList = null;
//        Peli instance = new Peli();
//        Map expResult = null;
//        Map result = instance.ryhmitteleLuvunMukaan(nList);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of laskeSuora method, of class Peli.
     */
    @Test
    public void testLaskePieniSuora() {
        Collection<Noppa> nList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            nList.add(new HuonoNoppa(i));
        }
        int mista = 1;
        int mihin = 5;
        int expResult = 15;
        int result = peli.laskeSuora(nList, 1, 5);
        assertEquals(expResult, result);
    }

    @Test
    public void testLaskePieniSuoraKunEiSuora() {
        Collection<Noppa> nList = new ArrayList<>();
        for (int i = 2; i <= 6; i++) {
            nList.add(new HuonoNoppa(i));
        }
        int mista = 1;
        int mihin = 5;
        int expResult = 0;
        int result = peli.laskeSuora(nList, 1, 5);
        assertEquals(expResult, result);
    }

    @Test
    public void testLaskeSuuriSuora() {
        Collection<Noppa> nList = new ArrayList<>();
        for (int i = 2; i <= 6; i++) {
            nList.add(new HuonoNoppa(i));
        }
        int mista = 2;
        int mihin = 6;
        int expResult = 20;
        int result = peli.laskeSuora(nList, 2, 6);
        assertEquals(expResult, result);
    }

    @Test
    public void testLaskeSuuriSuoraKunEiSuora() {
        Collection<Noppa> nList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            nList.add(new HuonoNoppa(i));
        }
        int mista = 2;
        int mihin = 6;
        int expResult = 0;
        int result = peli.laskeSuora(nList, 2, 6);
        assertEquals(expResult, result);
    }

    /**
     * Test of laskeBonusPisteet method, of class Peli.
     */
    @Test
    public void testLaskeBonusPisteet() {
        peli.addPelaaja("testPelaaja");

        Map<Integer, Kentta> apuLista = new HashMap<>();
        apuLista.put(1, Kentta.YKKOSET);
        apuLista.put(2, Kentta.KAKKOSET);
        apuLista.put(3, Kentta.KOLMOSET);
        apuLista.put(4, Kentta.NELOSET);
        apuLista.put(5, Kentta.VITOSET);
        apuLista.put(6, Kentta.KUTOSET);

        Map<Kentta, Integer> pt = peli.getPelaajat().get(0).getPisteet();
        for (int i = 1; i < 7; i++) {
            pt.put(apuLista.get(i), 5 * i);
        }
        peli.laskeBonusPisteet(peli.getPelaajat().get(0));
        assertEquals(50, peli.getPelaajat().get(0).getPisteet().get(Kentta.BONUS));
    }
}

class HuonoNoppa extends Noppa {

    private int sLuku;

    public HuonoNoppa(int luku) {
        super();
        sLuku = luku;
    }

    public void setLuku(int luku) {
        sLuku = luku;
    }

    @Override
    public int getLuku() {
        return sLuku;
    }
}
