package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Missile;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public interface ShootingState {

    public CopyOnWriteArrayList<Missile> shoot(Missile missile, int gravity);
}
