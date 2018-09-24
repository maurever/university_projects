/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.dpo.mvcshooter.factory;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public abstract class AbstractGraphicsObjectFactory {
    
    public abstract Missile createMissile();

    public abstract Enemy createEnemy();

}
