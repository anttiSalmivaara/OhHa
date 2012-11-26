package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import logiikka.Peli;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class HeitaListener implements ActionListener {
    
    private Peli peli;
    private JPanel nopat;
    private PelaajaVuoro pv;
    private int ovelaLaskuri;
 
    public HeitaListener(PelaajaVuoro pv, JPanel nopat, Peli peli) {
        this.peli = peli;
        this.nopat = nopat;
        this.pv = pv;
        this.ovelaLaskuri = 0;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        peli.heitaNopat(getHeitettavat());
        if (ovelaLaskuri == 0) {
            pv.alusta();
        }
    }
    
    private List<Integer> getHeitettavat() {
        ArrayList<Integer> palaute = new ArrayList<>();
        GraafNoppa apuri;
        for (int i = 0; i < 5; i++) {
            apuri = (GraafNoppa) nopat.getComponent(i);
            if (apuri.isSelected() ) {
                palaute.add(i+1);
            }
        }
        return palaute;
    }
}
