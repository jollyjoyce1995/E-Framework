package POS_JDBCFramework_QueryBuilder.test;

public class AbstractDao {
	public ArrayList<String> getColumnNames();
	public PreparedStatement addValuesToStatement(PreparedStatement statement);
	public PreparedStatement addValuesAndIDToStatement(PreparedStatement statement);
	public String tableName();
}
