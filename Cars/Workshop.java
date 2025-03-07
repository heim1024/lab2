package Cars;

import Cars.Interfaces.IWorkshop;

import java.util.ArrayList;

public class Workshop<T extends Car> implements IWorkshop<T> {
    private int max;
    CarView frame;
    public ArrayList<T> carList = new ArrayList<>(max);
    private int availableSpace;
    public Workshop(int availableSpace, CarView frame){
        this.availableSpace = availableSpace;
        this.max = availableSpace;
        this.carList = new ArrayList<>();
        this.frame = frame;
    }
    @Override
    public void workShopStore(Car car) {
        if (isNearWorkshop(car)) {
            if (car != null){
                car.storeCar();
                park((T) car);
                System.out.println("Car stored in Workshop!");
                frame.drawPanel.moveIt(-200, -200, frame.drawPanel.getPoint(car));
            }
            else{
                System.out.println("car is null");
            }
        }
    }

    @Override
    public boolean isNearWorkshop(Car car) {
        if (frame == null) {
            System.out.println("Error: Frame is not initialized.");
            return false;
        }

        int workshopX = frame.drawPanel.getWorkshopPoint("volvo240Workshop").x;
        int workshopY = frame.drawPanel.getWorkshopPoint("volvo240Workshop").y;
        int threshold = 50; // Collision range

        return Math.abs(car.getX() - workshopX) < threshold &&
                Math.abs(car.getY() - workshopY) < threshold;
    }

    @Override
    public void park(T parkCar){
        if (availableSpace > 0){
        carList.add(parkCar);
        availableSpace --;
        }
        else{
            System.out.println("Not enough space in the workshop!");
        }
    }
    @Override
    public void unPark(T unParkCar){
        if (carList.contains(unParkCar)){
            carList.remove(unParkCar);
            availableSpace++;
        }
        else {
            System.out.println("Car not in inventory");
        }
    }

    @Override
    public boolean isFull() {
        return availableSpace == 0;
    }

    public ArrayList<T> getCars(){
        return carList;
    }
}
