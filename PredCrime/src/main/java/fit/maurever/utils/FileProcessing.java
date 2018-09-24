package fit.maurever.utils;

import fit.maurever.implementation.database.Database;
import fit.maurever.layout.MainFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Util for process file.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class FileProcessing {

    public static void saveFile(File file) {
        FileReader in = null;
        FileWriter out = null;
        try {
            in = new FileReader(file.getAbsolutePath());
            out = new FileWriter(file.getName());
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }

    public static void saveDataFromFileToDatabase(File file, DataItem[] items, String separator) throws ParseException {
        try {
            Database.createDatabase(items);
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            String[] lineArray;
            ArrayList<DataItem> databaseItems;
            Calendar calendar = new GregorianCalendar();
            Date date;
            while ((line = input.readLine()) != null) {
                databaseItems = new ArrayList<>();
                lineArray = line.split(separator);
                for (DataItem item : items) {
                    if (item.hasFormat()) {
                        date = new SimpleDateFormat(item.getFormat()).parse(lineArray[item.getColumnIndex()]);
                        calendar.setTime(date);
                        databaseItems.add(new DataItem("day", "INTEGER", null, "" + calendar.get(Calendar.DAY_OF_MONTH)));
                        databaseItems.add(new DataItem("month", "INTEGER", null, "" + calendar.get(Calendar.MONTH)));
                        databaseItems.add(new DataItem("yyear", "INTEGER", null, "" + calendar.get(Calendar.YEAR)));
                        databaseItems.add(new DataItem("hours", "INTEGER", null, "" + calendar.get(Calendar.HOUR)));
                        databaseItems.add(new DataItem("minutes", "INTEGER", null, "" + calendar.get(Calendar.MINUTE)));
                    } else {
                        databaseItems.add(new DataItem(item, lineArray[item.getColumnIndex()]));
                    }
                }
                Database.insert(databaseItems, null);
            }
            MainFrame.statusbar.setText("Data were succesfuly saved.");
        } catch (IOException ex) {
            MainFrame.statusbar.setText(ex.getMessage());
        }
    }

    public static void saveDataFromArrayToFile(Object[][] data, String name) {
        FileWriter out = null;
        File file = new File(name);
        String rowString;
        try {
            out = new FileWriter(file.getName());
            for (Object[] row : data) {
                rowString = "";
                for (Object column : row) {
                    rowString += column.toString() + "; ";
                }
                out.append(rowString.substring(0, rowString.length() - 2) + "\n");
            }
            MainFrame.statusbar.setText("File was created with name '" + name + "'.");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
            MainFrame.statusbar.setText("File not found error.");
        } catch (IOException ex) {
            System.out.println(ex.toString());
            MainFrame.statusbar.setText("File IO error.");
        } finally {
            try {
                out.close();
            } catch (Exception ex) {
                System.out.println(ex.toString());
                MainFrame.statusbar.setText("File closing error.");
            }
        }
    }

}
