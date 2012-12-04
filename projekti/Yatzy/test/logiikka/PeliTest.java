package logiikka;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import yatzy.Kentta;
import yatzy.Pelaaja;

/**
 *
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class PeliTest {

    private Peli peli;
    private Pelaaja pelaaja;
    private List<Integer> heitettavat;
    private Collection<Noppa> ykkoset;
    private Collection<Noppa> kakkoset;
    private Collection<Noppa> kolmoset;
    private Collection<Noppa> neloset;
    private Collection<Noppa> vitoset;
    private Collection<Noppa> kutoset;
    private Collection<Noppa> pSuora;
    private Collection<Noppa> sSuora;
    private Collection<Noppa> taysKasi;

    public PeliTest() {
    }

    @Before
    public void setUp() {
        this.peli = new Peli();

        peli.addPelaaja("testPelaaja");
        pelaaja = peli.getPelaajat().get(0);

        this.heitettavat = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            this.heitettavat.add(i);
        }
        ykkoset = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            ykkoset.add(new HuonoNoppa(1));
        }
        kakkoset = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            kakkoset.add(new HuonoNoppa(2));
        }
        kolmoset = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            kolmoset.add(new HuonoNoppa(3));
        }
        neloset = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            neloset.add(new HuonoNoppa(4));
        }
        vitoset = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            vitoset.add(new HuonoNoppa(5));
        }
        kutoset = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            kutoset.add(new HuonoNoppa(6));
        }
        pSuora = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            pSuora.add(new HuonoNoppa(i));
        }
        sSuora = new ArrayList<>();
        for (int i = 2; i < 7; i++) {
            sSuora.add(new HuonoNoppa(i));
        }
        taysKasi = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            taysKasi.add(new HuonoNoppa(2));
        }
        for (int i = 0; i < 3; i++) {
            taysKasi.add(new HuonoNoppa(3));
        }
    }

    /**
     * Test of heitaNopat method, of class Peli.
     */
    @Test
    public void testHeitaNopat() {
        Map<Integer, Noppa> result = peli.heitaNopat(heitettavat);
        boolean b = true;
        for (int i = 1; i < 6; i++) {
            b = b && result.get(i).getLuku() < 7 && result.get(i).getLuku() > 0;
        }
        assertTrue(b);
    }

    @Test
    public void testHeitaNopat1000Kertaa() {
        boolean b = true;
        for (int j = 0; j < 1000; j++) {
            Map<Integer, Noppa> result = peli.heitaNopat(heitettavat);
            for (int i = 1; i < 6; i++) {
                b = b && result.get(i).getLuku() < 7 && result.get(i).getLuku() > 0;
            }
        }
        assertTrue(b);
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
        for (int j = 0; j < 1000; j++) {
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
    public void testKirjaaPisteetYkkoset() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.YKKOSET, ykkoset);
        assertEquals(5, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetKakkoset() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.KAKKOSET, kakkoset);
        assertEquals(10, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetKolmoset() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.KOLMOSET, kolmoset);
        assertEquals(15, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetNeloset() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.NELOSET, neloset);
        assertEquals(20, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetVitoset() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.VITOSET, vitoset);
        assertEquals(25, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetKutoset() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.KUTOSET, kutoset);
        assertEquals(30, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetPari() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.PARI, kutoset);
        assertEquals(12, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetKaksiParia() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.KAKSIPARIA, kutoset);
        assertEquals(24, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetPieniSuora() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.PIENISUORA, pSuora);
        assertEquals(15, pelaaja.getSumma());
    }

    @Test
    public void testKirjaaPisteetSuuriSuora() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.SUURISUORA, sSuora);
        assertEquals(20, pelaaja.getSumma());
    }

    @Test
    public void testaaKirjaaPisteetTayskasi() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.TAYSKASI, kutoset);
        assertEquals(30, pelaaja.getSumma());
    }

    @Test
    public void testaaKirjaaPisteetYatzy() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.YATZY, ykkoset);
        assertEquals(50, pelaaja.getSumma());
    }

    @Test
    public void testaaKirjaaPisteetSattuma() throws Exception {
        peli.kirjaaPisteet(pelaaja, Kentta.SATTUMA, kakkoset);
        assertEquals(10, pelaaja.getSumma());
    }

    /**
     * Test of peliLoppu method, of class Peli.
     */
    @Test
    public void testPeliLoppu() throws Exception {
        for (Kentta k : Kentta.values()) {
            if (k != Kentta.VIRHE && k != Kentta.SUMMA && k != Kentta.BONUS) {
                peli.kirjaaPisteet(pelaaja, k, ykkoset);
            }
        }
        assertTrue(peli.peliLoppu());
    }

    /**
     * Test of addPelaaja method, of class Peli.
     */
    @Test
    public void testAddPelaaja() {
        assertTrue(!peli.getPelaajat().isEmpty() && peli.getPelaajat().get(0).getNimi().equals("testPelaaja"));
    }

    @Test
    public void testAdd1000Pelaajaa() {
        for (int i = 0; i < 999; i++) {
            peli.addPelaaja("" + i);
        }
        assertEquals(1000, peli.getPelaajat().size());
    }

    /**
     * Test of getPelaajat method, of class Peli.
     */
    @Test
    public void testGetPelaajat() {
        assertEquals("testPelaaja", peli.getPelaajat().get(0).getNimi());
    }

    @Test
    public void testGet1000Pelaajaa() {
        for (int i = 0; i < 999; i++) {
            peli.addPelaaja("" + i);
        }
        assertEquals(peli.getPelaajat().size(), 1000);
    }

    @Test
    public void testAdd1000PelaajaaGetNimiOikein() {
        for (int i = 1; i < 1000; i++) {
            peli.addPelaaja("" + i);
        }
        assertEquals("999", peli.getPelaajat().get(999).getNimi());
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
    @Test
    public void testRyhmitteleLuvunMukaanNopatSamoja() {
        Map<Integer, Integer> tulos = peli.ryhmitteleLuvunMukaan(ykkoset);
        assertTrue(tulos.get(1) == 5 &&
                tulos.get(2) == 0 &&
                tulos.get(3) == 0 &&
                tulos.get(4) == 0 &&
                tulos.get(5) == 0 &&
                tulos.get(6) == 0);
    }

    @Test
    public void testRyhmitteleLuvunMukaanNopatEri() {
        Map<Integer, Integer> tulos = peli.ryhmitteleLuvunMukaan(pSuora);
        assertTrue(tulos.get(1) == 1
                && tulos.get(2) == 1
                && tulos.get(3) == 1
                && tulos.get(4) == 1
                && tulos.get(5) == 1
                && tulos.get(6) == 0);
    }

    @Test
    public void testRyhmitteleLuvunMukaanKaksiSamaaSilmalukua() {
        ArrayList<Noppa> testiNopat = new ArrayList<>();
        testiNopat.add(new HuonoNoppa(1));
        testiNopat.add(new HuonoNoppa(2));
        testiNopat.add(new HuonoNoppa(2));
        testiNopat.add(new HuonoNoppa(3));
        testiNopat.add(new HuonoNoppa(4));
        
        Map<Integer, Integer> tulos = peli.ryhmitteleLuvunMukaan(testiNopat);
        
        assertTrue(tulos.get(1) == 1 &&
                tulos.get(2) == 2 &&
                tulos.get(3) == 1 &&
                tulos.get(4) == 1 &&
                tulos.get(5) == 0 &&
                tulos.get(6) == 0);
    }

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
        int result = peli.laskeSuora(nList, mista, mihin);
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
        int result = peli.laskeSuora(nList, mista, mihin);
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
        int result = peli.laskeSuora(nList, mista, mihin);
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
        int result = peli.laskeSuora(nList, mista, mihin);
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
        double apulainen = 1.0 * peli.getPelaajat().get(0).getPisteet().get(Kentta.BONUS);
        assertEquals(50, apulainen, 0.001);
    }

    /**
     * Test of aloita method, of class Peli.
     */
    @Test
    public void testAloita() {
        Peli testiPeli = new Peli();
        testiPeli.addPelaaja("testi1");
        testiPeli.addPelaaja("testi2");
        testiPeli.aloita();
        
        int apuSumma = 0;
        for (Noppa n : testiPeli.getNopat().values()) {
            apuSumma += n.getLuku();
        }
        
        assertTrue(testiPeli.getVuorossa().getNimi().equals("testi1") &&
                   apuSumma != 0);
    }

    /**
     * Test of vuoroVaihtuu method, of class Peli.
     */
    @Test
    public void testVuoroVaihtuu() {
        Peli testiPeli = new Peli();
        testiPeli.addPelaaja("testi1");
        testiPeli.addPelaaja("testi2");
        
        testiPeli.aloita();
        testiPeli.vuoroVaihtuu();
        
        assertEquals("testi2", testiPeli.getVuorossa().getNimi());
    }

    /**
     * Test of getVuorossa method, of class Peli.
     */
    @Test
    public void testGetVuorossa() {
        peli.aloita();
        assertEquals("testPelaaja", peli.getVuorossa().getNimi());
    }

    /**
     * Test of getVoittaja method, of class Peli.
     */
    @Test
    public void testGetVoittaja() {
        Peli testiPeli = new Peli();
        testiPeli.addPelaaja("testi1");
        testiPeli.addPelaaja("testi2");
        
        testiPeli.getPelaajat().get(0).asetaPisteet(10, Kentta.YKKOSET);
        testiPeli.getPelaajat().get(1).asetaPisteet(15, Kentta.YKKOSET);
        
        assertEquals("testi2", testiPeli.getVoittaja().getNimi());
    }

    /**
     * Test of laskeKaksiParia method, of class Peli.
     */
    @Test
    public void testLaskeKaksiPariaKunKaksiParia() {
        assertEquals(10, peli.laskeKaksiParia(taysKasi));
    }
    
    public void testLaskeKaksiPariaKunEiKahtaParia() {
        assertEquals(0, peli.laskeKaksiParia(pSuora));
    }

    /**
     * Test of laskeTaysKasi method, of class Peli.
     */
    @Test
    public void testLaskeTaysKasiKunTayskasi() {
        assertEquals(13, peli.laskeTaysKasi(taysKasi));
    }
    @Test
    public void testLaskeTaysKasiKunEiTaysKasi() {
        assertEquals(0, peli.laskeTaysKasi(pSuora));
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