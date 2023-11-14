package pl.jwz.models;

import java.awt.*;

public class Sensors {

    private int centerSensorLength;
    private int leftSensorLength;
    private int rightSensorLength;
    private boolean sensorsVisibility;
    private final Car car = Car.getInstance();
    private double carWidth = car.getCarWidth();
    private double carHeight = car.getCarHeight();
    private int carRotation = (int) car.getRotation();

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

    public void createSensors(Graphics graphics) {
        createCenterSensor(graphics);
        createLeftSensor(graphics);
        createRightSensor(graphics);
    }

    private void createCenterSensor(Graphics g) {

        if (carRotation == 0) {
            carRotation = 1;
        }

        int centerCar = car.getCarWidth() / 2;
        int centerXCar = x1 + centerCar;

        g.setColor(Color.red);
        g.drawLine(centerXCar, y1, centerXCar, y1 - centerSensorLength);
    }

    private void createLeftSensor(Graphics g) {
        int x2 = (int) (x1 + leftSensorLength * Math.cos(180));
        int y2 = (int) (y1 + leftSensorLength * Math.sin(180));

        g.setColor(Color.red);
        g.drawLine(x1, y1 - 1, x2, y2);
    }

    private void createRightSensor(Graphics g) {
        int rightX = x1 + car.getCarWidth();

        int x2 = (int) (rightX + rightSensorLength * Math.cos(-45));
        int y2 = (int) (y1 + rightSensorLength * Math.sin(-45));

        g.setColor(Color.red);
        g.drawLine(rightX, y1 - 1, x2, y2);
    }
}
