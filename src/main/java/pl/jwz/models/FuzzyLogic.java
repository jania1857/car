package pl.jwz.models;

public class FuzzyLogic {

    private final double[] far = {0.7, 1};
    private final double[] medium = {0.4, 0.7};
    private final double[] close = {0, 0.4};

    public void logic(double leftSensor, double centerSensor, double rightSensor, double maxLength, Car car) {
        if (leftSensor == 0) {
            leftSensor = maxLength;
        }

        if (centerSensor == 0) {
            centerSensor = maxLength;
        }

        if (rightSensor == 0) {
            rightSensor = maxLength;
        }

        double left = leftSensor / maxLength;
        double center = centerSensor / maxLength;
        double right = rightSensor / maxLength;


        if (closeLeft(left) & closeRight(right) & closeCenter(center)) {
            hardLeftTurn(car);
        }

        if (closeLeft(left) & closeRight(right) & mediumCenter(center)) {
            simplyTurn();
        }

        if (closeLeft(left) & closeRight(right) & farCenter(center)) {
            simplyTurn();
        }

        if (closeLeft(left) & mediumRight(right) & closeCenter(center)) {
            mediumRightTurn(car);
        }

        if (closeLeft(left) & mediumRight(right) & mediumCenter(center)) {
            slightLeftTurn(car);
        }

        if (closeLeft(left) & mediumRight(right) & farCenter(center)) {
            slightRightTurn(car);
        }

        if (closeLeft(left) & mediumRight(right) & farCenter(center)) {
            slightRightTurn(car);
        }

        if (closeLeft(left) & mediumRight(right) & closeCenter(center)) {
            hardRightTurn(car);
        }

        if (closeLeft(left) & farRight(right) & farCenter(center)) {
            slightRightTurn(car);
        }

        if (mediumLeft(left) & closeRight(right) & closeCenter(center)) {
            mediumLeftTurn(car);
        }

        if (mediumLeft(left) & closeRight(right) & mediumCenter(center)) {
            slightLeftTurn(car);
        }

        if (mediumLeft(left) & closeRight(right) & farCenter(center)) {
            slightLeftTurn(car);
        }

        if (mediumLeft(left) & mediumRight(right) & closeCenter(center)) {
            hardLeftTurn(car);
        }

        if (mediumLeft(left) & mediumRight(right) & mediumCenter(center)) {
            simplyTurn();
        }

        if (mediumLeft(left) & mediumRight(right) & farCenter(center)) {
            simplyTurn();
        }

        if (mediumLeft(left) & farRight(right) & closeCenter(center)) {
            mediumRightTurn(car);
        }

        if (mediumLeft(left) & farRight(right) & mediumCenter(center)) {
            slightRightTurn(car);
        }

        if (mediumLeft(left) & farRight(right) & farCenter(center)) {
            simplyTurn();
        }

        if (farLeft(left) & closeRight(right) & closeCenter(center)) {
            hardLeftTurn(car);
        }

        if (farLeft(left) & closeRight(right) & mediumCenter(center)) {
            mediumLeftTurn(car);
        }

        if (farLeft(left) & closeRight(right) & farCenter(center)) {
            slightLeftTurn(car);
        }

        if (farLeft(left) & mediumRight(right) & closeCenter(center)) {
           mediumLeftTurn(car);
        }

        if (farLeft(left) & mediumRight(right) & mediumCenter(center)) {
            slightLeftTurn(car);
        }

        if (farLeft(left) & mediumRight(right) & farCenter(center)) {
           simplyTurn();
        }

        if (farLeft(left) & farRight(right) & closeCenter(center)) {
            hardLeftTurn(car);
        }

        if (farLeft(left) & farRight(right) & mediumCenter(center)) {
            mediumLeftTurn(car);
        }

        if (farLeft(left) & farRight(right) & farCenter(center)) {
            simplyTurn();
        }
    }

    private boolean closeLeft(double left) {
        return left >= close[0] & left <= close[1];
    }

    private boolean closeRight(double right) {
        return right >= close[0] & right <= close[1];
    }

    private boolean closeCenter(double center) {
        return center >= close[0] & center <= close[1];
    }

    private boolean mediumLeft(double left) {
        return left >= medium[0] & left <= medium[1];
    }

    private boolean mediumRight(double right) {
        return right >= medium[0] & right <= medium[1];
    }

    private boolean mediumCenter(double center) {
        return center >= medium[0] & center <= medium[1];
    }

    private boolean farLeft(double left) {
        return left >= far[0] & left >= far[1];
    }

    private boolean farRight(double right) {
        return right >= far[0] & right >= far[1];
    }

    private boolean farCenter(double center) {
        return center >= far[0] & center >= far[1];
    }

    private void hardLeftTurn(Car car) {
        for (int i = 0; i < 3; i++) {
            car.rotateLeft();
        }
    }

    private void mediumLeftTurn(Car car) {
        for (int i = 0; i < 2; i++) {
            car.rotateLeft();
        }
    }

    private void slightLeftTurn(Car car) {
        car.rotateLeft();
    }

    private void hardRightTurn(Car car) {
        for (int i = 0; i < 3; i++) {
            car.rotateRight();
        }
    }

    private void mediumRightTurn(Car car) {
        for (int i = 0; i < 2; i++) {
            car.rotateLeft();
        }
    }

    private void slightRightTurn(Car car) {
        car.rotateRight();
    }

    private void simplyTurn() {

    }
}
