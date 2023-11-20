package pl.jwz.models;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sensors {

    private int centerSensorLength;
    private int leftSensorLength;
    private int rightSensorLength;
    private boolean sensorsVisibility;
    private final Car car = Car.getInstance();
    private double carWidth = car.getCarWidth();
    private double carHeight = car.getCarHeight();
    private double carRotation = car.getRotation();

    private int x1 = (int) car.getX();
    private int y1 = (int) car.getY();

    public Sensors() {
        this.centerSensorLength = 45;
        this.leftSensorLength = 45;
        this.rightSensorLength = 45;
        this.sensorsVisibility = true;
    }

    public int getCenterSensorLength() {
        return centerSensorLength;
    }

    public void setCenterSensorLength(int centerSensorLength) {
        this.centerSensorLength = centerSensorLength;
    }

    public int getLeftSensorLength() {
        return leftSensorLength;
    }

    public void setLeftSensorLength(int leftSensorLength) {
        this.leftSensorLength = leftSensorLength;
    }

    public int getRightSensorLength() {
        return rightSensorLength;
    }

    public void setRightSensorLength(int rightSensorLength) {
        this.rightSensorLength = rightSensorLength;
    }

    public boolean isSensorsVisibility() {
        return sensorsVisibility;
    }

    public void setSensorsVisibility(boolean sensorsVisibility) {
        this.sensorsVisibility = sensorsVisibility;
    }

    public void createSensors(Graphics2D graphics) {
        createCenterSensor(graphics);
        createLeftSensor(graphics);
       // createRightSensor(graphics);
    }

    private void createCenterSensor(Graphics2D g) {

        int centerXCar = (int) (car.getX() + (carWidth/2));

        AffineTransform transform = new AffineTransform();
        transform.rotate(carRotation, centerXCar, y1);

        g.setColor(Color.red);
        g.setTransform(transform);
        g.drawLine(centerXCar, y1 - 1, centerXCar, y1 - centerSensorLength);
    }

    private void createLeftSensor(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(carRotation, x1, y1);

        int x2 = (int) (x1 + leftSensorLength * Math.cos(180));
        int y2 = (int) (y1 + leftSensorLength * Math.sin(180));

        g.setTransform(transform);
        g.setColor(Color.red);
        g.drawLine(x1, y1 - 1, x2, y2);
    }

    private void createRightSensor(Graphics2D g) {
        int rightX = x1 + car.getCarWidth();

        int x2 = (int) (rightX + rightSensorLength * Math.cos(-45));
        int y2 = (int) (y1 + rightSensorLength * Math.sin(-45));

        g.setColor(Color.red);
        g.drawLine(rightX, y1 - 1, x2, y2);
    }
}
