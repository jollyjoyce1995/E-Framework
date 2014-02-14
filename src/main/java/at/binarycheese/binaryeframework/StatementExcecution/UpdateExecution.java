package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateExecution<T> implements StatementExcecution<T> {

	@Override
	public int execute(T t, Connection connection) {
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

	@Override
	public ArrayList<T> selectExecute(Integer id, String tablename,
			Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}
}
