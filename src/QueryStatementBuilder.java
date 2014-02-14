package src;

import test.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryStatementBuilder {
    /*
		findByIdStatement(int id; string tablename, connection)
		findAllStatement(string tablename; connectiong)
		insertStatement(T t, connection)
		updateStatement(t t, connection)
		deleteStatement(id, conncetion)
	*/

    public PreparedStatement findAllStatement(AbstractDao dao, Connection connection) throws QueryBuilderException {
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

    public PreparedStatement findByIdStatement(AbstractDao dao, Connection connection, int id) throws QueryBuilderException {
        PreparedStatement findByIdStatement;
        try {
            StringBuffer statementText = new StringBuffer();
            statementText.append("SELECT * FROM ");
            statementText.append(dao.tableName());
            statementText.append(" WHERE ID = ");
            statementText.append(id);
            findByIdStatement = connection.prepareStatement(statementText.toString());
            return findByIdStatement;

        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create find by id statement", e);
        }
    }

    public PreparedStatement insertStatement(AbstractDao dao, Connection connection) throws QueryBuilderException {
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
            statementText.append(" VALUES ( ");
            splitChar = "";
            for (String columnName : dao.getColumnNames()) {
                statementText.append(splitChar);
                splitChar = ", ";
                statementText.append("?");
            }
            statementText.append(" ) ");
            insertStatement = connection.prepareStatement(statementText.toString());
            insertStatement = dao.addValuesToStatement(insertStatement);
            return insertStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create insert statement", e);
        }
    }

    public PreparedStatement updateStatement(AbstractDao dao, Connection connection) throws QueryBuilderException {
        PreparedStatement updateStatement;
        try {
            StringBuffer statementText = new StringBuffer();
            statementText.append("UPDATE ").append(dao.tableName());
            statementText.append(" SET ");
            int count = 0;
            for (String columnName : dao.getColumnNames()) {
                count++;
                statementText.append(" ").append(columnName).append(" = ? ");
                if (count < dao.getColumnNames().size()) {
                    statementText.append(" , ");
                }
            }
            statementText.append(" WHERE ").append("ID").append(" = ? ");
            updateStatement = connection.prepareStatement(statementText.toString());
            updateStatement = dao.addValuesToStatement(updateStatement);
            return updateStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create update statement", e);
        }
    }

    public PreparedStatement deleteStatement(AbstractDao dao, Connection connection, int id) throws QueryBuilderException {
        PreparedStatement deleteStatement;
        try {
            StringBuffer statementText = new StringBuffer();
            statementText.append("DELETE FROM ").append(dao.tableName());
            statementText.append(" WHERE ").append("ID").append(" = ").append(id);
            deleteStatement = connection.prepareStatement(statementText.toString());
            return deleteStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create delete statement", e);
        }
    }
}
