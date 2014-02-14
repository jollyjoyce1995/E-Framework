package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import src.QueryBuilderException;
import src.QueryStatementBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryStatementBuilderJUnitTest {

    String dbName = "InfernoKeysDB";
    Connection connection;
    AbstractDao dao;


    @Before
    public void init(){
        try {
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/" + dbName, "sa", "");
        } catch (SQLException e) {
            System.out.println("Failed establishing connection (Have you replaced the dbName Placeholder?)");
        }
        dao = new AbstractDao();
    }

	@Test
	public void testFindAllStatement() throws QueryBuilderException {
        String expected = "SELECT * FROM test";
        PreparedStatement result = new QueryStatementBuilder().findAllStatement(dao, connection);
        assertEquals(expected, result.toString());
	}

}
