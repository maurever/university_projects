package fit.maurever.implementation.regression;

/**
 * Polynomial Regression Model uses Cross Validation
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class PolynomialRegressionCrossValidation {

    private int maxPolynomialDegree;
    private int testingSetNumber;
    private Double[][] data;
    private Double[][] testingSet;
    private Double[][] trainingSet;


    public PolynomialRegressionCrossValidation(int maxPolynomialDegree, int testingSetNumber, Double[][] data) {
        this.maxPolynomialDegree = maxPolynomialDegree;
        this.testingSetNumber = testingSetNumber;
        this.data = data;
    }

    public PolynomialRegressionCrossValidation(Double[][] data) {
        this.maxPolynomialDegree = 10;
        this.testingSetNumber = 3;
        this.data = data;
    }

    /**
     * Cross validation method for getting the best polynom degree.
     * @return the best polynom degree
     */
    public int getBestPolynomialRegressionDegree() {
        int bestPolynomialDegree = 0;
        double tmpCrossErrorAvg, tmpCrossErrorSum, minCrossError = Double.MAX_VALUE;
        int interval = (data.length / testingSetNumber), i;
        Regression regression;
        for (int j = 1; j <= maxPolynomialDegree; j++) {
            tmpCrossErrorSum = 0;
            i = 0;
            int k = 0;
            while (i < data.length) {
                devideDataPartly(k++, interval);
                regression = new NPolynomialRegressionModel(trainingSet, j);
                regression.process();
                for (Double[] value : testingSet) {
                    tmpCrossErrorSum += Math.pow(regression.countY(value[0]) - value[1], 2);
                }
                if (i + interval == data.length - 1) {
                    i = data.length + 1;
                } else if (i + 3 * interval > data.length) {
                    i = data.length - interval - 1;
                } else {
                    i += interval;
                }
            }
            tmpCrossErrorAvg = tmpCrossErrorSum / (testingSetNumber - j);
            if (tmpCrossErrorAvg < minCrossError) {
                minCrossError = tmpCrossErrorAvg;
                bestPolynomialDegree = j;
            }
        }
        return bestPolynomialDegree;
    }

    /**
     * Cross validation method for getting the best polynom degree using sliding window.
     * @return the best polynom degree
     */
    public int getBestPolynomialRegressionDegreeWindow() {
        int bestPolynomialDegree = 0;
        double tmpCrossErrorAvg, tmpCrossErrorSum, minCrossError = Double.MAX_VALUE;
        int interval = (data.length / testingSetNumber), i;
        Regression regression;
        for (int j = 1; j <= maxPolynomialDegree; j++) {
            tmpCrossErrorSum = 0;
            i = 0;
            int k = 0;
            while (k + interval < data.length) {
                devideDataWindow(k, interval + k);
                regression = new NPolynomialRegressionModel(trainingSet, j);
                regression.process();
                for (Double[] value : testingSet) {
                    tmpCrossErrorSum += Math.pow(regression.countY(value[0]) - value[1], 2);
                }
                k++;
            }
            tmpCrossErrorAvg = tmpCrossErrorSum / (testingSetNumber - j);
            if (tmpCrossErrorAvg < minCrossError) {
                minCrossError = tmpCrossErrorAvg;
                bestPolynomialDegree = j;
            }
        }
        return bestPolynomialDegree;
    }

    /**
     * Method devides data into two parts - traning and testing data, using window principle.
     * @param from
     * @param to 
     */
    public void devideDataWindow(int from, int to) {
        int i = 0, j = 0, interval = to - from;
        testingSet = new Double[interval][2];
        trainingSet = new Double[data.length - interval][2];
        while (i + j < data.length) {
            if (i + j >= from && i + j < to) {
                testingSet[i][0] = data[i + j][0];
                testingSet[i][1] = data[i + j][1];
                i++;
            } else {
                trainingSet[j][0] = data[i + j][0];
                trainingSet[j][1] = data[i + j][1];
                j++;
            }
        }
    }

    /**
     * Method devides data into two parts - traning and testing data, using interval principle.
     * @param index
     * @param interval 
     */
    public void devideDataPartly(int index, int interval) {
        int i = 0, j = 0;
        testingSet = new Double[interval][2];
        trainingSet = new Double[data.length - interval][2];
        while (i + j < data.length) {
            if ((i + j - index) % (data.length / interval) == 0 && i < interval) {
                testingSet[i][0] = data[i + j][0];
                testingSet[i][1] = data[i + j][1];
                i++;
            } else {
                trainingSet[j][0] = data[i + j][0];
                trainingSet[j][1] = data[i + j][1];
                j++;
            }
        }
    }

    public int getMaxPolynomialDegree() {
        return maxPolynomialDegree;
    }

    public int getTestingSetNumber() {
        return testingSetNumber;
    }

    public void setTestingSetNumber(int testingSetNumber) {
        this.testingSetNumber = testingSetNumber;
    }

    public Double[][] getData() {
        return data;
    }

}
