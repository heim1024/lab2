package Cars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:
    Workshop<Volvo240> volvo240Workshop;

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        cc.volvo240Workshop = new Workshop<>(5, cc.frame);

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new ScaniaP124());

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */


    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                if (!car.getLoaded()){
                    car.move();
                    int x = (int) Math.round(car.getX());
                    int y = (int) Math.round(car.getY());
                    frame.drawPanel.moveit(x, y, frame.drawPanel.getPoint(car));

                    if (volvo240Workshop.isNearWorkshop(car) && car instanceof Volvo240 && !volvo240Workshop.isFull()){
                        volvo240Workshop.workShopStore(car);
                    }
                    // Reverse direction at screen limits
                    if ((car.getY() > frame.getY() - 65 && car.getDirection() == Car.Direction.forward)
                            || (car.getY() < 20 && car.getDirection() == Car.Direction.back)) {
                        car.setDir();
                    }
                }
            }
            frame.drawPanel.repaint(); // Ensure UI updates **after** the loop
        }
    }



    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            if(!car.getLoaded()){
                car.gas(gas);
            }
        }
    }
    void brake(int brakeAmount){
        for (Car car : cars){
            car.brake(brakeAmount);
        }
    }

    void startEngine(){
        for (Car car : cars){
            car.startEngine();
        }
    }
    void stopEngine(){
        for (Car car : cars){
            car.stopEngine();
        }
    }

    void setFlak(boolean lift) {
        for (Car car : cars) {
            if (car instanceof LastBil) {
                ((LastBil) car).setFlak(lift);
            }
        }
    }

    void setTurboOn(){
        for (Car car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }
    void setTurboOff(){
        for (Car car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

}
