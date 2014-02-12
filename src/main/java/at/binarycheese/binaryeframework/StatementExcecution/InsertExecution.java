package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertExecution<T> implements StatementExcecution<T> {

	@Override
	public int execute(T t, Connection connection) {
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

	@Override
	public void selectExecute() {
		// TODO Auto-generated method stub
		return;
	}

}
