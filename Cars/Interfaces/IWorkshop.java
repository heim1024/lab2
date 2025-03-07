package Cars.Interfaces;

import Cars.Car;

public interface IWorkshop <T extends Car> {
    boolean isNearWorkshop(Car car);
    boolean isFull();
    void park(T parkCar);
    void unPark(T unParkCar);
    void workShopStore(T car);
}

