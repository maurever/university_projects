package fit.maurever.implementation.regression;

/**
 * Exponencial Smoothing Regression Model
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class ExponencialSmoothingModel extends Regression {

    private double prevS1 = 0;
    private double prevS2 = 0;
    private final double alpha;

    public ExponencialSmoothingModel(Double[][] insertedValues, double alpha) {
        super(insertedValues);
        this.alpha = alpha;
    }

    @Override
    public void process() {
        double x, y, newY;
        for (Double[] insertedValue : insertedValues) {
            x = insertedValue[0];
            y = insertedValue[1];
            insertedData.add(x, y);
            newY = countY(y);
            countedData.add(x, newY);
            countedPredictedDataLine.add(x, newY);
        }
        variablesEquation = null;
    }

    @Override
    public void predict(int numberOfPredicatedX) {
        double predictedY;
        double t, beta0, beta1;
        predictedDataArray = new Double[numberOfPredicatedX][2];
        double n = insertedValues[insertedValues.length - 1][0];
        for (int i = 1; i < numberOfPredicatedX + 1; i++) {
            t = n + i;
            beta0 = 2 * prevS1 - prevS2;
            beta1 = ((1 - alpha) / alpha) * (prevS1 - prevS2);
            predictedY = beta0 + beta1 * (t - n + 1);
            predictedData.add(t, predictedY);
            predictedDataArray[i - 1] = new Double[]{Math.round(t) + 0.0, Math.round(predictedY) + 0.0};
            if (i == 1) {
                countedData.add(t, predictedY);
                countedPredictedDataLine.add(t, predictedY);
            }
        }
    }

    @Override
    public double countY(double y) {
        prevS1 = countS(y, prevS1);
        prevS2 = countS(prevS1, prevS2);
        return (2 + (1 - alpha) / alpha) * prevS1 - (1 + (1 - alpha) / alpha) * prevS1;
    }

    @Override
    public double[] getRegressionMatrixRowValue(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double countS(double x, double prev) {
        return (1 - alpha) * x + alpha * prev;
    }

}
