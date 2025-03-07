package Cars;

import Cars.Bilar.Saab95;
import Cars.Bilar.Scania114L300;
import Cars.Bilar.ScaniaP124;
import Cars.Bilar.Volvo240;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCars {
    public Volvo240 vovvo;
    public Saab95 saab;
    public Workshop<Volvo240> volvoWorkShop;
    public Workshop<Car> workshop;
    public ScaniaP124 scaniaP124;
    public Scania114L300 scania114L300;
    public CarView frame;
    @Before
    public void setUp(){
        vovvo = new Volvo240(100, 100);
        saab = new Saab95(100, 100);
        volvoWorkShop = new Workshop<Volvo240>(10, frame);
        workshop = new Workshop<>(10, frame);
        scaniaP124 = new ScaniaP124(100, 100);
        scania114L300 = new Scania114L300();
    }

    @Test
    public void doorsTest() {
        assertEquals(4, vovvo.getNrDoors());
        assertEquals(2, saab.getNrDoors());
    }

    @Test
    public void engineTest(){
        assertEquals(100, vovvo.getEnginePower());
        assertEquals(125, saab.getEnginePower());
    }

    @Test
    public void moveTest(){
        //Volvo240
        vovvo.startEngine();
        assertTrue(vovvo.isMoving());
        //Saab95
        saab.startEngine();
        assertTrue(saab.isMoving());
    }

    @Test
    public void gasTest(){
        //Volvo240
        vovvo.startEngine();
        for(int i = 0; i < 500; i++){
            vovvo.gas(1);
        }
        assertTrue(vovvo.getEnginePower() >= vovvo.getCurrentSpeed());

        //Saab95
        saab.startEngine();
        for(int i = 0; i < 500; i++){
            saab.gas(1);
        }
        assertTrue(saab.getEnginePower() >= saab.getCurrentSpeed());
    }

    @Test
    public void brakeTest(){
        //Volvo240
        for(int i = 0; i < 500; i++){
            vovvo.brake(1);
        }
        assertTrue(0 <= vovvo.getCurrentSpeed());

        //Saab95
        for(int i = 0; i < 500; i++){
            saab.brake(1);
        }
        assertTrue(0 <= saab.getCurrentSpeed());
    }

    @Test
    public void turnTest(){
        //Volvo240
        vovvo.turnLeft();
        assertSame(Car.Direction.left, vovvo.getDirection());
        vovvo.turnRight();
        assertSame(Car.Direction.right, vovvo.getDirection());
        vovvo.move();
        assertSame(Car.Direction.forward, vovvo.getDirection());

        //Saab95
        saab.turnLeft();
        assertSame(Car.Direction.left, saab.getDirection());
        saab.turnRight();
        assertSame(Car.Direction.right, saab.getDirection());
        saab.move();
        assertSame(Car.Direction.forward, saab.getDirection());
    }

    @Test
    public void speedFactorTest(){
        assertEquals(1.25, vovvo.speedFactor(), 0.0);
        saab.setTurboOn();
        assertEquals(1.625, saab.speedFactor(), 0.0);
    }

    @Test
    public void parkTest(){
        volvoWorkShop.park(vovvo);
        workshop.park(saab);
        workshop.park(vovvo);
    }

    @Test
    public void lastTest(){
        scania114L300.setFlak(false);
        scania114L300.lastaBil(vovvo);
        scania114L300.lastaBil(scaniaP124);
    }

}
