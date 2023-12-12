package pl.jwz.models;

public class FuzzyLogic {

    private final double[] far = {0.8, 1};
    private final double[] medium = {0.5, 0.8};
    private final double[] close = {0, 0.5};

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

        boolean closeLeft = closeLeft(left);
        boolean closeRight = closeRight(right);
        boolean closeCenter = closeCenter(center);
        boolean mediumLeft = mediumLeft(left);
        boolean mediumRight = mediumRight(right);
        boolean mediumCenter = mediumCenter(center);
        boolean farLeft = farLeft(left);
        boolean farRight = farRight(right);
        boolean farCenter = farCenter(center);


        if((closeLeft && closeRight && farCenter) || (mediumLeft && mediumRight && mediumCenter) ||
                (mediumLeft && mediumRight && farCenter) || (mediumLeft && farRight && farCenter) ||
                (farLeft && mediumRight && farCenter) || (farLeft && farRight && farCenter)){

           // simplyTurn();

        } else {
            if (closeLeft && closeRight && closeCenter) {
                hardLeftTurn(car);
            }

//            if (closeLeft && closeRight && mediumCenter) {
//                simplyTurn();
//            }

//            if (closeLeft && closeRight && farCenter) {
//                simplyTurn();
//            }

            if (closeLeft && mediumRight && closeCenter) {
                mediumRightTurn(car);
            }

            if (closeLeft && mediumRight && mediumCenter) {
                slightLeftTurn(car);
            }

            if (closeLeft && mediumRight && farCenter) {
                slightRightTurn(car);
            }

            if (closeLeft && mediumRight && farCenter) {
                slightRightTurn(car);
            }

            if (closeLeft && mediumRight && closeCenter) {
                hardRightTurn(car);
            }

            if (closeLeft && farRight && farCenter) {
                slightRightTurn(car);
            }

            if (mediumLeft && closeRight && closeCenter) {
                mediumLeftTurn(car);
            }

            if (mediumLeft && closeRight && mediumCenter) {
                slightLeftTurn(car);
            }

            if (mediumLeft && closeRight && farCenter) {
                slightLeftTurn(car);
            }

            if (mediumLeft && mediumRight && closeCenter) {
                hardLeftTurn(car);
            }

//            if (mediumLeft && mediumRight && mediumCenter) {
//                simplyTurn();
//            }

//            if (mediumLeft && mediumRight && farCenter) {
//                simplyTurn();
//            }

            if (mediumLeft && farRight && closeCenter) {
                mediumRightTurn(car);
            }

            if (mediumLeft && farRight && mediumCenter) {
                slightRightTurn(car);
            }

//            if (mediumLeft && farRight && farCenter) {
//                simplyTurn();
//            }

            if (farLeft && closeRight && closeCenter) {
                hardLeftTurn(car);
            }

            if (farLeft && closeRight && mediumCenter) {
                mediumLeftTurn(car);
            }

            if (farLeft && closeRight && farCenter) {
                slightLeftTurn(car);
            }

            if (farLeft && mediumRight && closeCenter) {
                mediumLeftTurn(car);
            }

            if (farLeft && mediumRight && mediumCenter) {
                slightLeftTurn(car);
            }

//            if (farLeft && mediumRight && farCenter) {
//                simplyTurn();
//            }

            if (farLeft && farRight && closeCenter) {
                hardLeftTurn(car);
            }

            if (farLeft && farRight && mediumCenter) {
                mediumLeftTurn(car);
            }

//            if (farLeft && farRight && farCenter) {
//                simplyTurn();
//            }
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
