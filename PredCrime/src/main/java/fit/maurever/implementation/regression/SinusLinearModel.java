package fit.maurever.implementation.regression;

/**
 * Simple Sinus Regression Model.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class SinusLinearModel extends Regression {

    private double period;

    public SinusLinearModel(Double[][] insertedValues, double period) {
        super(insertedValues);
        this.period = period;
    }

    @Override
    public double countY(double x) {
        return variablesEquation.get(0) + variablesEquation.get(1) * x + variablesEquation.get(2) * Math.sin((2 * Math.PI * x) / period) + variablesEquation.get(3) * Math.cos((2 * Math.PI * x) / period);
    }

    @Override
    public double[] getRegressionMatrixRowValue(double x) {
        return new double[]{1.0, x, countSinX(x), countCosX(x)};
    }

    public double countSinX(double x) {
        return Math.sin((2 * Math.PI * x) / this.period);
    }

    public double countCosX(double x) {
        return Math.cos((2 * Math.PI * x) / this.period);
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

}
