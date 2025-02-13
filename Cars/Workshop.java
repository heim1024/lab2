package Cars;

import java.util.ArrayList;

public class Workshop<T extends Car> {
    private int max;
    public ArrayList<T> carList = new ArrayList<>(max);
    private int availableSpace;
    public Workshop(int availableSpace){
        this.availableSpace = availableSpace;
        max = availableSpace;
    }

    public void park(T parkCar){
        if (availableSpace > 0){
        carList.add(parkCar);
        availableSpace --;
        }
        else{
            System.out.println("Not enough space in the workshop!");
        }
    }

    public void unPark(T unParkCar){
        if (carList.contains(unParkCar)){
            carList.remove(unParkCar);
            availableSpace++;
        }
        else {
            System.out.println("Car not in inventory");
        }
    }

}
