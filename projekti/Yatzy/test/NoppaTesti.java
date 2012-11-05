import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import yatzy.logiikka.*;
/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class NoppaTesti {
    
    public NoppaTesti() {
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
    

}
