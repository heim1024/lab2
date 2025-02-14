package Cars;

import java.awt.*;

public final class Volvo240 extends Car {
    private final static double trimFactor = 1.25;
    public Volvo240(){
        super(4, 100, Color.black, "Cars.Volvo240", 10, 0, Direction.forward, true, false);
        stopEngine();
    }

    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}

