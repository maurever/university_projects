package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.visitor.DrawVisitor;
import cz.fit.dpo.mvcshooter.state.DoubleCannonState;
import cz.fit.dpo.mvcshooter.state.ShootingState;
import cz.fit.dpo.mvcshooter.state.SimpleCannonState;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Cannon extends GraphicsObject {

    private ShootingState cannonState;
    private boolean simpleCannonState;
    private double angleRadians;
    private int speed;

    public Cannon() {
        this.setX(30);
        this.setY(450);
        this.angleRadians = Math.toRadians(70);
        this.speed = 70;
        this.simpleCannonState = true;
        this.cannonState = new SimpleCannonState();
    }

    public Cannon(int inicialX, int inicialY, int angle, int speed) {
        this.setX(inicialX);
        this.setY(inicialY);
        this.angleRadians = Math.toRadians(angle);
        this.speed = speed;
        this.simpleCannonState = true;
        this.cannonState = new SimpleCannonState();
    }

    public Cannon(Cannon cannon) {
        this.setX(cannon.getX());
        this.setY(cannon.getY());
        this.angleRadians = cannon.getAngleRadians();
        this.speed = cannon.getSpeed();
        this.simpleCannonState = cannon.getSimpleCannonState();
        this.cannonState = this.simpleCannonState ? SimpleCannonState.getInstance() : DoubleCannonState.getInstance();
    }

    public boolean move(int deltaCannonY) {
        if ((deltaCannonY < 0 && this.getY() + deltaCannonY > Shooter.MIN_CANNON_Y) || (deltaCannonY > 0 && this.getY() + deltaCannonY < Shooter.MAX_CANNON_Y)) {
            this.setY(this.getY() + deltaCannonY);
            return true;
        } else {
            return false;
        }
    }

    public CopyOnWriteArrayList<Missile> fire(Missile missile, int gravity) {
        missile.setX(this.getX());
        missile.setY(this.getY());
        missile.setInicialX(this.getX());
        missile.setInicialY(this.getY());
        missile.setGravity(gravity);
        missile.setAngle(this.getAngleRadians());
        missile.setSpeed(this.getSpeed());
        return cannonState.shoot(missile, gravity);
    }

    public void changecannonState() {
        if (simpleCannonState) {
            this.cannonState = DoubleCannonState.getInstance();
        } else {
            this.cannonState = SimpleCannonState.getInstance();
        }
        this.simpleCannonState = !simpleCannonState;
    }

    public double getAngleRadians() {
        return this.angleRadians;
    }

    public int getAngleDegrees() {
        return (int) Math.round(Math.toDegrees(this.angleRadians));
    }

    public void setAngleRadians(double angleRadians) {
        this.angleRadians = angleRadians;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCannonState(ShootingState cannonState) {
        this.cannonState = cannonState;
    }

    public ShootingState getCannonState() {
        return cannonState;
    }

    public void setSimpleCannonState(boolean simpleCannonState) {
        this.simpleCannonState = simpleCannonState;
    }

    public boolean getSimpleCannonState() {
        return this.simpleCannonState;
    }

    @Override
    public void accept(DrawVisitor visitor) {
        visitor.visitCannon(this);
    }

}
