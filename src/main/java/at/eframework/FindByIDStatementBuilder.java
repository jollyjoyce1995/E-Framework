package at.eframework;

/**
 * Created by Michi on 12.03.14.
 */
public class FindByIDStatementBuilder implements StatementBuilder{
    public String create(TableMapper dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("SELECT ID");
        String splitChar = ", ";
        for (String columName : dao.getColumnNames()){
            statementText.append(splitChar);
            statementText.append(columName);
        }
        statementText.append(" FROM ");
        statementText.append(dao.tableName());
        statementText.append(" WHERE ID = ?");
        return statementText.toString();

    }
}
