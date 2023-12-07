package pl.jwz.models;

import lombok.Getter;
import lombok.Setter;
import pl.jwz.ModifierSensors;

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
    private Car car;
    private ModifierSensors modifierSensors;
    private final int carWidth;
    private final int carHeight;
    private final double carRotation;
    private final int middleOfCarX;
    private final int middleOfCarY;
    private boolean leftCollision;
  //  private static Sensors instance;

    public Sensors(Car car, ModifierSensors modifierSensors) {
        this.car = car;
        this.carWidth = car.getCarWidth();
        this.carHeight = car.getCarHeight();
        this.carRotation = car.getRotation();
        this.middleOfCarX = (int) (car.getX() + carWidth / 2);
        this.middleOfCarY = (int) (car.getY() + carHeight / 2);
        this.centerSensorLength = 60;
        this.leftSensorLength = modifierSensors.getL1();
        this.rightSensorLength = 60;
        this.sensorsVisibility = true;
    }

//    public static synchronized Sensors getInstance(Car car, ModifierSensors modifierSensors) {
//       return new Sensors(car, modifierSensors);
//    }

    public void createSensors(Graphics2D graphics, BufferedImage trackImage) {
        createCenterSensor(graphics);
        createLeftSensor(graphics, trackImage);
        createRightSensor(graphics);
    }

    private void createCenterSensor(Graphics2D g) {
        rotateSensors(g);
        setVisible(g);
        g.drawLine(middleOfCarX, middleOfCarY - carHeight / 2, middleOfCarX, middleOfCarY - centerSensorLength);
    }

    private void createLeftSensor(Graphics2D g, BufferedImage trackImage) {


        double x2 = (middleOfCarX + leftSensorLength * Math.cos(Math.PI));
        double y2 = (middleOfCarY + leftSensorLength * Math.cos(Math.PI));
        setVisible(g);
        g.drawLine(middleOfCarX - carWidth / 2, middleOfCarY - carHeight / 2, (int) x2, (int) y2);
        Color sensorColor = checkSensorCollision(trackImage, x2,y2);
        Color blackColor = Color.BLACK;

        leftCollision = blackColor.equals(sensorColor);
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

    private Color checkSensorCollision(BufferedImage trackImage, double xp, double yp) {
        double xo = middleOfCarX;
        double yo = middleOfCarY;
        double alfa = carRotation;
        double x = xo + (xp - xo) * Math.cos(alfa) + (yp - yo) * Math.sin(alfa);
        double y = yo - (yp - yo) * Math.cos(alfa) + (xp - xo) * Math.sin(alfa);
        int colorValue = trackImage.getRGB((int) x, (int) y);
        return new Color(colorValue);
    }

}