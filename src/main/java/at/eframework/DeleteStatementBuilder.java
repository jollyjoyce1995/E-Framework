package at.eframework;

/**
 * Created by Michi on 12.03.14.
 */
public class DeleteStatementBuilder implements StatementBuilder {
    public String create(TableMapper dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("DELETE FROM ").append(dao.tableName());
        statementText.append(" WHERE ").append("ID").append(" = ?");
        return statementText.toString();
    }
}
