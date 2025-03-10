package Cars.Bilar;

import Cars.Car;
import Cars.LastBil;

import java.awt.*;
import java.util.ArrayList;

public final class Scania114L300 extends LastBil {
    public Scania114L300() {
        super(2, 500, Color.MAGENTA, "Cars.Scania114L300", 0, 0, Direction.forward, true);

    }

    private int lastNr = 0;
    private ArrayList<Car> lastList = new ArrayList<>(10);

    public void lastaBil(Car car){
        if(!getFlak() && car.getLoadable()){
            lastList.get(lastNr).setLoaded(true);
            lastList.add(lastNr, car);
            lastList.get(lastNr).setX(getX());
            lastList.get(lastNr).setY(getY());
            lastNr++;
        }
        else {
            System.out.println("unable to lasta");
        }
    }

    public void lastaAvBil(){
        if(!getFlak()){
            lastList.get(lastNr).setY(getY() - (10 * lastNr));
            lastList.get(lastNr).setLoaded(false);
            lastList.remove(lastNr);
            lastNr--;
        }
        else {
            System.out.println("unable to lasta av");
        }
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.005;
    }

    @Override
    public void move(){
        super.move();
        for (Car car : lastList) {
            car.setX(getX());
            car.setY(getY());
        }
    }
}