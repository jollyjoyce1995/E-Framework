package POS_JDBCFramework_QueryBuilder.src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import POS_JDBCFramework_QueryBuilder.test;

public class QueryStatementBuilder {
	/*
		findByIdStatement(int id; string tablename, connection)
		findAllStatement(string tablename; connectiong)
		insertStatement(T t, connection)
		updateStatement(t t, connection)
		deleteStatement(id, conncetion)
	*/
	
	private PreparedStatement findAllStatement(AbstractDao dao, Connection connection) {
        try {
            if (findAllStatement == null) {
                StringBuffer statementText = new StringBuffer();
                statementText.append("SELECT * FROM ");
                statementText.append(dao.tableName());
                findAllStatement = connection.prepareStatement(statementText.toString());
            }
            return findAllStatement;

        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create find all statement", e);
        }
    }
	
	private PreparedStatement findByIdStatement(AbstractDao dao, Connection connection, int id) {
        try {
            if (findByIdStatement == null) {
                StringBuffer statementText = new StringBuffer();
                statementText.append("SELECT * FROM ");
                statementText.append(dao.tableName());
                statementText.append(" WHERE ID = ");
                statementText.append(id);
                findByIdStatement = connection.prepareStatement(statementText.toString());
            }
            return findByIdStatement;

        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create find by id statement", e);
        }
    }
	
	private PreparedStatement insertStatement(AbstractDao dao, Connection connection) {
        try {
            if (insertStatement == null) {
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
            }
            return insertStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create insert statement", e);
        }
    }

    private PreparedStatement updateStatement(AbstractDao dao, Connection connection) {
        try {
            if (updateStatement == null) {
                StringBuffer statementText = new StringBuffer();
                statementText.append("UPDATE ").append(dao.tableName());
                statementText.append(" SET ");
                int count = 0;
                for (String columnName : dao.getColumnNames()) {
                    count++;
                    statementText.append(" ").append(columnName).append(" = ? ");
                    if (count < dao.getColumnNames().length) {
                        statementText.append(" , ");
                    }
                }
                statementText.append(" WHERE ").append("ID").append(" = ? ");
                updateStatement = connection.prepareStatement(statementText.toString());
                updateStatement = dao.addValuesToStatement(updateStatement);
            }
            return updateStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create update statement", e);
        }
    }

    private PreparedStatement deleteStatement(AbstractDao dao, Connection connection, int id) {
        try {
            if (deleteStatement == null) {
                StringBuffer statementText = new StringBuffer();
                statementText.append("DELETE FROM ").append(dao.tableName());
                statementText.append(" WHERE ").append("ID").append(" = ").append(id);
                deleteStatement = connection.prepareStatement(statementText.toString());
            }
            return deleteStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create delete statement", e);
        }
    }
}
