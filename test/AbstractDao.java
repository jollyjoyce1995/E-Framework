package test;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class AbstractDao {
	public ArrayList<String> getColumnNames(){
        return null;
    }
	public PreparedStatement addValuesToStatement(PreparedStatement statement){
        return null;
    }
	public PreparedStatement addValuesAndIDToStatement(PreparedStatement statement){
        return null;
    }
	public String tableName(){
        return "FILES";
    }
}
