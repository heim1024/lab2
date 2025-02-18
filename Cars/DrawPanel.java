package Cars;

import Cars.CarController;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    // To keep track of a single car's position
    Point volvoPoint = new Point(20, 0);
    Point NaaaaazPoint;

    BufferedImage saabImage;
    Point saabPoint = new Point(50, 0);

    BufferedImage scaniap124Image;
    Point scaniap124Point = new Point(100, 0);

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    private CarController carController;

    // TODO: Make this general for all cars
    void moveit(int x, int y, Point carPoint){
        carPoint.x = x;
        carPoint.y = y;
    }

    public Point getPoint(Car car){
        return switch (car.getModelname()) {
            case "Cars.Volvo240" -> volvoPoint;
            case "Cars.Saab95" -> saabPoint;
            case "Cars.ScaniaP124" -> scaniap124Point;
            default -> NaaaaazPoint;
        };

    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        this.carController = carController;
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/VolvoBrand.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/Saab95.jpg"));
            scaniap124Image = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/Scania.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        g.drawImage(saabImage, saabPoint.x, saabPoint.y, null);
        g.drawImage(scaniap124Image, scaniap124Point.x, scaniap124Point.y, null);
        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null);

    }

}
