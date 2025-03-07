package Cars.Factories;

import Cars.Car;
import Cars.Interfaces.CarFactory;
import Cars.Bilar.ScaniaP124;

public class ScaniaFactory implements CarFactory {
    @Override
    public Car createCar(double x, double y) {
        return new ScaniaP124(x, y);
    }
}
