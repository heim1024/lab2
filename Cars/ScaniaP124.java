package Cars;

import java.awt.*;

public final class ScaniaP124 extends LastBil {
    public ScaniaP124() {
        super(2, 310, Color.GREEN, "Cars.ScaniaP124", 10, 0, Direction.forward, true);
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
