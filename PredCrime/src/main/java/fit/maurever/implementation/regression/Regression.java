package fit.maurever.implementation.regression;

import de.erichseifert.gral.data.DataTable;
import fit.maurever.utils.MathUtil;
import org.jblas.DoubleMatrix;
import org.jblas.Solve;

/**
 * Abstract class for types of regression.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public abstract class Regression {

    protected Double[] residuals;
    protected Double[][] insertedValues;
    protected Double[][] predictedDataArray;
    protected DataTable insertedData;
    protected DataTable countedData;
    protected DataTable countedPredictedDataLine;
    protected DataTable predictedData;
    protected DoubleMatrix variablesEquation;
    protected double maxX;
    protected double minX;

    public Regression(Double[][] insertedValues) {
        this.insertedValues = insertedValues;
        this.insertedData = new DataTable(Double.class, Double.class);
        this.countedData = new DataTable(Double.class, Double.class);
        this.countedPredictedDataLine = new DataTable(Double.class, Double.class);
        this.predictedData = new DataTable(Double.class, Double.class);
        if (insertedValues != null) {
            this.residuals = new Double[insertedValues.length];
        } else {
            this.residuals = new Double[0];
        }
        this.minX = Double.MAX_VALUE;
        this.maxX = Double.MIN_VALUE;
    }

    /**
     * From given x it counts y using internal function.
     * @param x
     * @return 
     */
    public abstract double countY(double x);

    /**
     * Prepare matrix row, only for linear model.
     * @param x
     * @return matrix row
     */
    public abstract double[] getRegressionMatrixRowValue(double x);

    public void process() {
        double[] regressionValues = new double[insertedValues.length];
        double[][] regressionMatrix = new double[insertedValues.length][];
        for (int i = 0; i < insertedValues.length; i++) {
            double x = insertedValues[i][0];
            double y = insertedValues[i][1];
            regressionValues[i] = y;
            insertedData.add(x, y);
            maxX = maxX < x ? x : maxX;
            minX = minX > x ? x : minX;
            regressionMatrix[i] = getRegressionMatrixRowValue(x);
        }
        DoubleMatrix dmatrix = new DoubleMatrix(regressionMatrix);
        DoubleMatrix dvalues = new DoubleMatrix(regressionValues);
        variablesEquation = Solve.solveLeastSquares(dmatrix, dvalues);
        double y, x;
        for (Double[] insertedValue : insertedValues) {
            x = insertedValue[0];
            y = countY(x);
            countedData.add(x, y);
        }

    }

    /**
     * Predict specific number of new value using internal or linear principle.
     * @param numberOfPredicatedX 
     */
    public void predict(int numberOfPredicatedX) {
        if (insertedValues != null) {
            predictedDataArray = new Double[numberOfPredicatedX][2];
            int j;
            double tmpY;
            double y, x;
            for (x = minX; x <= maxX; x += (maxX - minX) / 100) {
                y = countY(x);
                countedPredictedDataLine.add(x, y);
            }
            int interval = (int) Math.round((maxX - minX) / (insertedValues.length - 1));
            for (x = maxX, j = -1; x < maxX + (numberOfPredicatedX + 1) * interval; x += interval, j++) {
                tmpY = countY(x);
                tmpY = tmpY < 0 ? 0 : tmpY;
                if (j == -1) {
                    countedPredictedDataLine.add(x, tmpY);
                } else {
                    predictedDataArray[j][1] = Math.round(tmpY) + 0.0;
                    predictedDataArray[j][0] = Math.round(x) + 0.0;
                }
                predictedData.add(x, tmpY);
            }
        }
    }

    /**
     * Count Sum of Square Error.
     * @return SSE
     */
    public double countSSE() {
        double residualSum = 0;
        if (residuals != null && residuals.length != 0) {
            for (Double residual : residuals) {
                residualSum += residual * residual;
            }
            return MathUtil.round(residualSum, 5);
        }
        return -1;
    }

    /**
     * Count Sum of Square Total.
     * @return SST
     */
    public double countSST() {
        double avg = MathUtil.countAverage(insertedData);
        double sst = 0;
        for (int i = 0; i < insertedData.getRowCount(); i++) {
            sst += (Double.valueOf(insertedData.get(1, i).toString()) - avg) * (Double.valueOf(insertedData.get(1, i).toString()) - avg);
        }
        return MathUtil.round(sst, 5);
    }

    /**
     * Count sum of Square Residuals.
     * @return SSR
     */
    public double countSSR() {
        double avg = MathUtil.countAverage(insertedData);
        double ssr = 0;
        for (int i = 0; i < countedData.getRowCount(); i++) {
            ssr += (Double.valueOf(countedData.get(1, i).toString()) - avg) * (Double.valueOf(countedData.get(1, i).toString()) - avg);
        }
        return MathUtil.round(ssr, 5);
    }

    /**
     * Count Mean Sum of Error.
     * @return MSE
     */
    public double countMSE() {
        if (variablesEquation != null) {
            return MathUtil.round(countSSE() / (insertedData.getRowCount() - variablesEquation.length), 5);
        } else if (insertedValues != null) {
            return MathUtil.round(countSSE() / insertedData.getRowCount(), 5);
        } else {
            return -1;
        }
    }

    /**
     * Count Mean Sum of Residual.
     * @return MSR
     */
    public double countMSR() {
        if (variablesEquation != null) {
            return MathUtil.round(countSSR() / (variablesEquation.length - 1), 5);
        } else {
            return -1;
        }
    }

    /**
     * Count Mean Absolute Error.
     * @return MAE
     */
    public double countMAE() {
        double residualSum = 0;
        if (residuals != null && residuals.length != 0) {
            for (Double residual : residuals) {
                residualSum += residual;
            }
        }
        if (insertedValues != null) {
            return MathUtil.round(residualSum / insertedData.getRowCount(), 5);
        } else {
            return -1;
        }

    }

    /**
     * Count index of determination.
     * @return R2
     */
    public double countR2() {
        if (countSST() != 0) {
            return MathUtil.round(countSSR() / countSST(), 5);
        }
        return -1;
    }

    /**
     * Count value of F-test
     * @return R
     */
    public double countF() {
        if (countMSE() != -1) {
            return MathUtil.round(countMSR() / countMSE(), 5);
        }
        return -1;
    }

    public void setResidues() {
        if (countedData != null && countedData.getRowCount() != 0) {
            for (int i = 0; i < insertedData.getRowCount(); i++) {
                residuals[i] = Math.abs((Double) countedData.get(1, i) - (Double) insertedData.get(1, i));
            }
        }
    }

    public Double[] getResiduals() {
        return residuals;
    }

    public Double[][] getInsertedValues() {
        return insertedValues;
    }

    public void resetInsertedValues(Double[][] insertedValues) {
        this.insertedValues = insertedValues;
        this.insertedData = new DataTable(Double.class, Double.class);
        this.countedData = new DataTable(Double.class, Double.class);
        this.countedPredictedDataLine = new DataTable(Double.class, Double.class);
        this.predictedData = new DataTable(Double.class, Double.class);
        this.residuals = new Double[insertedValues.length];
        this.minX = Double.MIN_VALUE;
        this.maxX = Double.MAX_VALUE;
    }

    public Double[][] getPredictedDataArray() {
        return predictedDataArray;
    }

    public DataTable getInsertedData() {
        return insertedData;
    }

    public DataTable getCountedData() {
        return countedData;
    }

    public DataTable getCountedPredictedDataLine() {
        return countedPredictedDataLine;
    }

    public DataTable getPredictedData() {
        return predictedData;
    }

    public DoubleMatrix getVariablesEquation() {
        return variablesEquation;
    }

    /**
     * Convert information from regression model to html string.
     * @return 
     */
    public String toHtmlString() {
        String regressionInf;
        this.setResidues();
        double r = MathUtil.countR(insertedData);
        double r2 = this.countR2();
        double sst = this.countSST();
        double sse = this.countSSE();
        double mse = this.countMSE();
        double msr = this.countMSR();
        double mae = this.countMAE();
        double ssr = this.countSSR();
        double f = this.countF();

        regressionInf = "<html>Equitation Coeficients: <br>";
        if (variablesEquation != null) {
            for (int i = 0; i < variablesEquation.getLength(); i++) {
                regressionInf += "x<sub>" + i + "</sub> = " + variablesEquation.get(i) + " ";
                if ((i - 2) % 3 == 0 || i == variablesEquation.length - 1) {
                    regressionInf += "<br>";
                }
            }
        }
        regressionInf += "Correlation coefficient of inserted data:<br> r<sub>in</sub> = " + r + "<br>";
        regressionInf += "Coefficient of determination:<br> R<sup>2</sup><sub>reg</sub> = " + r2 + " <br>";
        regressionInf += "Sum of Squares Total: <br> SST = " + sst + "<br>";
        regressionInf += "Sum of Squares Residual:<br> SSR = " + ssr + "<br>";
        regressionInf += "Sum of Squares Error:<br> SSE = " + sse + "<br>";
        regressionInf += "Mean Squared Error:<br> MSE = " + mse + "<br>";
        regressionInf += "Mean Squared Residual:<br> MSR = " + msr + "<br>";
        regressionInf += "Mean Absolute Error:<br> MAE = " + mae + "<br>";
        regressionInf += "F-ration:<br> F= " + f + "<br>";
        regressionInf += "</html>";
        return regressionInf;
    }

    /**
     * Convert information from regression model to string array
     * @return 
     */
    public String[][] getInformationInArray() {
        String[][] infoArray;
        if (variablesEquation != null) {
            infoArray = new String[9 + variablesEquation.getLength()][];
        } else {
            infoArray = new String[9][];
        }
        this.setResidues();
        infoArray[0] = new String[]{"Correlation coefficient of inserted data", "" + MathUtil.countR(insertedData)};
        infoArray[1] = new String[]{"Coefficient of determination", "" + this.countR2()};
        infoArray[2] = new String[]{"Sum of Squares Total", "" + this.countSST()};
        infoArray[3] = new String[]{"Sum of Squares Residual", "" + this.countSSR()};
        infoArray[4] = new String[]{"Sum of Squares Error", "" + this.countSSE()};
        infoArray[5] = new String[]{"Mean Squared Error", "" + this.countMSE()};
        infoArray[6] = new String[]{"Mean Squared Residual", "" + this.countMSR()};
        infoArray[7] = new String[]{"Mean Absolute Error", "" + this.countMAE()};
        infoArray[8] = new String[]{"F-ration", "" + this.countF()};
        if (variablesEquation != null) {
            for (int i = 9; i < 9 + variablesEquation.getLength(); i++) {
                infoArray[i] = new String[]{"Parameter " + (i - 8), "" + variablesEquation.get(i - 9)};
            }
        }
        return infoArray;
    }

}
