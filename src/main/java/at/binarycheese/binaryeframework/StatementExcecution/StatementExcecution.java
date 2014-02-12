package at.binarycheese.binaryeframework.StatementExcecution;

import java.net.ConnectException;
import java.sql.Connection;

public interface StatementExcecution<T> {

	public int execute(T t, Connection connection);
	
	public void selectExecute(String tablename,Connection connection);
}
