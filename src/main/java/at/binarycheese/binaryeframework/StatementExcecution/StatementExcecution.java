package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class StatementExcecution<T> {
	Connection connection;
	public StatementExcecution(Connection connection){
		this.connection = connection;
	}
}
