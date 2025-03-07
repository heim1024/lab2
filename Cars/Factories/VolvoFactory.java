package Cars.Factories;

import Cars.Car;
import Cars.Interfaces.CarFactory;
import Cars.Bilar.Volvo240;

public class VolvoFactory implements CarFactory {
    @Override
    public Car createCar(double x, double y) {
        return new Volvo240(x, y);
    }
}
