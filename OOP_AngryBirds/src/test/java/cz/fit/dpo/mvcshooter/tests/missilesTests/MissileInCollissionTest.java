package cz.fit.dpo.mvcshooter.tests.missilesTests;

import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.Missile;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class MissileInCollissionTest {

    int collissionX = 130;
    int collissionY = 200;
    int tolleration = 20;
    Missile missile = new Missile();

    @Before
    public void setMissile() {
        missile.setX(collissionX);
        missile.setY(collissionY);
    }

    @Test
    public void isMissileInCollissionFloorTrue() {
        Enemy enemyMock = mock(Enemy.class);
        when(enemyMock.getX()).thenReturn(collissionX - tolleration);
        when(enemyMock.getY()).thenReturn(collissionY - tolleration);
        assertTrue(missile.isInCollision(enemyMock));
    }

    @Test
    public void isMissileInCollissionCeilTrue() {
        Enemy enemyMock = mock(Enemy.class);
        when(enemyMock.getX()).thenReturn(collissionX + tolleration);
        when(enemyMock.getY()).thenReturn(collissionY + tolleration);
        assertTrue(missile.isInCollision(enemyMock));
    }

    @Test
    public void isMissileInCollissionFalse() {
        Enemy enemyMock = mock(Enemy.class);
        when(enemyMock.getX()).thenReturn(collissionX - tolleration + 42);
        when(enemyMock.getY()).thenReturn(collissionY - tolleration + 42);
        assertFalse(missile.isInCollision(enemyMock));
    }
}
