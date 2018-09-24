package cz.fit.dpo.mvcshooter.model;


import cz.fit.dpo.mvcshooter.model.interfaces.visitor.DrawVisitor;
import cz.fit.dpo.mvcshooter.strategy.MissileMovementStrategy;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Missile extends GraphicsObject {

    private int inicialX;
    private int inicialY;
    private int gravity;
    private int speed;
    private double angle;
    private int timer;
    private MissileMovementStrategy strategy;

    public Missile() {
    }

    public Missile(MissileMovementStrategy strategy) {
        this.strategy = strategy;
    }

    public Missile(Missile missile) {
        this.setX(missile.getX());
        this.setY(missile.getY());
        this.inicialX = missile.getInicialX();
        this.inicialY = missile.getInicialY();
        this.speed = missile.getSpeed();
        this.angle = missile.getAngle();
        this.gravity = missile.getGravity();
        this.strategy = missile.getStrategy();

        this.timer = missile.getTimer();
    }

    public void setInicialX(int inicialX) {
        this.inicialX = inicialX;
    }

    public int getInicialX() {
        return inicialX;
    }

    public void setInicialY(int inicialY) {
        this.inicialY = inicialY;
    }

    public int getInicialY() {
        return inicialY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setTimer() {
        this.timer = 0;
    }

    public MissileMovementStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MissileMovementStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean isInCollision(GraphicsObject object) {
        return ((this.getX() >= object.getX() - Shooter.TOLERATION && this.getX() <= object.getX() + Shooter.TOLERATION) && (this.getY() >= object.getY() - Shooter.TOLERATION && this.getY() <= object.getY() + Shooter.TOLERATION));
    }

    public void move() {
        this.setX(strategy.getNewX(this));
        this.setY(strategy.getNewY(this));
        timer++;
    }

    @Override
    public void accept(DrawVisitor visitor) {
        visitor.visitMissile(this);
    }

}
