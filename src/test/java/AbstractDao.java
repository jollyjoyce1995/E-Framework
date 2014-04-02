import at.eframework.TableMapper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class AbstractDao implements TableMapper {
	public ArrayList<String> getColumnNames(){
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("NAME");
        ret.add("DESCRIPTION");
        return ret;
    }
	public PreparedStatement addValuesToStatement(PreparedStatement statement){
        return null;
    }
	public PreparedStatement addValuesAndIDToStatement(PreparedStatement statement){
        return null;
    }
	public String tableName(){
        return "FUNCTIONS";
    }
}
