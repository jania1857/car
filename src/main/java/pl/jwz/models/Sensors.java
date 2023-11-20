package pl.jwz.models;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sensors {

    private int centerSensorLength;
    private int leftSensorLength;
    private int rightSensorLength;
    private boolean sensorsVisibility;
    private final Car car = Car.getInstance();
    private final double carWidth = car.getCarWidth();
    private final double carHeight = car.getCarHeight();
    private final double carRotation = car.getRotation();
    private final int x1 = (int) ((int) car.getX() + (carWidth / 2));
    private final int y1 = (int) ((int) car.getY() + (carHeight / 2));
    private final int halfHeightOfCar = (int) carHeight / 2;
    private final int halfWidthOfCar = (int) carWidth / 2;

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
        createRightSensor(graphics);
    }

    private void createCenterSensor(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(carRotation, x1, y1);

        int x2 = (int) (x1 + leftSensorLength * Math.cos(Math.PI * 1.5));
        int y2 = (int) (y1 + leftSensorLength * Math.sin(Math.PI * 1.5));


        g.setTransform(transform);
        useColor(g);
        g.drawLine(x1, y1 - halfHeightOfCar, x2, y2);
    }

    private void createLeftSensor(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(carRotation, x1, y1);

        int x2 = (int) (x1 + leftSensorLength * Math.cos(Math.PI * 1.25));
        int y2 = (int) (y1 + leftSensorLength * Math.sin(Math.PI * 1.25));


        g.setTransform(transform);
        useColor(g);
        g.drawLine(x1 - halfWidthOfCar, y1 - halfHeightOfCar, x2, y2);
    }

    private void createRightSensor(Graphics2D g) {
         AffineTransform transform = new AffineTransform();

        transform.rotate(carRotation, x1, y1);
        int x2 = (int) (x1 + leftSensorLength * Math.cos(Math.PI * 1.75));
        int y2 = (int) (y1 + leftSensorLength * Math.sin(Math.PI * 1.75));


        g.setTransform(transform);
        useColor(g);
        g.drawLine(x1 + halfWidthOfCar, y1 - halfHeightOfCar, x2, y2);
    }

    private void useColor(Graphics2D g) {
        if (sensorsVisibility) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
    }
}