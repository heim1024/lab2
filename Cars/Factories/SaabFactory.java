package Cars.Factories;

import Cars.Car;
import Cars.Interfaces.CarFactory;
import Cars.Bilar.Saab95;

public class SaabFactory implements CarFactory {
    @Override
    public Car createCar(double x, double y) {
        return new Saab95(x, y);
    }
}
