package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertExcecution<T> {
	
	public int insert(T t, Connection connection) {
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
}
