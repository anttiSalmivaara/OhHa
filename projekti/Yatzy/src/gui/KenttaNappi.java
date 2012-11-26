package gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import logiikka.Peli;
import yatzy.Kentta;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class KenttaNappi extends JButton {

    private Kentta kentta;

    public KenttaNappi(PelaajaVuoro pv, JPanel taulukko, Peli peli, Kentta kentta) {
        super();
        this.kentta = kentta;
        super.addActionListener(new ScoreCardListener(pv, taulukko, peli, this.kentta));
        if (this.kentta == Kentta.BONUS && this.kentta == Kentta.SUMMA) {
            super.setEnabled(false);
            super.setText("0");
        }
    }

    public void setActive() {
        if (super.getText().isEmpty()) {
            super.setEnabled(true);
        }
    }

    public void setPassive() {
        super.setEnabled(false);
    }
}
