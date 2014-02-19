package at.eframework;


public class QueryStatementBuilder {
    /*
        findByIdStatement(int id; string tablename, connection)
		findAllStatement(string tablename; connectiong)
		insertStatement(T t, connection)
		updateStatement(t t, connection)
		deleteStatement(id, conncetion)
	*/

   /* public PreparedStatement findAllStatement(AbstractDaoInterface dao, Connection connection) throws QueryBuilderException {
        PreparedStatement findAllStatement;
        try {
            StringBuffer statementText = new StringBuffer();
            statementText.append("SELECT * FROM ");
            statementText.append(dao.tableName());
            findAllStatement = connection.prepareStatement(statementText.toString());
            return findAllStatement;

        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create find all statement", e);
        }
    }

    public PreparedStatement findByIdStatement(AbstractDaoInterface dao, Connection connection) throws QueryBuilderException {
        PreparedStatement findByIdStatement;
        try {
            StringBuffer statementText = new StringBuffer();
            statementText.append("SELECT * FROM ");
            statementText.append(dao.tableName());
            statementText.append(" WHERE ID = ?");
            findByIdStatement = connection.prepareStatement(statementText.toString());
            return findByIdStatement;

        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create find by id statement", e);
        }
    }

    public PreparedStatement insertStatement(AbstractDaoInterface dao, Connection connection) throws QueryBuilderException {
        PreparedStatement insertStatement;
        try {
            StringBuffer statementText = new StringBuffer();
            statementText.append("INSERT INTO ").append(dao.tableName()).append(" ( ");
            String splitChar = "";
            for (String columnName : dao.getColumnNames()) {
                statementText.append(splitChar);
                splitChar = " , ";
                statementText.append(columnName);
            }
            statementText.append(" ) ");
            statementText.append("VALUES ( ");
            splitChar = "";
            for (String columnName : dao.getColumnNames()) {
                statementText.append(splitChar);
                splitChar = ", ";
                statementText.append("?");
            }
            statementText.append(" )");
            insertStatement = connection.prepareStatement(statementText.toString());
            return insertStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create insert statement", e);
        }
    }

    public PreparedStatement updateStatement(AbstractDaoInterface dao, Connection connection) throws QueryBuilderException {
        PreparedStatement updateStatement;
        try {
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
            updateStatement = connection.prepareStatement(statementText.toString());
            return updateStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create update statement", e);
        }
    }

    public PreparedStatement deleteStatement(AbstractDaoInterface dao, Connection connection) throws QueryBuilderException {
        PreparedStatement deleteStatement;
        try {
            StringBuffer statementText = new StringBuffer();
            statementText.append("DELETE FROM ").append(dao.tableName());
            statementText.append(" WHERE ").append("ID").append(" = ?");
            deleteStatement = connection.prepareStatement(statementText.toString());
            return deleteStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create delete statement", e);
        }
    }    */

    public String findAllStatement(AbstractDaoInterface dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("SELECT * FROM ");
        statementText.append(dao.tableName());
        return statementText.toString();
    }

    public String findByIdStatement(AbstractDaoInterface dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("SELECT * FROM ");
        statementText.append(dao.tableName());
        statementText.append(" WHERE ID = ?");
        return statementText.toString();

    }

    public String insertStatement(AbstractDaoInterface dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("INSERT INTO ").append(dao.tableName()).append(" ( ");
        String splitChar = "";
        for (String columnName : dao.getColumnNames()) {
            statementText.append(splitChar);
            splitChar = " , ";
            statementText.append(columnName);
        }
        statementText.append(" ) ");
        statementText.append("VALUES ( ");
        splitChar = "";
        for (String columnName : dao.getColumnNames()) {
            statementText.append(splitChar);
            splitChar = ", ";
            statementText.append("?");
        }
        statementText.append(" )");
        return statementText.toString();
    }

    public String updateStatement(AbstractDaoInterface dao) throws QueryBuilderException {
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

    public String deleteStatement(AbstractDaoInterface dao) throws QueryBuilderException {
        StringBuffer statementText = new StringBuffer();
        statementText.append("DELETE FROM ").append(dao.tableName());
        statementText.append(" WHERE ").append("ID").append(" = ?");
        return statementText.toString();
    }
}
