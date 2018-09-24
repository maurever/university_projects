package cz.fit.dpo.mvcshooter.strategy;

import cz.fit.dpo.mvcshooter.model.Missile;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public interface MissileMovementStrategy {

    public int getNewX(Missile missile);

    public int getNewY(Missile missile);

}
