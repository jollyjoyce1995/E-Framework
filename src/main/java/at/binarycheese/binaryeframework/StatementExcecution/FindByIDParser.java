package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.util.Task;

import at.binarycheese.binaryeframework.StatementExcecution.QueryStatementExecution.QueryInputParser;

public class FindByIDParser<T> implements QueryInputParser {
	private int id;
	private String tablename;
	private Connection connection;
	
	public FindByIDParser(int id, String tablename, Connection connection) {
		this.id= id;
		this.tablename = tablename;
		this.connection = connection;
	}

	@Override
	public List find() {
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
			ArrayList<T> asdf =new ArrayList<T>();
			asdf.add(entity);
			return asdf;
		} catch (SQLException e) {
			throw new EFrameWorkUserIsIdiotException("Failed at findById query");
		}
	}
	
}