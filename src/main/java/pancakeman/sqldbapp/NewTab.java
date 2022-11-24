package pancakeman.sqldbapp;


import javafx.scene.control.*;
import javafx.scene.web.*;
import java.sql.SQLException;


public class NewTab  {

    public String GetWithTableNewTab(String query)  throws SQLException  {

        //get data from the query
        JDBC queryEngine = new JDBC();
        Column[] columns = null;
        try {
            columns = queryEngine.SingleQuery(query);
        }
        catch (SQLException e){
            System.out.println("Something happened");
            e.printStackTrace();
        }



        //put data in a text area


        StringBuilder wholeStringHTML = new StringBuilder("""
                <!DOCTYPE html>
                <html>
                <head>
                </head>
                <body>
                <table>
                <tr>
                """);

        for (int i = 0; i < columns.length; i++) {
            wholeStringHTML.append("<th>").append(columns[i].getColumnName()).append("</th>\n");
        }
        wholeStringHTML.append("</tr>\n");

        for (int i = 0; i < columns[0].getColumnData().size(); i++) {
            wholeStringHTML.append("<tr>\n");
            for (int j = 0; j < columns.length; j++) {
                if (j % 4 == 0){
                    wholeStringHTML.append("<td><pre>").append(columns[j].getColumnData().get(i)).append("</pre></td>");
                }
                else {
                    wholeStringHTML.append("<td>").append(columns[j].getColumnData().get(i)).append("</td>");
                }
            }
            wholeStringHTML.append("</tr>\n");
        }

        wholeStringHTML.append("""
                </table>
                </body>
                </html>""");

       // WebView webView = new WebView();

       // webView.getEngine().loadContent(wholeStringHTML.toString());






        //add the tableview to a tab and return it to be added to the tabpane
        Tab tab = new Tab();
        //tab.setContent(webView);
        tab.setClosable(true);
        tab.setText("Table");
        return wholeStringHTML.toString();
    }


}
