package fit.maurever.layout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Map panel, not implemented yet!
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class MapPanel extends JPanel {

    private static final long serialVersionUID = -3933493236765411447L;

    public static final int WIDTH = 947;
    public static final int HEIGHT = 543;

    private static final int SQUARE_SIZE = 10;

    public static double max = Double.MIN_VALUE;

    List<MapArea> areas;

    public MapPanel() {
        areas = new ArrayList<MapArea>();
    }

    public void addArea(MapArea mp) {
        areas.add(mp);
    }

    @Override
    public void paintComponent(Graphics g) {
        BufferedImage result = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D gbi = result.createGraphics();
        BufferedImage x = null;
        try {
            x = ImageIO.read(new File("images/mapa_pruhledna.png"));
        } catch (IOException ex) {
            System.err.println("ERR: Map not loaded!");
        }
        gbi.drawImage(x, 0, 0, this);
        for (MapArea mapArea : areas) {
            double value = mapArea.getValue() < 1 ? 0 : Math.log10(mapArea.getValue());
            gbi.setColor(new Color(1.0f, 0.0f, 0.0f, (float) ((0.8f * value) / Math.log10(max))));
            gbi.fillRect(mapArea.getXpr(), mapArea.getYpr(), SQUARE_SIZE, SQUARE_SIZE);
        }
        g.drawImage(result, 0, 0, this);
    }

    public void print() {
        for (MapArea area : areas) {
            System.out.println(area.getXpr() + " " + area.getYpr() + " " + area.getColor() + " " + area.getValue());
        }
        System.out.println("................");

    }
}
