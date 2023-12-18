package pl.jwz.models;

public class FuzzyLogic {
    private final int max;
    private final double a;
    private final double b;
    private final double c;
    private static double[][] leftDistance;
    private static double[][] rightDistance;
    private static double[][] centerDistance;
    private static final double[][] turn = new double[8][91];
    private static final double[][] conclusion = new double[8][91];
    private static final double[][] aggregation = new double[2][91];


    public FuzzyLogic(int maximum) {
        this.max = (maximum + 2);
        int border = (int) (double) ((max - (max / 3)) / 3);
        this.a = border;
        this.b = border * 2;
        this.c = border * 3;
        leftDistance = new double[4][max];
        rightDistance = new double[4][max];
        centerDistance = new double[4][max];

        fillTurn();
        fillDistanceVariable();
    }

    private void fillTurn() {
        for (int i = 0; i < turn[0].length; i++) {
            turn[0][i] = i;

            if (i <= 5)
                turn[1][i] = 1;
            if (i >= 5 & i <= 15)
                turn[1][i] = (15 - (double) i) / (15 - 5);

            if (i >= 5 & i <= 15)
                turn[2][i] = ((double) i - 5) / (15 - 5);
            if (i >= 15 & i <= 30)
                turn[2][i] = (30 - (double) i) / (30 - 15);

            if (i >= 15 & i <= 30)
                turn[3][i] = ((double) i - 15) / (30 - 15);
            if (i >= 30 & i <= 45)
                turn[3][i] = (45 - (double) i) / (45 - 30);

            if (i >= 30 & i <= 45)
                turn[4][i] = ((double) i - 30) / (45 - 30);
            if (i >= 45 & i <= 60)
                turn[4][i] = (60 - (double) i) / (60 - 45);

            if (i >= 45 & i <= 60)
                turn[5][i] = ((double) i - 45) / (60 - 45);
            if (i >= 60 & i <= 75)
                turn[5][i] = (75 - (double) i) / (75 - 60);

            if (i >= 60 & i <= 75)
                turn[6][i] = ((double) i - 60) / (75 - 60);
            if (i >= 75 & i <= 85)
                turn[6][i] = (85 - (double) i) / (85 - 75);

            if (i >= 75 & i <= 85)
                turn[7][i] = ((double) i - 75) / (85 - 75);
            if (i >= 85 & i <= 91)
                turn[7][i] = 1;
        }
    }

    private void fillDistanceVariable() {

        for (int i = 0; i < leftDistance[0].length; i++) {
            leftDistance[0][i] = i;
            rightDistance[0][i] = i;
            centerDistance[0][i] = i;

            //Left
            if (i <= a)
                leftDistance[1][i] = 1;
            else if (i >= a && i <= b)
                leftDistance[1][i] = (b - i) / (b - a);

            if (i >= a && i <= b)
                leftDistance[2][i] = (i - a) / (b - a);
            else if (i >= b && i <= c)
                leftDistance[2][i] = (c - i) / (c - b);

            if (i >= b && i <= c)
                leftDistance[3][i] = (i - b) / (c - b);
            else if (i >= c & i <= max)
                leftDistance[3][i] = 1;

            //Right
            if (i <= a)
                rightDistance[1][i] = 1;
            else if (i >= a && i <= b)
                rightDistance[1][i] = (b - i) / (b - a);

            if (i >= a && i <= b)
                rightDistance[2][i] = (i - a) / (b - a);
            else if (i >= b & i <= c)
                rightDistance[2][i] = (c - i) / (c - b);

            if (i >= b && i <= c)
                rightDistance[3][i] = (i - b) / (c - b);
            else if (i >= c & i <= max)
                rightDistance[3][i] = 1;

            //Center
            if (i <= a)
                centerDistance[1][i] = 1;
            else if (i >= a && i <= b)
                centerDistance[1][i] = (b - i) / (b - a);

            if (i >= a && i <= b)
                centerDistance[2][i] = (i - a) / (b - a);
            else if (i >= b & i <= c)
                centerDistance[2][i] = (c - i) / (c - b);

            if (i >= b && i <= c)
                centerDistance[3][i] = (i - b) / (c - b);
            else if (i >= c && i <= max)
                centerDistance[3][i] = 1;
        }
    }

    private double findMaxValue(double... numbers) {
        double maxValue = 0d;
        for (double d : numbers) {
            maxValue = Math.max(maxValue, d);
        }
        return maxValue;
    }

    private double findMinValue(double... numbers) {
        double minValue = 1d;
        for (double d : numbers) {
            minValue = Math.min(minValue, d);
        }
        return minValue;
    }

    private double fuzzyLogic(int left, int center, int right) {

        double closeLeft = leftDistance[1][left];
        double mediumLeft = leftDistance[2][left];
        double farLeft = leftDistance[3][left];

        double closeCenter = centerDistance[1][center];
        double mediumCenter = centerDistance[2][center];
        double farCenter = centerDistance[3][center];

        double closeRight = rightDistance[1][right];
        double mediumRight = rightDistance[2][right];
        double farRight = rightDistance[3][right];

        double R1 = findMinValue(closeLeft, closeRight, closeCenter);
        double R2 = findMinValue(closeLeft, closeRight, mediumCenter);
        double R3 = findMinValue(closeLeft, closeRight, farCenter);

        double R4 = findMinValue(closeLeft, mediumRight, closeCenter);
        double R5 = findMinValue(closeLeft, mediumRight, mediumCenter);
        double R6 = findMinValue(closeLeft, mediumRight, farCenter);

        double R7 = findMinValue(closeLeft, farRight, closeCenter);
        double R8 = findMinValue(closeLeft, farRight, mediumCenter);
        double R9 = findMinValue(closeLeft, farRight, farCenter);

        double R10 = findMinValue(mediumLeft, closeRight, closeCenter);
        double R11 = findMinValue(mediumLeft, closeRight, mediumCenter);
        double R12 = findMinValue(mediumLeft, closeRight, farCenter);

        double R13 = findMinValue(mediumLeft, mediumRight, closeCenter);
        double R14 = findMinValue(mediumLeft, mediumRight, mediumCenter);
        double R15 = findMinValue(mediumLeft, mediumRight, farCenter);

        double R16 = findMinValue(mediumLeft, farRight, closeCenter);
        double R17 = findMinValue(mediumLeft, farRight, mediumCenter);
        double R18 = findMinValue(mediumLeft, farRight, farCenter);

        double R19 = findMinValue(farLeft, closeRight, closeCenter);
        double R20 = findMinValue(farLeft, closeRight, mediumCenter);
        double R21 = findMinValue(farLeft, closeRight, farCenter);

        double R22 = findMinValue(farLeft, mediumRight, closeCenter);
        double R23 = findMinValue(farLeft, mediumRight, mediumCenter);
        double R24 = findMinValue(farLeft, mediumRight, farCenter);

        double R25 = findMinValue(farLeft, farRight, closeCenter);
        double R26 = findMinValue(farLeft, farRight, mediumCenter);
        double R27 = findMinValue(farLeft, farRight, farCenter);

        //Conclusion
        double hardInLeft = findMaxValue(R1, R13, R19, R25);
        double mediumInLeft = findMaxValue(R10, R20, R22, R26);
        double softInLeft = findMaxValue(R11, R12, R21, R23);
        double goSimply = findMaxValue(R2, R3, R14, R15, R18, R24, R27);
        double hardInRight = findMaxValue(R7);
        double mediumInRight = findMaxValue(R4, R8, R16);
        double softInRight = findMaxValue(R5, R6, R9, R17);

        for (int i = 0; i < turn[0].length; i++) {
            conclusion[0][i] = i;

            if (i <= 15)
                conclusion[1][i] = findMinValue(hardInLeft, turn[1][i]);
            if (i >= 5 && i <= 30)
                conclusion[2][i] = findMinValue(mediumInLeft, turn[2][i]);
            if (i >= 15 && i <= 45)
                conclusion[3][i] = findMinValue(softInLeft, turn[3][i]);
            if (i >= 30 && i <= 60)
                conclusion[4][i] = findMinValue(goSimply, turn[4][i]);
            if (i >= 45 && i <= 75)
                conclusion[5][i] = findMinValue(softInRight, turn[5][i]);
            if (i >= 60 && i <= 85)
                conclusion[6][i] = findMinValue(mediumInRight, turn[6][i]);
            if (i >= 75 && i <= 90)
                conclusion[7][i] = findMinValue(hardInRight, turn[7][i]);

        }

        // Aggregation
        for (int i = 0; i < turn[0].length; i++) {
            aggregation[0][i] = conclusion[0][i];
            aggregation[1][i] = findMaxValue(conclusion[1][i],
                    conclusion[2][i], conclusion[3][i],
                    conclusion[4][i], conclusion[5][i],
                    conclusion[6][i], conclusion[7][i]);
        }

        // Sharpening
        double g = 0;
        double d = 0;
        for (int j = 0; j < turn[0].length; j++) {
            g = aggregation[0][j] * aggregation[1][j] + g;
            d = aggregation[1][j] + d;
        }

        double y = g / d;
        return (y - 45);
    }

    public void ChangeCarRotation(int leftSensor, int centerSensor, int rightSensor, Car car) {

        double actualRotation = car.getRotation();

        double newRotation = actualRotation + fuzzyLogic(leftSensor, centerSensor, rightSensor);
        car.setRotation(newRotation);

    }
}