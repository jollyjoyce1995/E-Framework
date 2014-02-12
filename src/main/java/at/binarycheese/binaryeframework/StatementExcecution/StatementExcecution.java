package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.util.ArrayList;

public interface StatementExcecution<T> {

	public int execute(T t, Connection connection);
	
	public ArrayList<T> selectExecute(Integer id, String tablename,Connection connection);
}
