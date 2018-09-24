package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.factory.AbstractGraphicsObjectFactory;
import cz.fit.dpo.mvcshooter.factory.RealisticObjectsFactory;
import cz.fit.dpo.mvcshooter.factory.SimpleObjectsFactory;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Observable;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Model extends Observable {

    private Cannon cannon;
    private AbstractGraphicsObjectFactory factory;
    private CopyOnWriteArrayList<Missile> missiles;
    private CopyOnWriteArrayList<Enemy> enemies;
    private CopyOnWriteArrayList<Collision> collisions;

    private int gravity;
    private int score;

    public Model() {
        this.factory = Shooter.simpleMode ? SimpleObjectsFactory.getInstance() : RealisticObjectsFactory.getInstance();
        this.cannon = new Cannon();
        this.missiles = new CopyOnWriteArrayList();
        this.enemies = new CopyOnWriteArrayList();
        this.resetEnemies();
        this.collisions = new CopyOnWriteArrayList();
        this.gravity = 10;
        this.score = 0;
    }

    public void changeCannonState() {
        this.cannon.changecannonState();
        setChanged();
        notifyObservers();
    }

    public void changeGravity(int deltaGravity) {
        if ((deltaGravity < 0 && this.gravity + deltaGravity >= Shooter.MIN_GRAVITY) || (deltaGravity > 0 && this.gravity + deltaGravity <= Shooter.MAX_GRAVITY)) {
            this.gravity += deltaGravity;
            setChanged();
            notifyObservers();
        }
    }

    public void changeSpeed(int deltaSpeed) {
        int cannonSpeed = this.cannon.getSpeed();
        if ((deltaSpeed < 0 && cannonSpeed + deltaSpeed >= Shooter.MIN_SPEED) || (deltaSpeed > 0 && cannonSpeed + deltaSpeed <= Shooter.MAX_SPEED)) {
            this.cannon.setSpeed(cannonSpeed + deltaSpeed);
            setChanged();
            notifyObservers();
        }
    }

    public void changeAngle(int deltaAngle) {
        int cannonDegrees = this.cannon.getAngleDegrees();
        if ((deltaAngle < 0 && cannonDegrees + deltaAngle >= Shooter.MIN_ANGLE) || (deltaAngle > 0 && cannonDegrees + deltaAngle <= Shooter.MAX_ANGLE)) {
            this.cannon.setAngleRadians(Math.toRadians(cannonDegrees + deltaAngle));
            setChanged();
            notifyObservers();
        }
    }

    public void changeCannonYPossition(int deltaCannonY) {
        if (cannon.move(deltaCannonY)) {
            setChanged();
            notifyObservers();
        }
    }

    public void shoot() {
        CopyOnWriteArrayList<Missile> tmpMissiles = this.cannon.fire(factory.createMissile(), this.gravity);
        for (Missile tmpMissile : tmpMissiles) {
            missiles.add(tmpMissile);
        }
        setChanged();
        notifyObservers();
    }

    private void changeScore(int deltaScore) {
        this.score += deltaScore;
        setChanged();
        notifyObservers();
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGravity() {
        return gravity;
    }

    public int getScore() {
        return score;
    }

    public Cannon getCannon() {
        return cannon;
    }

    public CopyOnWriteArrayList<Missile> getMissiles() {
        return missiles;
    }

    public CopyOnWriteArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public CopyOnWriteArrayList<Collision> getCollisions() {
        return collisions;
    }

    private void resetEnemies() {
        for (int i = 0; i < Shooter.NUMBER_OF_ENEMIES; i++) {
            enemies.add(factory.createEnemy());
        }
    }

    public synchronized void moveObjects() {
        for (Enemy enemy : enemies) {
            enemy.move();
        }
        for (Missile missile : missiles) {
            if (missile.getX() == Shooter.MAX_X + 10) {
                missiles.remove(missile);
                break;
            } else {
                missile.move();
                for (Enemy enemy : enemies) {
                    if (missile.isInCollision(enemy)) {
                        collisions.add(new Collision(missile.getX(), missile.getY()));
                        missiles.remove(missile);
                        enemies.remove(enemy);
                        enemies.add(factory.createEnemy());
                        this.changeScore(10);
                        break;
                    }
                }
            }
        }
        for (Collision collision : collisions) {
            if (collision.isAlive()) {
                collision.decreaseTimer();
            } else {
                collisions.remove(collision);
            }
        }
    }

    public ModelMemento save() {
        return new Memento();
    }

    public void restore(ModelMemento memento) {
        this.cannon = ((Memento) memento).cannon;
        this.factory = ((Memento) memento).factory;
        this.missiles = ((Memento) memento).missiles;
        this.enemies = ((Memento) memento).enemies;
        this.gravity = ((Memento) memento).gravity;
        this.score = ((Memento) memento).score;
        setChanged();
        notifyObservers();
    }

    private class Memento implements ModelMemento {

        private Cannon cannon;
        private boolean simpleCannonState;
        private AbstractGraphicsObjectFactory factory;
        private CopyOnWriteArrayList<Missile> missiles;
        private CopyOnWriteArrayList<Enemy> enemies;

        private int gravity;
        private int score;

        public Memento() {

            this.cannon = new Cannon(Model.this.cannon);
            this.factory = Model.this.factory;
            this.missiles = new CopyOnWriteArrayList<Missile>();
            for (Missile missile : Model.this.missiles) {
                missiles.add(new Missile(missile));
            }
            this.enemies = new CopyOnWriteArrayList<Enemy>();
            for (Enemy enemy : Model.this.enemies) {
                enemies.add(enemy.copy());
            }
            this.score = Model.this.score;
            this.gravity = Model.this.gravity;
        }
    }

    public interface ModelMemento {

    }

}
