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

    private final Map<Car, Point> carPositions = new HashMap<>();
    private final Map<String, BufferedImage> carImages = new HashMap<>();
    private BufferedImage workshopImage;
    private final Point workshopPoint = new Point(300, 300);

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
            Point carSpawnPoint = new Point((int) car.getX(), (int) car.getY());
            carPositions.put(car, carSpawnPoint);

            // Load image dynamically based on car model
            if (!carImages.containsKey(car.getModelname())) {
                try {
                    BufferedImage carImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("../pics/" + car.getModelname() + ".jpg")));
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

    public void moveIt(int x, int y, Car car) {
        Point carPoint = getPoint(car);
        if (carPoint != null) {
            carPoint.setLocation(x, y);
            repaint();
        }
    }

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
