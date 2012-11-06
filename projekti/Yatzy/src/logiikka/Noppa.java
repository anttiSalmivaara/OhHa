package logiikka;
import yatzy.*;
import java.util.Random;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Noppa {
    private int silmaluku;
    private Random random;
    
    public Noppa() {
        this.random = new Random();
    }
    
    public void heita() {
        silmaluku = arvoLuku();
    }
    
    public int getLuku() {
        return silmaluku;
    }
    
    @Override
    public String toString() {
        return "" + silmaluku;
    }
    
    private int arvoLuku() {
        return random.nextInt(6) + 1;
    }
}