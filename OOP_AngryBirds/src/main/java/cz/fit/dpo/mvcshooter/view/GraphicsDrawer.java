package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Collision;
import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.GraphicsObject;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.interfaces.visitor.DrawVisitor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class GraphicsDrawer implements DrawVisitor {

    private BufferedImage cannonImage;
    private BufferedImage enemyImage1;
    private BufferedImage enemyImage2;
    private BufferedImage missileImage;
    private BufferedImage collisionImage;
    private Graphics graphics;

    private final int INFO_X = 0;
    private final int INFO_Y = 15;

    public GraphicsDrawer() {
        try {
            cannonImage = ImageIO.read(getClass().getResourceAsStream("/images/cannon.png"));
            enemyImage1 = ImageIO.read(getClass().getResourceAsStream("/images/enemy1.png"));
            enemyImage2 = ImageIO.read(getClass().getResourceAsStream("/images/enemy2.png"));
            missileImage = ImageIO.read(getClass().getResourceAsStream("/images/missile.png"));
            collisionImage = ImageIO.read(getClass().getResourceAsStream("/images/collision.png"));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public void drawInfo(int gravity, int speed, int angle, int score) {
        graphics.drawString("gravity: " + gravity
                + " speed: " + speed
                + " angle: " + angle
                + " score: " + score, INFO_X, INFO_Y);
    }

    public void draw(GraphicsObject object) {
        object.accept(this);
    }

    public void paint(ArrayList<GraphicsObject> objects, int gravity, int speed, int angle, int score) {
        drawInfo(gravity, speed, angle, score);
        for (GraphicsObject object : objects) {
            draw(object);
        }
    }

    @Override
    public void visitMissile(Missile missile) {
        this.graphics.drawImage(this.missileImage,
                missile.getX() - this.missileImage.getWidth() / 2,
                missile.getY() - this.missileImage.getHeight() / 2, null);
    }

    @Override
    public void visitEnemy(Enemy enemy) {
        BufferedImage tmpEnemyImage = enemy.getType() == 1 ? this.enemyImage1 : this.enemyImage2;
        this.graphics.drawImage(tmpEnemyImage,
                enemy.getX() - tmpEnemyImage.getWidth() / 2,
                enemy.getY() - tmpEnemyImage.getHeight() / 2, null);
    }

    @Override
    public void visitCannon(Cannon cannon) {
        this.graphics.drawImage(this.cannonImage,
                cannon.getX() - this.cannonImage.getWidth() / 2,
                cannon.getY() - this.cannonImage.getHeight() / 2, null);
    }

    @Override
    public void visitCollision(Collision collision) {
        graphics.drawImage(collisionImage,
                collision.getX() - collisionImage.getWidth() / 2,
                collision.getY() - collisionImage.getHeight() / 2, null);
    }

}
