package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;

public abstract class DMLStatementExecution<T> extends StatementExcecution<T>{
	public DMLStatementExecution(Connection connection){
		super(connection);
	}
	
	public abstract int execute(T t) throws EFrameWorkUserIsIdiotException;
}
