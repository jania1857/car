package pl.jwz.models;

public class FuzzyLogic {

    private final int max;
    private final double a;
    private final double b;
    private final double c;
    private double closeLeft;
    private double mediumLeft;
    private double farLeft;
    private double closeCenter;
    private double mediumCenter;
    private double farCenter;
    private double closeRight;
    private double mediumRight;
    private double farRight;
    private static double[][] leftDistance;
    private static double[][] rightDistance;
    private static double[][] centerDistance;
    private static final double[] rules = new double[27];
    private static final double[][] turn = new double[7][91];
    private static final double[][] conclusion = new double[8][91];
    private static final double[][] aggregation = new double[2][91];

    public FuzzyLogic(int maximum) {
        this.max = (maximum + 2);
        int border = (int) (double) ((max - (max / 3)) / 3);
        this.a = 16;
        this.b = 32;
        this.c = 51;
        leftDistance = new double[3][max];
        rightDistance = new double[3][max];
        centerDistance = new double[3][max];

        fillTurn();
        fillLeftDistance();
        fillRightDistance();
        fillCenterDistance();
    }

    private void fillTurn() {
        for (int i = 0; i < turn[0].length; i++) {

            if (i <= 5)
                turn[0][i] = 1;

            if (i >= 5 & i <= 15)
                turn[0][i] = (15 - (double) i) / (15 - 5);

            if (i >= 5 & i <= 15)
                turn[1][i] = ((double) i - 5) / (15 - 5);

            if (i >= 15 & i <= 30)
                turn[1][i] = (30 - (double) i) / (30 - 15);

            if (i >= 15 & i <= 30)
                turn[2][i] = ((double) i - 15) / (30 - 15);

            if (i >= 30 & i <= 45)
                turn[2][i] = (45 - (double) i) / (45 - 30);

            if (i >= 30 & i <= 45)
                turn[3][i] = ((double) i - 30) / (45 - 30);

            if (i >= 45 & i <= 60)
                turn[3][i] = (60 - (double) i) / (60 - 45);

            if (i >= 45 & i <= 60)
                turn[4][i] = ((double) i - 45) / (60 - 45);

            if (i >= 60 & i <= 75)
                turn[4][i] = (75 - (double) i) / (75 - 60);

            if (i >= 60 & i <= 75)
                turn[5][i] = ((double) i - 60) / (75 - 60);

            if (i >= 75 & i <= 85)
                turn[5][i] = (85 - (double) i) / (85 - 75);

            if (i >= 75 & i <= 85)
                turn[6][i] = ((double) i - 75) / (85 - 75);

            if (i >= 85 & i <= 91)
                turn[6][i] = 1;
        }
    }

    private void fillLeftDistance() {
        for (int i = 0; i < leftDistance[0].length; i++) {

            if (i <= a)
                leftDistance[0][i] = 1;
            else if (i >= a && i <= b)
                leftDistance[0][i] = (b - i) / (b - a);

            if (i >= a && i <= b)
                leftDistance[1][i] = (i - a) / (b - a);
            else if (i >= b && i <= c)
                leftDistance[1][i] = (c - i) / (c - b);

            if (i >= b && i <= c)
                leftDistance[2][i] = (i - b) / (c - b);
            else if (i >= c & i <= max)
                leftDistance[2][i] = 1;
        }
    }

    private void fillRightDistance() {
        for (int i = 0; i < rightDistance[0].length; i++) {

            if (i <= a)
                rightDistance[0][i] = 1;
            else if (i >= a && i <= b)
                rightDistance[0][i] = (b - i) / (b - a);

            if (i >= a && i <= b)
                rightDistance[1][i] = (i - a) / (b - a);
            else if (i >= b & i <= c)
                rightDistance[1][i] = (c - i) / (c - b);

            if (i >= b && i <= c)
                rightDistance[2][i] = (i - b) / (c - b);
            else if (i >= c & i <= max)
                rightDistance[2][i] = 1;
        }
    }

    private void fillCenterDistance() {
        for (int i = 0; i < centerDistance[0].length; i++) {

            if (i <= a)
                centerDistance[0][i] = 1;
            else if (i >= a && i <= b)
                centerDistance[0][i] = (b - i) / (b - a);

            if (i >= a && i <= b)
                centerDistance[1][i] = (i - a) / (b - a);
            else if (i >= b & i <= c)
                centerDistance[1][i] = (c - i) / (c - b);

            if (i >= b && i <= c)
                centerDistance[2][i] = (i - b) / (c - b);
            else if (i >= c && i <= max)
                centerDistance[2][i] = 1;
        }
    }

    public void blur(int left, int center, int right) {
        closeLeft = leftDistance[0][left];
        mediumLeft = leftDistance[1][left];
        farLeft = leftDistance[2][left];

        closeCenter = centerDistance[0][center];
        mediumCenter = centerDistance[1][center];
        farCenter = centerDistance[2][center];

        closeRight = rightDistance[0][right];
        mediumRight = rightDistance[1][right];
        farRight = rightDistance[2][right];
    }

    private void createRules() {
        rules[0] = findMinValue(closeLeft, closeRight, closeCenter);
        rules[1] = findMinValue(closeLeft, closeRight, mediumCenter);
        rules[2] = findMinValue(closeLeft, closeRight, farCenter);

        rules[3] = findMinValue(closeLeft, mediumRight, closeCenter);
        rules[4] = findMinValue(closeLeft, mediumRight, mediumCenter);
        rules[5] = findMinValue(closeLeft, mediumRight, farCenter);

        rules[6] = findMinValue(closeLeft, farRight, closeCenter);
        rules[7] = findMinValue(closeLeft, farRight, mediumCenter);
        rules[8] = findMinValue(closeLeft, farRight, farCenter);

        rules[9] = findMinValue(mediumLeft, closeRight, closeCenter);
        rules[10] = findMinValue(mediumLeft, closeRight, mediumCenter);
        rules[11] = findMinValue(mediumLeft, closeRight, farCenter);

        rules[12] = findMinValue(mediumLeft, mediumRight, closeCenter);
        rules[13] = findMinValue(mediumLeft, mediumRight, mediumCenter);
        rules[14] = findMinValue(mediumLeft, mediumRight, farCenter);

        rules[15] = findMinValue(mediumLeft, farRight, closeCenter);
        rules[16] = findMinValue(mediumLeft, farRight, mediumCenter);
        rules[17] = findMinValue(mediumLeft, farRight, farCenter);

        rules[18] = findMinValue(farLeft, closeRight, closeCenter);
        rules[19] = findMinValue(farLeft, closeRight, mediumCenter);
        rules[20] = findMinValue(farLeft, closeRight, farCenter);

        rules[21] = findMinValue(farLeft, mediumRight, closeCenter);
        rules[22] = findMinValue(farLeft, mediumRight, mediumCenter);
        rules[23] = findMinValue(farLeft, mediumRight, farCenter);

        rules[24] = findMinValue(farLeft, farRight, closeCenter);
        rules[25] = findMinValue(farLeft, farRight, mediumCenter);
        rules[26] = findMinValue(farLeft, farRight, farCenter);
    }

    private void conclusion() {

        double hardInLeft = findMaxValue(rules[0], rules[12], rules[18], rules[24]);
        double mediumInLeft = findMaxValue(rules[9], rules[19], rules[21], rules[25]);
        double softInLeft = findMaxValue(rules[10], rules[11], rules[20], rules[22]);
        double goSimply = findMaxValue(rules[1], rules[2], rules[13], rules[14], rules[17], rules[23], rules[26]);
        double hardInRight = findMaxValue(rules[6]);
        double mediumInRight = findMaxValue(rules[3], rules[7], rules[15]);
        double softInRight = findMaxValue(rules[4], rules[5], rules[8], rules[16]);

        for (int i = 0; i < turn[0].length; i++) {
            conclusion[0][i] = i;

            if (i <= 15)
                conclusion[1][i] = findMinValue(hardInLeft, turn[0][i]);
            if (i >= 5 && i <= 30)
                conclusion[2][i] = findMinValue(mediumInLeft, turn[1][i]);
            if (i >= 15 && i <= 45)
                conclusion[3][i] = findMinValue(softInLeft, turn[2][i]);
            if (i >= 30 && i <= 60)
                conclusion[4][i] = findMinValue(goSimply, turn[3][i]);
            if (i >= 45 && i <= 75)
                conclusion[5][i] = findMinValue(softInRight, turn[4][i]);
            if (i >= 60 && i <= 85)
                conclusion[6][i] = findMinValue(mediumInRight, turn[5][i]);
            if (i >= 75 && i <= 90)
                conclusion[7][i] = findMinValue(hardInRight, turn[6][i]);
        }
    }

    private void aggregation() {
        for (int i = 0; i < turn[0].length; i++) {
            aggregation[0][i] = conclusion[0][i];
            aggregation[1][i] = findMaxValue(conclusion[1][i], conclusion[2][i], conclusion[3][i], conclusion[4][i], conclusion[5][i], conclusion[6][i], conclusion[7][i]);
        }
    }

    public double sharpening() {
        double g = 0;
        double d = 0;
        for (int j = 0; j < turn[0].length; j++) {
            g = aggregation[0][j] * aggregation[1][j] + g;
            d = aggregation[1][j] + d;
        }

        double y = g / d;
        y = y - 45;

        return y;
    }

    public void fuzzyLogicSystem(int left, int center, int right) {
        blur(left, center, right);
        createRules();
        conclusion();
        aggregation();
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
}
