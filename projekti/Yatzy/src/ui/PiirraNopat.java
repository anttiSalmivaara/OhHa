package ui;
import java.util.Collection;
import logiikka.Noppa;
/**
 * PiirraNopat-luokka sisältää pelin alun "otsikkonoppien" esityksen sekä metodin
 * noppien esittämiseen tekstikäyttöliittymässä.
 * 
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class PiirraNopat {
    
    private String alkunopat;
    
    public PiirraNopat() {
        this.alkunopat = "┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐\n" + 
                         "│       │ │       │ │       │ │       │ │       │\n" +
                         "│   Y   │ │   A   │ │   T   │ │   Z   │ │   Y   │\n" + 
                         "│       │ │       │ │       │ │       │ │       │\n" +
                         "└───────┘ └───────┘ └───────┘ └───────┘ └───────┘\n";
    }
   
    public String alkuNopat() {
        return alkunopat;
    }

    public String tulostaNopat(Collection<Noppa> nop) {
        StringBuilder sb = new StringBuilder();
        sb.append("┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐ ┌───────┐");
        sb.append("\n");
        for (Noppa n : nop) {
            int nLuku = n.getLuku();
            if (nLuku == 6 || nLuku == 5 || nLuku == 4) {
                sb.append("│ ●   ● │ ");
            } else if (nLuku == 3 || nLuku == 2) {
                sb.append("│ ●     │ ");
            } else {
                sb.append("│       │ ");
            }
        }
        sb.append("\n");
        for (Noppa n : nop) {
            int nLuku = n.getLuku();
            if (nLuku == 6) {
                sb.append("│ ●   ● │ ");
            } else if (nLuku == 1 || nLuku == 3 || nLuku == 5) {
                sb.append("│   ●   │ ");
            } else {
                sb.append("│       │ ");
            }
        }
        sb.append("\n");
        for (Noppa n : nop) {
            int nLuku = n.getLuku();
            if (nLuku == 6 || nLuku == 5 || nLuku == 4) {
                sb.append("│ ●   ● │ ");
            } else if (nLuku == 3 || nLuku == 2) {
                sb.append("│     ● │ ");
            } else {
                sb.append("│       │ ");
            }
        }
        sb.append("\n");
        sb.append("└───────┘ └───────┘ └───────┘ └───────┘ └───────┘");
        sb.append("\n");
        return sb.toString();
    }
}
