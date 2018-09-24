package fit.maurever.implementation.regression;

/**
 * N-polynomial Regression Model
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class NPolynomialRegressionModel extends Regression {

    private int polynomDegree;
    

    public NPolynomialRegressionModel(Double[][] insertedValues, int polynomDegree) {
        super(insertedValues);
        this.polynomDegree = polynomDegree;
    }

    @Override
    public double countY(double x) {
        double tmpResult = variablesEquation.get(0);
        for (int i = 1; i <= polynomDegree; i++) {
            tmpResult += variablesEquation.get(i) * Math.pow(x, i);
        }
        return tmpResult;
    }

    @Override
    public double[] getRegressionMatrixRowValue(double x) {
        double[] tmpArray = new double[polynomDegree + 1];
        tmpArray[0] = 1;
        for (int i = 1; i < tmpArray.length; i++) {
            tmpArray[i] = Math.pow(x, i);
        }
        return tmpArray;
    }

    public void setPolynomDegree(int polynomDegree) {
        this.polynomDegree = polynomDegree;
    }
}
