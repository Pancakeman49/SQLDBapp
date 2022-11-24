package pancakeman.sqldbapp;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private String ColumnName = "";
    private List<String> ColumnData = new ArrayList<>();
    public void Column(){

    }
    public void addColumnData(String str){
        ColumnData.add(str);
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public List<String> getColumnData() {
        return ColumnData;
    }

    public void setColumnData(List<String> columnData) {
        ColumnData = columnData;
    }
}
