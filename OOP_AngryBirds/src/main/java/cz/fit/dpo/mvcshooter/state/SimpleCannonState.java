package cz.fit.dpo.mvcshooter.state;

import cz.fit.dpo.mvcshooter.model.Missile;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class SimpleCannonState implements ShootingState {
    
    private static final SimpleCannonState instance = new SimpleCannonState();
    
    public SimpleCannonState() {
    }
    
    public static SimpleCannonState getInstance() {
        return instance;
    }
    
    @Override
    public CopyOnWriteArrayList<Missile> shoot(Missile missile, int gravity) {
        CopyOnWriteArrayList<Missile> missiles = new CopyOnWriteArrayList<Missile>();
        missiles.add(missile);
        return missiles;
    }
    
}
