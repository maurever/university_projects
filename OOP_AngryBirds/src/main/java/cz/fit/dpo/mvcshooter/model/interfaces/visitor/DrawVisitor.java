package cz.fit.dpo.mvcshooter.model.interfaces.visitor;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Collision;
import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public interface DrawVisitor {
    public void visitMissile(Missile missile);
    public void visitEnemy(Enemy enemy);
    public void visitCannon(Cannon cannon);
    public void visitCollision(Collision collision);
}
