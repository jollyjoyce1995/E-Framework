package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteExecution<T> implements StatementExcecution<T> {

	@Override
	public int execute(T t, Connection connection) {
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

	@Override
	public void selectExecute() {
		// TODO Auto-generated method stub
		
	}
}
