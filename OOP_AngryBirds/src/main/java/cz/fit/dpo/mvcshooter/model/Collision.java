package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.visitor.DrawVisitor;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Collision extends GraphicsObject {

    private int timer;

    public Collision(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.timer = 10;
    }

    public int getTimer() {
        return timer;
    }

    public void decreaseTimer() {
        if (timer >= 0) {
            timer--;
        }
    }

    public boolean isAlive() {
        return timer > 0;
    }

    @Override
    public void accept(DrawVisitor visitor) {
        visitor.visitCollision(this);
    }

}
