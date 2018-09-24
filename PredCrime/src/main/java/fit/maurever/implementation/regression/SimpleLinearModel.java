package fit.maurever.implementation.regression;

/**
 * Simple Linear Regression Model
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class SimpleLinearModel extends Regression {

    public SimpleLinearModel(Double[][] insertedValues) {
        super(insertedValues);
    }

    @Override
    public double countY(double x) {
        return variablesEquation.get(0) + variablesEquation.get(1) * x;
    }

    @Override
    public double[] getRegressionMatrixRowValue(double x) {
        return new double[]{1, x};
    }

}
