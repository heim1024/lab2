package Cars;

import Cars.Bilar.Saab95;
import Cars.Bilar.ScaniaP124;
import Cars.Bilar.Volvo240;
import Cars.Interfaces.CarFactory;
import Cars.Interfaces.CarObserver;
import Cars.Interfaces.IWorkshop;
import Cars.Factories.ScaniaFactory;
import Cars.Factories.SaabFactory;
import Cars.Factories.VolvoFactory;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    private static final int maxCars = 10;
    private int y = 50;
    private IWorkshop<Volvo240> workshop;
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    private List<CarObserver> observers = new ArrayList<>();

    private final Map<String, CarFactory> carFactories = new HashMap<>();

    //methods:
    public void addObserver(CarObserver obs){
        observers.add(obs);
    }
    private void notifyObservers(){
        for (CarObserver obs : observers){
            obs.update();
        }
    }

    public void addCar() {
        if (cars.size() < maxCars) {
            String[] carModels = carFactories.keySet().toArray(new String[0]);
            String randomCar = carModels[new Random().nextInt(carModels.length)];

            double randomX = new Random().nextDouble() * 700; // ✅ Spawn at random X (0-700)
            Car newCar = carFactories.get(randomCar).createCar(randomX, y); // Pass random X, fixed Y
            cars.add(newCar);
            System.out.println("Added " + newCar.getModelname() + " at X: " + randomX);

            frame.drawPanel.repaint(); // ✅ Force UI to update
        } else {
            System.out.println("Car limit reached!");
        }
    }

    public void removeCar() {
        if (!cars.isEmpty()) {
            int removeIndex = new Random().nextInt(cars.size());
            Car removedCar = cars.remove(removeIndex);
            System.out.println("Removed " + removedCar.getModelname());

            frame.drawPanel.moveIt(-500, -500, frame.drawPanel.getPoint(removedCar)); // ✅ Remove car from UI
            frame.drawPanel.repaint(); // ✅ Force UI update
        } else {
            System.out.println("No cars to remove!");
        }
    }


    public Car createCar(String modelName) {
        CarFactory factory = carFactories.get(modelName);
        if (factory != null) {
            return factory.createCar(new Random().nextInt(800), y);
        }
        throw new IllegalArgumentException("Unknown car model: " + modelName);
    }

    public CarController(IWorkshop<Volvo240> workshop) {
        this.workshop = workshop;

        carFactories.put("Cars.Volvo240", new VolvoFactory());
        carFactories.put("Cars.Saab95", new SaabFactory());
        carFactories.put("Cars.ScaniaP124", new ScaniaFactory());
    }

    public static void main(String[] args) {
        // Instance of this class
        CarView frame = new CarView("CarSim 1.0", null);

        IWorkshop<Volvo240> workshop = new Workshop<>(5, frame);

        CarController cc = new CarController(workshop);

        frame.setController(cc);
        cc.frame = frame;
        cc.addObserver(frame);


        // Start a new view and send a reference of self


        cc.cars.add(cc.createCar("Cars.Volvo240"));
        cc.cars.add(cc.createCar("Cars.Saab95"));
        cc.cars.add(cc.createCar("Cars.ScaniaP124"));

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
                    frame.drawPanel.moveIt(x, y, frame.drawPanel.getPoint(car));

                    if (workshop.isNearWorkshop(car) && car instanceof Volvo240 && !workshop.isFull()){
                        workshop.workShopStore((Volvo240) car);
                    }
                    // Reverse direction at screen limits
                    if ((car.getY() > frame.getY() - 65 && car.getDirection() == Car.Direction.forward)
                            || (car.getY() < 20 && car.getDirection() == Car.Direction.back)) {
                        car.setDir();
                    }
                }
            }
            notifyObservers(); // Ensure UI updates **after** the loop
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
