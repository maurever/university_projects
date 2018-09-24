package cz.fit.dpo.mvcshooter.model;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class SimpleEnemy extends Enemy {

    public SimpleEnemy() {
        super();
    }

    public SimpleEnemy(int x, int y, int type) {
        super(x, y, type);
    }

    @Override
    public void move() {
    }

    @Override
    public Enemy copy() {
        return new SimpleEnemy(this.getX(), this.getY(), this.getType());
    }

}
