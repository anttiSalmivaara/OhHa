/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.CardLayout;
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
    private CardLayout cl;
    private Container c;
 
    public HeitaListener(Peli peli, Container c) {
        this.peli = peli;
        this.cl = (CardLayout) ((JPanel) c.getComponent(1)).getLayout();
        this.c = c;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        peli.heitaNopat(getHeitettavat(c));
        this.cl.show((Container) c.getComponent(1), "Varsinainen peli");
    }
    
    private List<Integer> getHeitettavat(Container co) {
        ArrayList<Integer> palaute = new ArrayList<>();
        JPanel apuri = (JPanel) co.getComponent(0);
        for (int i = 0; i < 5; i++) {
            if (((GraafNoppa) apuri.getComponent(i)).isSelected()) {
                palaute.add(i + 1);
            }
        }
        return palaute;
    }
}
