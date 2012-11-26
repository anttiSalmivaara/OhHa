package gui;

import java.awt.Dimension;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JToggleButton;

/**
 *
 * @author Antti Salmivaara antti.salmivaara@helsinki.fi
 */
public class GraafNoppa extends JToggleButton {

    public GraafNoppa(Icon icon) throws Exception {
        super(icon);
        super.setPreferredSize(new Dimension(80, 80));
    }

    public void uusiNoppa(Icon icon) {
        super.setIcon(icon);
    }
}
