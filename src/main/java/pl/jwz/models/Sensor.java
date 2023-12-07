package pl.jwz.models;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class Sensor {
    private double defaultAngle;
    private double angle;
    private double distance;
    private boolean visible;
    private boolean center;
    private int carHeight;
    private double startX;
    private double startY;

    public Sensor(double angle, boolean center, int carHeight) {
        this.defaultAngle = angle;
        this.distance = 0;
        this.visible = true;
        this.center = center;
        this.carHeight = carHeight;
    }

    public void update(double carX, double carY, BufferedImage trackImage, double carRotation) {
        angle = defaultAngle + carRotation;
        if (center) {
            startX = carX + (double) (carHeight /2) * Math.cos(angle);
            startY = carY + (double) (carHeight /2) * Math.sin(angle);
        } else {
            startX = carX + (((double) carHeight * Math.sqrt(2)) /2) * Math.cos(angle);
            startY = carY + (((double) carHeight * Math.sqrt(2)) /2) * Math.sin(angle);
        }
        double d = 0;
        int stepSize = 1;

        while (d >= 0) {
            int x = (int) (startX + d * Math.cos(angle));
            int y = (int) (startY + d * Math.sin(angle));

            if (x >= 0 && x < trackImage.getWidth() && y >= 0 && y < trackImage.getHeight()) {
                int pixel = trackImage.getRGB(x, y);

                if (pixel == Color.BLACK.getRGB()) {
                    distance = d;
                    return;
                }

                d += stepSize;
            }
        }

        distance = d;
    }

    public void draw(Graphics2D g2d, double carX, double carY, int carHeight, int carWidth) {
        if (visible) {

            int sensorEndX = (int) (startX + distance * Math.cos(angle));
            int sensorEnY = (int) (startY + distance * Math.sin(angle));

            g2d.setColor(Color.RED);
            g2d.drawLine((int) startX, (int) startY, sensorEndX, sensorEnY);
        }
    }
}
