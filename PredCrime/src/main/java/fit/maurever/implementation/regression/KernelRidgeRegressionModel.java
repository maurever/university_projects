package fit.maurever.implementation.regression;

import org.jblas.*;
import static org.jblas.DoubleMatrix.*;
import static org.jblas.MatrixFunctions.*;

/**
 * Kernel Ridge Regression Model - not implemented yet!
 
 * Partly inspired from https://github.com/mikiobraun/jblas-examples/blob/master/src/KRR.java.
 * @author Mikio L. Braun 

 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class KernelRidgeRegressionModel extends Regression {

    private double mse;

    public KernelRidgeRegressionModel(Double[][] insertedValues) {
        super(insertedValues);
    }

    /**
     * Compute the alpha for Kernel Ridge Regression.
     * Computes alpha = (K + lambda I)^-1 Y.
     * @param X
     * @param Y
     * @param w
     * @param lambda
     * @return 
     */
    DoubleMatrix learnKRR(DoubleMatrix X, DoubleMatrix Y, double w, double lambda) {
        int n = X.rows;
        DoubleMatrix K = gaussianKernel(w, X, X);
        K.addi(eye(n).muli(lambda));
        DoubleMatrix alpha = Solve.solveSymmetric(K, Y);
        return alpha;
    }

    /**
     * Compute the Gaussian kernel for the rows of X and Z, and kernel width w.
     * @param w
     * @param X
     * @param Z
     * @return 
     */
    DoubleMatrix gaussianKernel(double w, DoubleMatrix X, DoubleMatrix Z) {
        DoubleMatrix d = Geometry.pairwiseSquaredDistances(X.transpose(), Z.transpose());
        return exp(d.div(w).neg());
    }

    /**
     * Predict KernelRidgeRegressionModel on XE which has been trained on X, w,
     * and alpha.
     * @param XE
     * @param X
     * @param w
     * @param alpha
     * @return 
     */
    DoubleMatrix predictKRR(DoubleMatrix XE, DoubleMatrix X, double w, DoubleMatrix alpha) {
        DoubleMatrix K = gaussianKernel(w, XE, X);
        return K.mmul(alpha);
    }

    @Override
    public double countMSE() {
        return this.mse;
    }

    /**
     * Method counts MSE from two matrix.
     * @param Y1
     * @param Y2
     * @return 
     */
    double countMSE(DoubleMatrix Y1, DoubleMatrix Y2) {
        DoubleMatrix diff = Y1.sub(Y2);
        return pow(diff, 2).mean();
    }

    @Override
    public void predict(int numberOfPredicatedX) {
        predictedDataArray = new Double[numberOfPredicatedX][2];
    }

    @Override
    public void process() {
        double w = 3;
        double lambda = 5;
        DoubleMatrix X = new DoubleMatrix();
        DoubleMatrix Y = new DoubleMatrix();
        for (Double[] row : insertedValues) {
            insertedData.add(row[0], row[1]);
            X.add(row[0]);
            Y.add(row[1]);
            System.out.println("x = " + row[0] + " y = " + row[1]);
        }
        DoubleMatrix alpha = learnKRR(X, Y, w, lambda);
        DoubleMatrix Yh = predictKRR(X, X, w, alpha);
        for (int i = 0; i < Yh.rows; i++) {
            countedData.add(X.get(i, 0), Yh.get(i, 0));
        }
        this.mse = countMSE(Yh, Y);
    }

    @Override
    public double countY(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double[] getRegressionMatrixRowValue(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
