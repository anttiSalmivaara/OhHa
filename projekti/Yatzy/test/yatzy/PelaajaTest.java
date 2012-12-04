package yatzy;

import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Antti Salmivaara <antti.salmivaara@helsinki.fi>
 */
public class PelaajaTest {
    
    public PelaajaTest() {
    }
    
    private Pelaaja pelaaja;
    
    @Before
    public void setUp() {
        pelaaja = new Pelaaja("testipelaaja");
    }
    
    @Test
    public void asetaJaGetPisteetToimii() {
        pelaaja.asetaPisteet(10, Kentta.TAYSKASI);
        Map<Kentta, Integer> apuri = pelaaja.getPisteet();
        int apuInt  = apuri.get(Kentta.TAYSKASI);
        assertEquals(10, apuInt);
    }
    @Test
    public void getNimiToimii(){
        assertEquals("testipelaaja", pelaaja.getNimi());
    }
    @Test
    public void getSummaToimii() {
        pelaaja.asetaPisteet(10, Kentta.KOLMESAMAA);
        pelaaja.asetaPisteet(10, Kentta.VITOSET);
        assertEquals(20, pelaaja.getSumma());
    }
    
    @Test
    public void peliLoppuToimiiIlmanBonusta() {
        for (Kentta k : Kentta.values()) {
            if (k != Kentta.BONUS && k != Kentta.VIRHE) {
                pelaaja.asetaPisteet(10, k);
            }
        }
        assertTrue(pelaaja.peliLoppu());
    }
    
    @Test
    public void peliLoppuToimiiBonuksenKanssa() {
        for (Kentta k : Kentta.values()) {
            if (k != Kentta.VIRHE) {
                pelaaja.asetaPisteet(10, k);
            }
        }
        assertTrue(pelaaja.peliLoppu());
    }
    
    @Test
    public void peliLoppuToimiiKunPeliEiOleLoppu() {
        for (int i = 0; i < 10; i++) {
            pelaaja.asetaPisteet(10, Kentta.values()[i]);
        }
        assertFalse(pelaaja.peliLoppu());
    }
}
