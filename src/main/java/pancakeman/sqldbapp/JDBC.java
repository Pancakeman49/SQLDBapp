package pancakeman.sqldbapp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    private final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=AdventureWorks2019;encrypt=true;trustServerCertificate=true;integratedSecurity=true;"; // no touchy this
    private final String username = "";
    private final String password = "";
    public Column[] SingleQuery(String query) throws SQLException{

        //query = "select * from Sales.Store"; //"show tables"

        try {
                //System.out.println("before con");
            Connection con = DriverManager.getConnection(url, username, password);
                //System.out.println("con");
            Statement statement = con.createStatement();
                //System.out.println("statement");
            ResultSet result = statement.executeQuery(query);
                // System.out.println("result");

            Column[] columns = new Column[result.getMetaData().getColumnCount()];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = new Column();
            }

            for (int i = 0; i < result.getMetaData().getColumnCount(); i++){
                columns[i].setColumnName(result.getMetaData().getColumnName(i + 1));
            }

            while (result.next()){
                for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {
                    columns[i].addColumnData(result.getString(i + 1));
                }
            }

            con.close();
            return columns;

        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("if you get \"This driver is not configured for integrated authentication.\" Got to: https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15 And download the english driver and open sqljdbc_11.2.0.0_enu.zip -> sqljdbc_11.2 -> enu -> auth -> x64 and copy the dll in the jdk bin folder which should be openjdk-19.0.1 under .jdks in your user profile folder");

        }
        return null;
    }
    public List<String> GetTablesFromDB(){
        List<String> names = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            DatabaseMetaData databaseMetaData = con.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null,null,null, new String[]{"TABLE"});

            while (resultSet.next()){
                names.add(resultSet.getString("TABLE_SCHEM") + "." + resultSet.getString("TABLE_NAME"));
            }
            con.close();
            return names;
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("if you get \"This driver is not configured for integrated authentication.\" Got to: https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15 And download the english driver and open sqljdbc_11.2.0.0_enu.zip -> sqljdbc_11.2 -> enu -> auth -> x64 and copy the dll in the jdk bin folder which should be openjdk-19.0.1 under .jdks in your user profile folder");

        }
        return null;
    }

}
