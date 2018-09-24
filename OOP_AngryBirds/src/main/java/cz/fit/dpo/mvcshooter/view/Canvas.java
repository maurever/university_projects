package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.GraphicsObject;
import cz.fit.dpo.mvcshooter.model.Model;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * View
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Canvas extends JPanel implements Observer {

    GraphicsDrawer drawer = new GraphicsDrawer();
    Model model;

    public Canvas(Model model, int x, int y, int width, int height) {
        this.model = model;
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setLocation(x, y);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
    }

    public void repaintView() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<GraphicsObject> objects = new ArrayList<GraphicsObject>();
        Cannon tmpCannon = model.getCannon();
        objects.add(tmpCannon);
        objects.addAll(model.getMissiles());
        objects.addAll(model.getEnemies());
        objects.addAll(model.getCollisions());
        drawer.setGraphics(g);
        drawer.paint(objects, model.getGravity(), tmpCannon.getSpeed(), tmpCannon.getAngleDegrees(), model.getScore());
    }

    @Override
    public void update(Observable o, Object arg) {
    }

}
