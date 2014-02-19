package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.binarycheese.binaryeframework.StatementExcecution.QueryStatementExecution.QueryInputParser;

public class FindAllParser<T> implements QueryInputParser {
	private String tablename;
	private Connection connection;
	
	public FindAllParser(String tablename, Connection connection) {
		this.tablename = tablename;
		this.connection = connection;
	}

	@Override
	public List find() {
		// TODO Auto-generated method stub
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
