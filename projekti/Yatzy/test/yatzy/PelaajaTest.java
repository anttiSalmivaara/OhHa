package yatzy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import yatzy.*;

/**
 *
 * @author aasalmiv
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
        assertEquals(pelaaja.getPisteet().get(Kentta.TAYSKASI), 10);
    }
    @Test
    public void getNimiToimii(){
        assertEquals(pelaaja.getNimi(),"testipelaaja");
    }
    @Test
    public void getSummaToimii() {
        pelaaja.asetaPisteet(10, Kentta.KOLMESAMAA);
        pelaaja.asetaPisteet(10, Kentta.VITOSET);
        assertEquals(pelaaja.getSumma(),20);
    }
}
