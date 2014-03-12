package at.eframework;

/**
 * Created by Michi on 12.03.14.
 */
public class InsertStatementBuilder implements StatementBuilder{
    public String create(TableMapper dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("INSERT INTO ").append(dao.tableName()).append(" ( ");
        String splitChar = "";
        for (String columnName : dao.getColumnNames()) {
            statementText.append(splitChar);
            splitChar = " , ";
            statementText.append(columnName);
        }
        statementText.append(" ) ");
        statementText.append("VALUES (? ");
        splitChar = ", ";
        for (String columnName : dao.getColumnNames()) {
            statementText.append(splitChar);
            statementText.append("?");
        }
        statementText.append(" )");
        return statementText.toString();
    }
}
