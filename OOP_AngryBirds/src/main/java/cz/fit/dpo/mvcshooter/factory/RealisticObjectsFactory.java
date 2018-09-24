package cz.fit.dpo.mvcshooter.factory;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.RealisticEnemy;
import cz.fit.dpo.mvcshooter.strategy.RealisticMissileStrategy;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class RealisticObjectsFactory extends AbstractGraphicsObjectFactory {

    private static final RealisticObjectsFactory instance = new RealisticObjectsFactory();

    private RealisticObjectsFactory() {
    }

    public static RealisticObjectsFactory getInstance() {
        return instance;
    }

    @Override
    public Missile createMissile() {
        return new Missile(new RealisticMissileStrategy());
    }

    @Override
    public Enemy createEnemy() {
        return new RealisticEnemy();
    }

}
