package cz.fit.dpo.mvcshooter.factory;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.SimpleEnemy;
import cz.fit.dpo.mvcshooter.strategy.SimpleMissileStrategy;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class SimpleObjectsFactory extends AbstractGraphicsObjectFactory {

    private static final SimpleObjectsFactory instance = new SimpleObjectsFactory();

    private SimpleObjectsFactory() {
    }

    public static SimpleObjectsFactory getInstance() {
        return instance;
    }

    @Override
    public Missile createMissile() {
        return new Missile(new SimpleMissileStrategy());
    }

    @Override
    public Enemy createEnemy() {
        return new SimpleEnemy();
    }

}
