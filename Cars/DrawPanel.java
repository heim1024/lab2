package Cars;

import Cars.Car;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// This panel represents the animated part of the view with the car images.
public class DrawPanel extends JPanel {

    private final Map<Car, Point> carPositions = new HashMap<>(); // ✅ Dynamic car positions
    private final Map<String, BufferedImage> carImages = new HashMap<>(); // ✅ Stores car images
    private BufferedImage workshopImage;
    private final Point workshopPoint = new Point(300, 300); // ✅ Fixed workshop position

    // ✅ Constructor: Initializes panel and loads images dynamically
    public DrawPanel(int width, int height) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.green);

        // Load the workshop image
        try {
            workshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("../pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    public void addCar(Car car) {
        if (!carPositions.containsKey(car)) {
            Point carSpawnPoint = new Point((int) car.getX(), (int) car.getY()); // ✅ Ensure X and Y are correct
            carPositions.put(car, carSpawnPoint);

            // Load image dynamically based on car model
            if (!carImages.containsKey(car.getModelname())) {
                try {
                    BufferedImage carImage;
                    if (car.getModelname().equals("Cars.Volvo240")){
                        carImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../pics/Volvo240.jpg")));
                    } else if (car.getModelname().equals("Cars.Saab95")) {
                        carImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../pics/Saab95.jpg")));
                    } else if (car.getModelname().equals("Cars.ScaniaP124")) {
                        carImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../pics/Scania.jpg")));
                    }else{
                        carImage = null;
                    }
                    carImages.put(car.getModelname(), carImage);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            repaint();
        }
    }


    public void removeCar(Car car) {
        if (carPositions.containsKey(car)) {
            carPositions.remove(car); // Remove car from rendering
            repaint();
        }
    }

    // ✅ Move a car to a new position
    public void moveIt(int x, int y, Car car) {
        Point carPoint = getPoint(car); // ✅ Get the stored reference
        if (carPoint != null) { // ✅ Ensure the car exists in the map
            carPoint.setLocation(x, y); // ✅ Update the existing reference, not replace it
            repaint();
        }
    }




    // ✅ Retrieves the position of a given car
    public Point getPoint(Car car) {
        return carPositions.computeIfAbsent(car, c -> new Point((int) car.getX(), (int) car.getY()));
    }




    // ✅ Retrieves the workshop position
    public Point getWorkshopPoint(String name) {
        if ("volvo240Workshop".equals(name)) {
            return workshopPoint;
        }
        return new Point(-100, -100); // Default off-screen
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw workshop
        if (workshopImage != null) {
            g.drawImage(workshopImage, workshopPoint.x, workshopPoint.y, null);
        }

        // Draw all cars dynamically
        for (Map.Entry<Car, Point> entry : carPositions.entrySet()) {
            Car car = entry.getKey();
            Point position = entry.getValue();
            BufferedImage carImage = carImages.get(car.getModelname());

            if (carImage != null) {
                g.drawImage(carImage, position.x, position.y, null);
            }
        }
    }
}
