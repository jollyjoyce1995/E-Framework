package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.ResultSet;

public interface Dao<T> {

	T bind(ResultSet resultSet);
}
