package cz.fit.dpo.mvcshooter.strategy;

import cz.fit.dpo.mvcshooter.model.Missile;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class SimpleMissileStrategy implements MissileMovementStrategy {

    @Override
    public int getNewX(Missile missile) {
        double time = missile.getTimer() * 0.1;
        return (int) (missile.getInicialX() + missile.getSpeed() * time * Math.cos(missile.getAngle()));
    }

    @Override
    public int getNewY(Missile missile) {
        double time = missile.getTimer() * 0.1;
        return (int) (missile.getInicialY() - missile.getSpeed() * time * Math.sin(missile.getAngle()));
    }
}
