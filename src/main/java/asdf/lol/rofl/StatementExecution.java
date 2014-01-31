package asdf.lol.rofl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes the statement
 */
public class StatementExecution<T> {
	private Connection connection;

	public StatementExecution(Connection connection) {
		this.connection = connection;
	}

	// // executes the statement
	// public void executeStatement(String statement) throws SQLException {
	// if (statement != null) {
	// PreparedStatement preparedStatement = null;
	// preparedStatement.execute();
	// } else {
	// throw new NullPointerException();
	// }
	// }

	public int save(T t, Object metadata) {
		if (t.getId() == null) {
			return insert(t, metadata);
		} else {
			return update(t, metadata);
		}
	}

	private int insert(T t, Object metadata) {
		try {
			PreparedStatement insertStmnt = QueryStatementBuilder.insertStatement(t, connection);
			int effectedRowCount = insertStmnt.executeUpdate();
			if (effectedRowCount == 0) {
				throw new EFrameWorkUserIsIdiotException(
						"Insert did not insert a row.");
			} else if (effectedRowCount != 1) {
				throw new EFrameWorkUserIsIdiotException(
						"Insert inserted more then one row.");
			}
			return effectedRowCount;
		} catch (SQLException e) {
			throw new EFrameWorkUserIsIdiotException("Failed at insert");
		}
	}

	private int update(T t, Object metadata) {
		try {
			PreparedStatement updateStmnt = QueryStatementBuilder.updateStatement(t,
					connection);
			int effectedRowCount = updateStmnt.executeUpdate();
			if (effectedRowCount == 0) {
				throw new EFrameWorkUserIsIdiotException(
						"Update did not find a matching row.");
			} else if (effectedRowCount != 1) {
				throw new EFrameWorkUserIsIdiotException(
						"Update found more then one row to delete.");
			}
			return effectedRowCount;
		} catch (SQLException e) {
			throw new EFrameWorkUserIsIdiotException("Failed at update");
		}
	}

	public void delete(T t, Object metadata) {
		try {
			PreparedStatement deleteStmnt = QueryStatementBuilder.deleteStatement(t,
					connection);
			int effectedRowCount = deleteStmnt.executeUpdate();
			if (effectedRowCount == 0) {
				throw new EFrameWorkUserIsIdiotException(
						"Delete did not find a matching row.");
			} else if (effectedRowCount != 1) {
				throw new EFrameWorkUserIsIdiotException(
						"Exact delete found more then one row to delete.");
			}
		} catch (SQLException e) {
			throw new EFrameWorkUserIsIdiotException("Failed at delete");
		}
	}

	public List<T> findAll(String tablename) {
		try {
			List<T> entities = new ArrayList<>();
			PreparedStatement statement = findAllStatement(tablename);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				T entity = bind(resultSet);
				entities.add(entity);
			}
			resultSet.close();
			return entities;
		} catch (SQLException e) {
			throw new EFrameWorkUserIsIdiotException(
					"Failed to fetch all entities");
		}
	}

	public T findById(int id, String tablename) {
		 try {
	            PreparedStatement findByIdStatement = findByIdStatement(id, tablename,connection);
	            ResultSet resultSet = findByIdStatement.executeQuery();
	            if (!resultSet.next()) {
	                throw new EFrameWorkUserIsIdiotException("Exact match didn't return data");
	            }
	            T entity = bind(resultSet);
	            if (resultSet.next()) {
	                throw new EFrameWorkUserIsIdiotException("Exact returned more then one row");
	            }
	            return entity;
	        } catch (SQLException e) {
	            throw new EFrameWorkUserIsIdiotException("Failed at findById query");
	        }
	}
}
