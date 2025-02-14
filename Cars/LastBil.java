package Cars;

import java.awt.*;
import java.util.ArrayList;

public abstract class LastBil extends Car{
    private boolean flakUp = true;
    public LastBil(int nrDoors, int enginePower, Color color, String modelName, double startX, double startY, Direction startDir, boolean flakUp) {
        super(nrDoors, enginePower, color, modelName, startX, startY, startDir, false, false);
    }

    public boolean getFlak(){
        return flakUp;
    }
    public void setFlak(boolean flak){
        if(!isMoving()){
            flakUp = flak;
        }
        else{
            System.out.println("stanna innan du justerar flaket!");
        }
    }


}


