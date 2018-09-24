package cz.fit.dpo.mvcshooter.tests.missilesTests;

import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.strategy.MissileMovementStrategy;
import cz.fit.dpo.mvcshooter.strategy.RealisticMissileStrategy;
import cz.fit.dpo.mvcshooter.strategy.SimpleMissileStrategy;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class MissileStrategyTest {

    int inicialX = 50;
    int inicialY = 450;
    int gravity = 10;
    int speed = 70;
    int timer = 20;
    double angle = Math.toRadians(45);
    int expectedSimpleX = 148;
    //int expectedSimpleX = (int) (inicialX + speed * timer * 0.1 * Math.cos(angle));
    int expectedSimpleY = 351;
    //int expectedSimpleY = (int) (inicialY - speed * timer * 0.1 * Math.sin(angle));
    int expectedRealisticX = 148;
    //int expectedRealisticX = (int) (inicialX + speed * timer * 0.1 * Math.cos(angle));
    int expectedRealisticY = 371;
    //int expectedRealisticY = (int) (inicialY - speed * timer * 0.1 * Math.sin(angle) + 0.5 * gravity * timer * 0.1 * timer * 0.1);
    Missile missile = new Missile();

    @Before
    public void getSetMissile() {
        missile.setInicialX(inicialX);
        missile.setInicialY(inicialY);
        missile.setGravity(gravity);
        missile.setSpeed(speed);
        missile.setAngle(angle);
        missile.setTimer(timer);
    }

    @Test
    public void delegatesToSimpleStrategyCorrectly() {
        MissileMovementStrategy strategyMock = mock(SimpleMissileStrategy.class);
        Missile testMissile = new Missile();
        testMissile.setStrategy(strategyMock);
        missile.setStrategy(new SimpleMissileStrategy());
        when(strategyMock.getNewX(any(Missile.class))).thenReturn(expectedSimpleX);
        when(strategyMock.getNewY(any(Missile.class))).thenReturn(expectedSimpleY);
        missile.move();
        assertEquals(strategyMock.getNewX(testMissile), missile.getX());
        assertEquals(strategyMock.getNewY(testMissile), missile.getY());
    }

    @Test
    public void delegatesToRealisticStrategyCorrectly() {
        MissileMovementStrategy strategyMock = mock(RealisticMissileStrategy.class);
        Missile testMissile = new Missile();
        testMissile.setStrategy(strategyMock);
        missile.setStrategy(new RealisticMissileStrategy());
        when(strategyMock.getNewX(any(Missile.class))).thenReturn(expectedRealisticX);
        when(strategyMock.getNewY(any(Missile.class))).thenReturn(expectedRealisticY);
        missile.move();
        assertEquals(strategyMock.getNewX(testMissile), missile.getX());
        assertEquals(strategyMock.getNewY(testMissile), missile.getY());
    }

}
