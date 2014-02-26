package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertExecution<T> extends DMLStatementExecution<T> {

	public InsertExecution(Connection connection) {
		super(connection);
	}

	@Override
	public int execute(T t) throws EFrameWorkUserIsIdiotException{
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
