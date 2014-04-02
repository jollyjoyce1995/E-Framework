package at.eframework;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryStatementFactory {
    /*
        findByIdStatement(int id; string tablename, connection)
		findAllStatement(string tablename; connectiong)
		insertStatement(T t, connection)
		updateStatement(t t, connection)
		deleteStatement(id, conncetion)
	*/

    public PreparedStatement findAllStatement(TableMapper dao, Connection connection) throws QueryBuilderException {
        PreparedStatement findAllStatement;
        try {
            findAllStatement = connection.prepareStatement(new FindAllStatementBuilder().create(dao));
            return findAllStatement;

        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create find all statement", e);
        }
    }

    public PreparedStatement findByIdStatement(TableMapper dao, Connection connection) throws QueryBuilderException {
        PreparedStatement findByIdStatement;
        try {
            findByIdStatement = connection.prepareStatement(new FindByIDStatementBuilder().create(dao));
            return findByIdStatement;

        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create find by id statement", e);
        }
    }

    public PreparedStatement insertStatement(TableMapper dao, Connection connection) throws QueryBuilderException {
        PreparedStatement insertStatement;
        try {
            insertStatement = connection.prepareStatement(new InsertStatementBuilder().create(dao));
            return insertStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create insert statement", e);
        }
    }

    public PreparedStatement updateStatement(TableMapper dao, Connection connection) throws QueryBuilderException {
        PreparedStatement updateStatement;
        try {
            updateStatement = connection.prepareStatement(new UpdateStatementBuilder().create(dao));
            return updateStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create update statement", e);
        }
    }

    public PreparedStatement deleteStatement(TableMapper dao, Connection connection) throws QueryBuilderException {
        PreparedStatement deleteStatement;
        try {
            deleteStatement = connection.prepareStatement(new DeleteStatementBuilder().create(dao));
            return deleteStatement;
        } catch (SQLException e) {
            throw new QueryBuilderException("Failed to create delete statement", e);
        }
    }
}
