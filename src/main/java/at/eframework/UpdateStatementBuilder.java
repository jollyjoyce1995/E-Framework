package at.eframework;

/**
 * Created by Michi on 12.03.14.
 */
public class UpdateStatementBuilder implements StatementBuilder{
    public String create(TableMapper dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("UPDATE ").append(dao.tableName());
        statementText.append(" SET ");
        int count = 0;
        for (String columnName : dao.getColumnNames()) {
            count++;
            statementText.append(columnName).append(" = ?");
            if (count < dao.getColumnNames().size()) {
                statementText.append(" , ");
            }
        }
        statementText.append(" WHERE ").append("ID").append(" = ?");
        return statementText.toString();
    }
}
