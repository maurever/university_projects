package cz.fit.dpo.mvcshooter.tests.copy;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Missile;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class CopyContstructorTest {
    
    @Test
    public void testCannonCopy() {
        Cannon cannon = new Cannon(10, 20, 45, 45);
        Cannon cannonDeepCopy = new Cannon(cannon);
        Cannon cannonShallowCopy = cannon;
        assertEquals(cannon, cannonShallowCopy);
        assertNotSame(cannon, cannonDeepCopy);
        assertEquals(cannon.getX(), cannonDeepCopy.getX());
        assertEquals(cannon.getY(), cannonDeepCopy.getY());
        assertEquals(cannon.getAngleDegrees(), cannonDeepCopy.getAngleDegrees());
    }
    
    @Test
    public void testMissileCopy() {
        Missile missile = new Missile();
        missile.setInicialX(20);
        missile.setInicialY(40);
        missile.setGravity(70);
        missile.setSpeed(70);
        missile.setAngle(Math.toRadians(45));
        missile.setTimer(30);
        Missile missileDeepCopy = new Missile(missile);
        assertNotSame(missile, missileDeepCopy);
        assertEquals(missile.getX(), missile.getX());
        assertEquals(missile.getY(), missile.getY());
        assertEquals(missile.getInicialX(), missile.getInicialX());
        assertEquals(missile.getInicialY(), missile.getInicialY());
        assertEquals(missile.getSpeed(), missile.getSpeed());
        assertEquals(missile.getGravity(), missile.getGravity());
    }
    
}
