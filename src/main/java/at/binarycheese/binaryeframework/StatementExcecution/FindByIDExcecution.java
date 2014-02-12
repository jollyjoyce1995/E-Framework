package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindByIDExcecution<T> implements StatementExcecution<T> {

	@Override
	public int execute(T t, Connection connection) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<T> selectExecute(Integer id, String tablename,
			Connection connection) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement findByIdStatement = QueryStatementBuilder
					.findByIdStatement(id, tablename, connection);
			ResultSet resultSet = findByIdStatement.executeQuery();
			if (!resultSet.next()) {
				throw new EFrameWorkUserIsIdiotException(
						"Exact match didn't return data");
			}
			T entity = T.bind(resultSet);
			if (resultSet.next()) {
				throw new EFrameWorkUserIsIdiotException(
						"Exact returned more then one row");
			}
			return entity;
		} catch (SQLException e) {
			throw new EFrameWorkUserIsIdiotException("Failed at findById query");
		}

	}
}