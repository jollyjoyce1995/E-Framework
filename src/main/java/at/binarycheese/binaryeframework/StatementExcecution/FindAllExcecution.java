package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindAllExcecution<T> implements StatementExcecution<T> {

	@Override
	public int execute(T t, Connection connection) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<T> selectExecute(Integer id, String tablename,
			Connection connection) {
				try {
					ArrayList<T> entities = new ArrayList<>();
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
