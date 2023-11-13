package pl.jwz.models;

import java.awt.*;

public class Sensors {

    private int center;
    private int leftSensorLength;
    private int centerSensorLength;
    private double rightSensorLength;
    private boolean sensorsVisibility;
    private final Car car = Car.getInstance();
    private double carWidth = car.getCarWidth();
    private double carHeight = car.getCarHeight();

    public Sensors() {
        this.leftSensorLength = 20;
        this.centerSensorLength = 45;
        this.rightSensorLength = 20d;
        this.sensorsVisibility = true;
    }

    public double getLeftSensorLength() {
        return leftSensorLength;
    }

    public double getCenterSensorLength() {
        return centerSensorLength;
    }

    public double getRightSensorLength() {
        return rightSensorLength;
    }

    public void setLeftSensorLength(int leftSensorLength) {
        this.leftSensorLength = leftSensorLength;
    }

    public void setCenterSensorLength(int centerSensorLength) {
        this.centerSensorLength = centerSensorLength;
    }

    public void setRightSensorLength(double rightSensorLength) {
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
    }

    private void createCenterSensor(Graphics g) {
        int x1 = (int) car.getX();
        int y1 = (int) car.getY();

        int aa = car.getCarWidth()/2;
        //int b1 = x1 + car.getCarWidth();

        center = x1 + aa;


        g.setColor(Color.red);
        g.drawLine(center, y1, center, y1 - leftSensorLength);
    }
}
