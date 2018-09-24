package fit.maurever.implementation.database;

import static fit.maurever.layout.MainFrame.statusbar;
import fit.maurever.utils.DataItem;
import fit.maurever.utils.DateUtil;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Object for comunication with database.
 * @author Veronika Maurerova <veronika at maurerova.cz>
 */
public class Database {

    //private static final String url = "jdbc:derby:PDD_DATA;create=true;user=Mori;password=mori;";
    private static final String url = "jdbc:derby://localhost:1527/PDD_DATA;user=Mori;password=mori";
    
    public static String univerzalGroupBy = "SELECT COUNT(*)"
            + " FROM TABLE_NAME "
            + " WHERE yyear = X"
            + " GROUP BY yyear, month, day order by yyear, month, day";
    public static String univerzalGroupByHour = "SELECT COUNT(*)"
            + " FROM TABLE_NAME "
            + " WHERE yyear = X AND month = X AND day = X"
            + " GROUP BY yyear, month, day, hour order by yyear, month, day, hour";
    public static String univerzalGroupByDay = "SELECT COUNT(*)"
            + " FROM TABLE_NAME "
            + " WHERE yyear = X AND month = X"
            + " GROUP BY yyear, month, day order by yyear, month, day";
    public static String univerzalGroupByMonth = "SELECT COUNT(*)"
            + " FROM TABLE_NAME "
            + " WHERE yyear = X"
            + " GROUP BY yyear, month order by yyear, month";
    public static String univerzalGroupByYear = "SELECT COUNT(*)"
            + " FROM TABLE_NAME "
            + " WHERE yyear = X"
            + " GROUP BY yyear order by yyear";

    private static Connection con;
    private static int tableCounter;
    private static int idCounter;

    private static void connect() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(url);
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /**
     * Create database from income DataItems. 
     * @param items 
     */
    public static void createDatabase(DataItem[] items) {
        connect();
        try {
            Statement sta = con.createStatement();
            String query = "CREATE TABLE DATA" + tableCounter++ + " ( "
                    + "id INTEGER PRIMARY KEY, "
                    + "yyear INTEGER, "
                    + "month INTEGER, "
                    + "day INTEGER, "
                    + "hours INTEGER, "
                    + "minutes INTEGER, ";
            for (DataItem item : items) {
                if (!item.hasFormat()) {
                    query += item.getName() + " " + item.getType() + ", ";
                }
            }
            query = query.substring(0, query.length() - 2);
            query += ")";
            System.out.println(query);
            sta.executeUpdate(query);
            sta.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /**
     * Method for making update query.
     * @param query  
     */
    public static void makeUpdateQuery(String query) {
        connect();
        try {
            con = DriverManager.getConnection(url);
            Statement sta = con.createStatement();
            sta.executeUpdate(query);
            sta.close();
            con.close();
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /**
     * Method makes select and then saves it to the file.
     * @param query
     * @param axisX
     * @param axisY 
     */
    public static void SelectToFile(String query, String axisX, String axisY) {
        connect();
        String filename;
        try {
            con = DriverManager.getConnection(url);
            Statement sta = con.createStatement();
            sta.execute(query);
            ResultSet rs = sta.getResultSet();
            filename = "group_own_" + DateUtil.getDateTime_ddMMyyyy_HHmm();
            FileWriter out = new FileWriter(filename);
            if (axisX == null || "".equals(axisX)) {
                axisX = "X";
            }
            if (axisY == null || "".equals(axisY)) {
                axisY = "Y";
            }
            out.write(axisX + "~" + axisY + "\n");;
            for (int i = 1; rs.next(); i++) {
                out.write(i + "~" + (int) rs.getDouble(1) + "\n");
            }
            out.close();
            sta.close();
            con.close();
            statusbar.setText("Data were generated, filename is: '" + filename + "'. Select them for view.");
        } catch (Exception e) {
            statusbar.setText("Incorrect query! Use correct syntax.");
            System.err.println("Exception: " + e.getMessage());
        }
    }

    /**
     * Method deletes table with specific name from database.
     * @param tableName 
     */
    public static void deleteTable(String tableName) {
        connect();
        try {
            con = DriverManager.getConnection(url);
            Statement sta = con.createStatement();
            sta.execute("DROP TABLE " + tableName);
            sta.close();
            con.close();
            statusbar.setText("Table was succesfully deleted.");
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            statusbar.setText(e.getMessage());
        }
    }

    /**
     * Method inserts data to database.
     * @param items
     * @param tableName 
     */
    public static void insert(ArrayList<DataItem> items, String tableName) {
        if (tableName == null) {
            tableName = "DATA" + tableCounter++;
        }
        try {
            connect();
            String startQuery = "INSERT INTO  " + tableName + " (ID, ";
            String query;
            for (DataItem item : items) {
                startQuery += item.getName() + ", ";
            }
            startQuery = startQuery.substring(0, startQuery.length() - 2);
            Statement sta = con.createStatement();
            query = startQuery;
            query += ") VALUES (" + idCounter++ + ", ";
            for (DataItem item : items) {
                if (item.getType().contains("VARCHAR") || item.getType().contains("LONGVARCHAR")) {
                    query += "'" + item.getValue() + "', ";
                } else {
                    query += item.getValue() + ", ";
                }
            }
            query = query.substring(0, query.length() - 2);
            query += ")";
            System.out.println(query);
            sta.execute(query);
        } catch (SQLException ex) {
            System.out.println("Exception: " + ex.getMessage());
            statusbar.setText(ex.getMessage());
        }
    }

    /**
     * Method returns a information about tables in database in html format.
     * @return String information about tables in database in html format or "" if there is error.
     */
    public static String getTableInfoHtml() {
        String result = "<html>";
        connect();
        try {
            con = DriverManager.getConnection(url);
            Statement sta = con.createStatement();
            Statement sta2 = con.createStatement();
            sta.execute("select s.schemaname || '.' || t.tablename from sys.systables t, sys.sysschemas s where t.schemaid = s.schemaid and t.tabletype = 'T' order by s.schemaname, t.tablename");
            ResultSet rs = sta.getResultSet();
            while (rs.next()) {
                result += "<b>Table name</b>: " + rs.getString(1);
                sta2.execute("select COLUMNNAME "
                        + "FROM sys.systables t, sys.syscolumns "
                        + "WHERE TABLEID = REFERENCEID and tablename = '" + rs.getString(1).split("\\.")[1] + "'");
                ResultSet rs2 = sta2.getResultSet();
                result += "<br><b>Columns</b>: <ul>";
                while (rs2.next()) {
                    result += "<li>" + rs2.getString(1) + "</li>";
                }
                result += "</ul><br>";
            }
            result += "</html>";
            sta.close();
            con.close();
            return result;
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            statusbar.setText(e.getMessage());
            return "";
        }
    }

}
