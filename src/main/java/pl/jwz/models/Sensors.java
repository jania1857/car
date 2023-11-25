package pl.jwz.models;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class Sensors {

    private int centerSensorLength;
    private int leftSensorLength;
    private int rightSensorLength;
    private boolean sensorsVisibility;
    private final Car car;
    private final int carWidth;
    private final int carHeight;
    private final double carRotation;
    private final int middleOfCarX;
    private final int middleOfCarY;
    private boolean leftCollision;

    public Sensors(Car car) {
        this.car = car;
        this.carWidth = car.getCarWidth();
        this.carHeight = car.getCarHeight();
        this.carRotation = car.getRotation();
        this.middleOfCarX = (int) (car.getX() + carWidth / 2);
        this.middleOfCarY = (int) (car.getY() + carHeight / 2);
        this.centerSensorLength = 60;
        this.leftSensorLength = 60;
        this.rightSensorLength = 60;
        this.sensorsVisibility = true;
    }

    public void createSensors(Graphics2D graphics) {
        createCenterSensor(graphics);
        createLeftSensor(graphics);
        createRightSensor(graphics);
    }

    private void createCenterSensor(Graphics2D g) {
        rotateSensors(g);
        setVisible(g);
        g.drawLine(middleOfCarX, middleOfCarY - carHeight / 2, middleOfCarX, middleOfCarY - centerSensorLength);
    }

    private void createLeftSensor(Graphics2D g) {
        int x2 = (int) (middleOfCarX + leftSensorLength * Math.cos(180));
        int y2 = (int) (middleOfCarY + leftSensorLength * Math.sin(180));
        setVisible(g);
        g.drawLine(middleOfCarX - carWidth / 2, middleOfCarY - carHeight / 2, x2, y2);

       // setLeftCollision(checkSensorCollision( x2, y2));
    }

    private void createRightSensor(Graphics2D g) {
        int x2 = (int) (middleOfCarX + rightSensorLength * Math.cos(-45));
        int y2 = (int) (middleOfCarY + rightSensorLength * Math.sin(-45));
        setVisible(g);
        g.drawLine(middleOfCarX + carWidth / 2, middleOfCarY - carHeight / 2, x2, y2);
    }

    private void rotateSensors(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(carRotation, middleOfCarX, middleOfCarY);
        g.setTransform(transform);
    }

    private void setVisible(Graphics2D g) {
        if (sensorsVisibility) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.WHITE);
        }
    }

    private boolean checkSensorCollision(BufferedImage trackImage, int x, int y) {
        int color = trackImage.getRGB(x, y);
        return color == Color.BLACK.getRGB();
    }
}