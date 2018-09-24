package cz.fit.dpo.mvcshooter.tests.copy;

import cz.fit.dpo.mvcshooter.model.RealisticEnemy;
import cz.fit.dpo.mvcshooter.model.SimpleEnemy;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class CopyMethodTest {

    @Test
    public void testSimpleEnemyCopy() {
        SimpleEnemy enemy = new SimpleEnemy(10, 10, 2);
        SimpleEnemy enemyDeepCopy = (SimpleEnemy) enemy.copy();
        assertNotSame(enemy, enemyDeepCopy);
        assertEquals(enemy.getX(), enemy.getX());
        assertEquals(enemy.getY(), enemy.getY());
        assertEquals(enemy.getType(), enemy.getType());
    }

    @Test
    public void testRealisticEnemyCopy() {
        RealisticEnemy enemy = new RealisticEnemy(10, 10, 2, 1000);
        RealisticEnemy enemyDeepCopy = (RealisticEnemy) enemy.copy();
        assertNotSame(enemy, enemyDeepCopy);
        assertEquals(enemy.getX(), enemy.getX());
        assertEquals(enemy.getY(), enemy.getY());
        assertEquals(enemy.getType(), enemy.getType());
        assertEquals(enemy.getTimer(), enemy.getTimer());
    }

}
