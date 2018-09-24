package fit.maurever.utils;

import de.erichseifert.gral.data.DataTable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Math util for some basic math operation.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class MathUtil {

    public static double countSum(double[] data) {
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum = sum + data[i];
        }
        return sum;
    }

    public static double countSquareSum(double[] data) {
        double sum = 0;
        double avg = countAverage(data);
        for (int i = 0; i < data.length; i++) {
            sum += data[i] - avg;
        }
        return sum;
    }

    public static double countAverage(double[] data) {
        return countSum(data) / data.length;
    }

    public static double countAverage(DataTable dataTable) {
        double[] data = transformDataTableToArray(dataTable, 1);
        return countSum(data) / data.length;
    }

    public static double countSquareDeviation(double[] dataX, double[] dataY) {
        if (dataX.length != dataY.length) {
            System.err.println("Array has not the same lenght! Return -1.");
            return -1;
        } else {
            double avgX = countAverage(dataX);
            double avgY = countAverage(dataY);
            double temp = 0;
            for (int i = 0; i < dataX.length; i++) {
                temp += (dataX[i] - avgX) * (dataY[i] - avgY);
            }
            return temp;
        }
    }

    public static double countSquareDeviation(DataTable firtsDataTable, DataTable secondDataTable) {
        double[] dataX = transformDataTableToArray(firtsDataTable, 1);
        double[] dataY = transformDataTableToArray(secondDataTable, 1);
        if (dataX.length != dataY.length) {
            System.err.println("Array has not the same lenght! Return -1.");
            return -1;
        } else {
            double avgX = countAverage(dataX);
            double avgY = countAverage(dataY);
            double temp = 0;
            for (int i = 0; i < dataX.length; i++) {
                temp += (dataX[i] - avgX) * (dataY[i] - avgY);
            }
            return temp / dataX.length;
        }
    }

    public static double countR(double[] firstArray, double[] secondArray) {
        double sXX = countSquareDeviation(firstArray, firstArray);
        double sYY = countSquareDeviation(secondArray, secondArray);
        double sXY = countSquareDeviation(firstArray, secondArray);
        return sXY / Math.sqrt(sXX * sYY);
    }

    public static double countR(DataTable dataTable) {
        double[] firstArray = transformDataTableToArray(dataTable, 0);
        double[] secondArray = transformDataTableToArray(dataTable, 1);
        double sXX = countSquareDeviation(firstArray, firstArray);
        double sYY = countSquareDeviation(secondArray, secondArray);
        double sXY = countSquareDeviation(firstArray, secondArray);
        return sXY / Math.sqrt(sXX * sYY);
    }

    public static double[] transformDataTableToArray(DataTable table, int columnIndex) {
        double[] array = new double[table.getRowCount()];
        for (int i = 0; i < table.getRowCount(); i++) {
            array[i] = Double.valueOf(table.get(columnIndex, i).toString());
        }
        return array;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
