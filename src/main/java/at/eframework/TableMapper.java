package at.eframework;

import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by Michi on 19.02.14.
 */
public interface TableMapper {
    ArrayList<String> getColumnNames();
    PreparedStatement addValuesToStatement(PreparedStatement statement);
    PreparedStatement addValuesAndIDToStatement(PreparedStatement statement);
    String tableName();

}
