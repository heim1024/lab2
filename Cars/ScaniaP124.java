package Cars;

import java.awt.*;

public final class ScaniaP124 extends LastBil {
    private double flakVinkel;
    public ScaniaP124() {
        super(2, 310, Color.GREEN, "Cars.ScaniaP124", 30, 0, Direction.forward, true);
    }

    @Override
    public boolean getFlak(){
        return flakVinkel != 0;
    }

    public void setFlakVinkel(double vinkel){
        if (vinkel <= 70 && vinkel >= 0 && !isMoving()){
            this.flakVinkel = vinkel;
        }
        else{
            System.out.println("Stanna fordonet eller skriv en vinkel i intervallet 0 - 70");
        }
    }

    @Override
    public double speedFactor() {
        return getEnginePower() * 0.006;
    }

    public void move(){
        if (getFlak()){
            super.move();
        }
        else{
            System.out.println("Flaket uppfällt, fäll ner flaket för att köra!");
        }
    }

}
