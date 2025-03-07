package Cars;

import Cars.Interfaces.Movable;

import java.awt.*;


public abstract class Car implements Movable {
    private final int nrDoors; // Number of doors on the Cars.car
    private final int enginePower; // Engine power of the Cars.car
    private final String modelName; // The Cars.car model name
    private boolean engineOn = false;
    private double currentSpeed; // The current speed of the Cars.car
    private double x, y; // Position coordinates
    private Color color; // Color of the Cars.car
    private Direction direction; // Direction the car is facing
    private boolean loadable;
    private boolean loaded;

    public abstract double speedFactor();

    public enum Direction {
        forward, back, right, left
    }

    public Car(int nrDoors, int enginePower, Color color, String modelName, double x, double y, Direction startDir, boolean loadable, boolean loaded){
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.currentSpeed = 0;
        this.direction = startDir;
        this.x = x;
        this.y = y;
        this.loadable = loadable;
        this.loaded = loaded;
    }

    public void storeCar(){
        setDir();
        setLoaded(true);
    }

    private boolean isNearWorkshop(Car car) {
        int workshopX = 300; // Adjust these based on `DrawPanel` coordinates
        int workshopY = 300;
        int threshold = 50; // Collision sensitivity range

        return Math.abs(car.getX() - workshopX) < threshold && Math.abs(car.getY() - workshopY) < threshold;
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public int getEnginePower(){
        return enginePower;
    }
    public Direction getDirection(){return direction;}
    public String getModelname(){return modelName;}
    public Color getColor(){return color;}
    public void setColor(Color clr){color = clr;}

    public double getX() {return x;}
    public double getY() {return y;}



    public void setDir(){
        for (int i = 0; i < 100; i++){
            brake(1);
        }
        if (direction == Direction.forward){
            direction = Direction.back;
        }
        else {
            direction = Direction.forward;
        }
    }

    public boolean getLoadable(){return loadable;}

    public void setX(double posX){
        if (getLoaded()){
            this.x = posX;
        }else{
            System.out.println("load you car first");
        }
    }

    public void setY(double posY){
        if (getLoaded()){
            this.y = posY;
        }else{
            System.out.println("load you car first");
        }
    }

    public void setLoaded(boolean bool){
        loaded = bool;
        stopEngine();
    }
    public boolean getLoaded(){
        return loaded;
    }

    public boolean isMoving(){
        return currentSpeed > 0;
    }

    public void startEngine(){
        if (!getLoaded()){
            currentSpeed = 0.1;
            engineOn = true;
        }else{
            System.out.println("unload your car first");
        }

    }
    public void stopEngine(){
        currentSpeed = 0;
        engineOn = false;
    }

    public double getCurrentSpeed(){return currentSpeed;}

    // TODO fix this method according to lab pm
    public void gas(double amount){
        if (amount <= 1 && amount >= 0){
            incrementSpeed(amount);
        }
        else{
            System.out.println("input a value between 0 and 1!");
        }
    }
    // TODO fix this method according to lab pm
    public void brake(double amount){
        if (amount <= 1 && amount >= 0){
            decrementSpeed(amount);
        }
        else{
            System.out.println("input a value between 0 and 1!");
        }
    }

    private void incrementSpeed(double amount){
        if(engineOn){
            currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        }
        else{
            System.out.println("turn on your engine first");
        }
    }
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    @Override
    public void move() {
        if(direction == Direction.forward){
            y += currentSpeed;
        } else if (direction == Direction.back) {
            y -= currentSpeed;
        } else if (direction == Direction.right) {
            x += currentSpeed;
        } else if (direction == Direction.left) {
            x -= currentSpeed;
        }
    }

    @Override
    public void turnLeft(){
        direction = Direction.left;
    }

    @Override
    public void turnRight(){
        direction = Direction.right;
    }



}
