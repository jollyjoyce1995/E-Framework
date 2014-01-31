import java.sql.Connection;
import java.util.List;

/**
 * Executes the statement
 */
public class StatementExecution<T> {
	private Connection connection;

	public StatementExecution(Connection connection) {
		this.connection = connection;
	}

	// // executes the statement
	// public void executeStatement(String statement) throws SQLException {
	// if (statement != null) {
	// PreparedStatement preparedStatement = null;
	// preparedStatement.execute();
	// } else {
	// throw new NullPointerException();
	// }
	// }

	public void save(T t) {

	}

	private void insert(T t) {

	}

	private void update(T t) {

	}

	public List<T> findAll() {
		return null;
	}

	public T findById(Long id) {
		return null;
	}
}
