package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindAllExcecution<T> {
	public List<T> findAll(String tablename, Connection connection) {
		try {
			List<T> entities = new ArrayList<>();
			PreparedStatement statement = QueryStatementBuilder
					.findAllStatement(tablename, connection);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				T entity = T.bind(resultSet);
				entities.add(entity);
			}
			resultSet.close();
			return entities;
		} catch (SQLException e) {
			throw new EFrameWorkUserIsIdiotException(
					"Failed to fetch all entities");
		}
	}
}
