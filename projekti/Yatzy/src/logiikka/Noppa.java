package logiikka;
import java.util.Random;

/**
 * Luokassa määritellään noppa, jonka voi heittää ja jolta voi pyytää silmälukua palautusarvona.
 * 
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class Noppa {
    private int silmaluku;
    private Random random;
    
    /**
     * Konstruktori luo satunnaislukugeneraattorin.
     */
    public Noppa() {
        this.random = new Random();
    }
    
    /**
     * Metodi "heittää" nopan eli arpoo silmäluku-muuttujalle uuden arvon.
     * 
     */    
    public void heita() {
        silmaluku = arvoLuku();
    }
    /**
     * Metodi palauttaa nopan tämänhetkisen silmäluvun.
     * @return nopan silmäluku
     */
    public int getLuku() {
        return silmaluku;
    }
    
    /**
     * Metodi palauttaa nopan tämänhetkisen silmäluvun tekstinä.
     * @return nopan silmäluku tekstinä
     */
    @Override
    public String toString() {
        return "" + silmaluku;
    }
    
    private int arvoLuku() {
        return random.nextInt(6) + 1;
    }
//┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐
//│ ●     │ │ ●   ● │ │       │ │ ●     │ │ ●   ● │
//│   ●   │ │       │ │   ●   │ │       │ │   ●   │
//│     ● │ │ ●   ● │ │       │ │     ● │ │ ●   ● │
//└───────┘ └───────┘ └───────┘ └───────┘ └───────┘
//
//┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐
//│       │ │       │ │       │ │       │ │       │
//│   Y   │ │   A   │ │   T   │ │   Z   │ │   Y   │
//│       │ │       │ │       │ │       │ │       │
//└───────┘ └───────┘ └───────┘ └───────┘ └───────┘
//
//
 
}