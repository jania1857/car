package pl.jwz.models;

public class Sensors {
    private double leftSensorLength;
    private double centerSensorLength;
    private double rightSensorLength;
    private boolean sensorsVisibility;
    private final Car car = new Car();
    private double carWidth = car.getCarWidth();
    private double carHeight = car.getCarHeight();

    public Sensors() {
        this.leftSensorLength = 45d;
        this.centerSensorLength = 45d;
        this.rightSensorLength = 45d;
        this.sensorsVisibility = true;

        createSensors();
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

    public void setLeftSensorLength(double leftSensorLength) {
        this.leftSensorLength = leftSensorLength;
    }

    public void setCenterSensorLength(double centerSensorLength) {
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

    public void createSensors(){

    }

    private void createLeftSensor(){

    }


}
