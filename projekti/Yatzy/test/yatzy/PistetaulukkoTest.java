package yatzy;

import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author aasalmiv
 */
public class PistetaulukkoTest {
    
    public PistetaulukkoTest() {
    }
    
    private Pistetaulukko taulukko;
    
    @Before
    public void setUp() {
        taulukko = new Pistetaulukko();
    }
    
    @Test
    public void asetaPisteetJaGetPisteetToimii() {
        taulukko.asetaPisteet(3, Kentta.YKKOSET);
        Map pistelista = taulukko.getPisteet();
        assertEquals(pistelista.get(Kentta.YKKOSET),3);
    }
    @Test
    public void getSummaToimii() {
        taulukko.asetaPisteet(4, Kentta.KAKKOSET);
        taulukko.asetaPisteet(6, Kentta.KOLMOSET);
        assertEquals(taulukko.getSumma(),10);
    }    
    @Test
    public void toStringTulostaaOikein() {
        taulukko.asetaPisteet(4, Kentta.KAKKOSET);
        taulukko.asetaPisteet(6, Kentta.KOLMOSET);
        assertEquals(taulukko.toString(), "KOLMOSET: 6\nKAKKOSET: 4\n");
    }
}

