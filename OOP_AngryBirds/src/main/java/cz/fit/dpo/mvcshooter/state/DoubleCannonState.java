package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.Shooter;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class DoubleCannonState implements ShootingState {

    private static final DoubleCannonState instance = new DoubleCannonState();

    private DoubleCannonState() {
    }

    public static DoubleCannonState getInstance() {
        return instance;
    }

    @Override
    public CopyOnWriteArrayList<Missile> shoot(Missile missile, int gravity) {
        Missile secondMissile = new Missile(missile);
        if (missile.getAngle() - 45 >= Shooter.MIN_ANGLE) {
            secondMissile.setAngle(missile.getAngle() - 45);
        } else if (missile.getAngle() + 45 <= Shooter.MAX_ANGLE) {
            secondMissile.setAngle(missile.getAngle() + 45);
        } else {
            secondMissile.setAngle(missile.getAngle() + 30);
        }
        CopyOnWriteArrayList<Missile> missiles = new CopyOnWriteArrayList<Missile>();
        missiles.add(missile);
        missiles.add(secondMissile);
        return missiles;
    }

}
