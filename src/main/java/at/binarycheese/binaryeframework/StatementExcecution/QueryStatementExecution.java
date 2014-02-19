package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.util.List;

public class QueryStatementExecution<T> extends StatementExcecution<T>{

	public QueryStatementExecution(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public List<T> execute(QueryInputParser query){
		return query.find();
	}
	
	interface QueryInputParser{
		List find();
	}
}
