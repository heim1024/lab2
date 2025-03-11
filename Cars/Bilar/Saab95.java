package Cars.Bilar;

import Cars.Car;

import java.awt.*;

public final class Saab95 extends Car {
    private boolean turboOn;
    public Saab95(double x, double y){
        super(2, 125, Color.red, "Saab95", 0, 0, Direction.forward, true, false);
	    turboOn = false;
        stopEngine();
    }

    public void setTurboOn(){
        turboOn = true;
    }
    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

}
