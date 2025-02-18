package Cars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

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

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new ScaniaP124());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    public Workshop<Volvo240> volvo240Workshop = new Workshop<>(5);

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                if (!car.getLoaded()){
                    car.move();
                    int x = (int) Math.round(car.getX());
                    int y = (int) Math.round(car.getY());
                    frame.drawPanel.moveit(x, y, frame.drawPanel.getPoint(car));

                    // Check if Volvo is near the workshop
                    if (car instanceof Volvo240) {
                        if (isNearWorkshop(car)) {
                            car.setDir();
                            volvo240Workshop.park((Volvo240) car);
                            System.out.println("Volvo stored in Workshop!");
                            car.setLoaded(true);
                            frame.drawPanel.moveit(-200,-200, frame.drawPanel.getPoint(car));
                        }
                    }

                    // Reverse direction if at screen limits
                    if ((car.getY() > frame.getY() - 65 && car.getDirection() == Car.Direction.forward)
                            || (car.getY() < 20 && car.getDirection() == Car.Direction.back)) {
                        car.setDir();
                    }
                }
            }
            frame.drawPanel.repaint(); // Ensure UI updates **after** the loop
        }
    }



    private boolean isNearWorkshop(Car car) {
        int workshopX = 300; // Adjust these based on `DrawPanel` coordinates
        int workshopY = 300;
        int threshold = 50; // Collision sensitivity range

        return Math.abs(car.getX() - workshopX) < threshold && Math.abs(car.getY() - workshopY) < threshold;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    public void startEngine(){
        for (Car car : cars){
            car.startEngine();
        }
    }

    public void brake(int brakeAmount){
        for (Car car : cars){
            car.brake(brakeAmount);
        }
    }

    public void setFlak(boolean lift) {
        for (Car car : cars) {
            if (car instanceof LastBil) {
                ((LastBil) car).setFlak(lift);
            }
        }
    }

}
