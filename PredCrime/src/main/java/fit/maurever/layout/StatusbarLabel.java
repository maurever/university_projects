package fit.maurever.layout;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * Statusbar Label
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class StatusbarLabel extends JLabel {

    private final String headerText = "Status: ";

    public StatusbarLabel() {
        this.setPreferredSize(new Dimension(-1, 22));
        this.setBorder(LineBorder.createGrayLineBorder());
        this.setOpaque(true);
    }

    @Override
    public void setText(String text) {
        super.setText(headerText + text);
    }
}
