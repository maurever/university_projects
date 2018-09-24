package cz.fit.dpo.mvcshooter.model;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class RealisticEnemy extends Enemy {

    private int timer;

    public RealisticEnemy(int x, int y, int type, int timer) {
        super(x, y, type);
        this.timer = timer;
    }

    public RealisticEnemy() {
        timer = 1000;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    void resetPosition() {
        this.setX((int) (Math.random() * (Shooter.MAX_X - 50) + 50));
        this.setY((int) (Math.random() * (Shooter.MAX_X - 50) + 50));
        this.timer = 1000;
    }

    @Override
    public void move() {
        if (timer == 0) {
            resetPosition();
        } else {
            this.timer--;
        }
    }

    @Override
    public Enemy copy() {
        return new RealisticEnemy(this.getX(), this.getY(), this.getType(), this.timer);
    }
}
