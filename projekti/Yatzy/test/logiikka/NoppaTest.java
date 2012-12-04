package logiikka;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class NoppaTest {
    
    public NoppaTest() {
    }
    
    Noppa noppa;

    @Before
    public void setUp() {
        noppa = new Noppa();
    }
    @Test
    public void getteriToimii() {
        noppa.heita();
        assertTrue(noppa.getLuku() != 0);
    }
    @Test
    public void nopanHeittoOnnistuu() {
        noppa.heita();
        assertTrue(0 < noppa.getLuku() && noppa.getLuku() < 7);
    }
    @Test
    public void noppaHeittaaSataKertaa() {
        boolean testi = recHeita(100, true, noppa);
        assertTrue(testi);
    }
    
    private boolean recHeita(int kertaa, boolean tulos, Noppa noppa) {
        if (kertaa > 0) {
            noppa.heita();
            tulos = recHeita(kertaa - 1, tulos && (0 < noppa.getLuku() && noppa.getLuku() < 7), noppa);
        }
        return tulos;
    }
    
    @Test
    public void noppaHeittää10000KertaaKaikkiLuvutEsiintyy() {
        ArrayList<Integer> tulokset = new ArrayList<Integer>();
        for (int i = 0; i < 10000; i++) {
            noppa.heita();
            tulokset.add(noppa.getLuku());
        }
        assertTrue(tulokset.contains(1) &&
                tulokset.contains(2) &&
                tulokset.contains(3) &&
                tulokset.contains(4) &&
                tulokset.contains(5) &&
                tulokset.contains(6));
    }
    

}
