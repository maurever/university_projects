package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.visitor.DrawVisitor;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public abstract class Enemy extends GraphicsObject {

    private final int type;

    public Enemy() {
        this.setX((int) (Math.random() * (Shooter.MAX_X - 30) + 30));
        this.setY((int) (Math.random() * (Shooter.MAX_Y - 30) + 30));
        this.type = (int) (Math.random() * 2 + 1);
    }

    public Enemy(int x, int y, int type) {
        super(x, y);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public abstract void move();

    public abstract Enemy copy();

    @Override
    public void accept(DrawVisitor visitor) {
        visitor.visitEnemy(this);
    }

}
