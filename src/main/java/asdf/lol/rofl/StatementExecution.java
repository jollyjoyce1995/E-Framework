package asdf.lol.rofl;
import EFrameWorkUserIsIdiotException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		if(t.getId()==null){
			return insert(t,metadata);
		}else{
			return update(t, metadata);
		}
	}

	private int insert(T t, Object metadata) {
		try {
            PreparedStatement insertStmnt = insertStatement(t, metadata,connection);
            int effectedRowCount = insertStmnt.executeUpdate();
            if (effectedRowCount == 0) {
                throw  new EFrameWorkUserIsIdiotException("Insert did not insert a row.");
            } else if (effectedRowCount != 1) {
                throw  new EFrameWorkUserIsIdiotException("Insert inserted more then one row.");
            }
            return effectedRowCount;
        } catch (SQLException e) {
            throw new EFrameWorkUserIsIdiotException("Failed at insert");
        }
	}

	private int update(T t, Object metadata) {
		 try {
	            PreparedStatement updateStmnt = updateStatement(t,metadata,connection);
	            int effectedRowCount = updateStmnt.executeUpdate();
	            if (effectedRowCount == 0) {
	                throw  new EFrameWorkUserIsIdiotException("Update did not find a matching row.");
	            } else if (effectedRowCount != 1) {
	                throw  new EFrameWorkUserIsIdiotException("Update found more then one row to delete.");
	            }
	            return effectedRowCount;
	        } catch (SQLException e) {
	            throw new EFrameWorkUserIsIdiotException("Failed at update");
	        }
	}

	public List<T> findAll(Object metadata) {
		return null;
	}

	public T findById(Long id, Object metadata) {
		return null;
	}
}
