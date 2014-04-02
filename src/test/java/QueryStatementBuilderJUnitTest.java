import static org.junit.Assert.*;

import at.eframework.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;

public class QueryStatementBuilderJUnitTest {

    String dbName = "InfernoKeysDB";
    Connection connection;
    TableMapper dao;


    @Before
    public void init(){
        /*try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/" + dbName, "sa", "");
        } catch (SQLException e) {
            System.out.println("Failed establishing connection (Have you replaced the dbName Placeholder?)");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found");
        }             */
        dao = new AbstractDao();
    }

	@Test
	public void testFindAllStatement() throws QueryBuilderException {
        String expected = "SELECT ID, NAME, DESCRIPTION FROM " + dao.tableName();
        String result = new FindAllStatementBuilder().create(dao);
        assertEquals(expected, result);
	}

    @Test
    public void testFindByIDStatement() throws QueryBuilderException {
        String expected = "SELECT ID, NAME, DESCRIPTION FROM " + dao.tableName() + " WHERE ID = ?";
        String result = new FindByIDStatementBuilder().create(dao);
        assertEquals(expected, result);
    }

    @Test
    public void testInsertStatement() throws QueryBuilderException {
        ArrayList<String> colNames = dao.getColumnNames();
        String expected = "INSERT INTO " + dao.tableName() + " (ID , " + colNames.get(0) + " , " + colNames.get(1) +  " ) VALUES (?, ?, ? )";
        String result = new InsertStatementBuilder().create(dao);
        assertEquals(expected, result);
    }

    @Test
    public void testUpdateStatement() throws QueryBuilderException {
        ArrayList<String> colNames = dao.getColumnNames();
        String expected = "UPDATE " + dao.tableName() + " SET " + colNames.get(0) + " = ? , " + colNames.get(1) +  " = ? WHERE ID = ?";
        String result = new UpdateStatementBuilder().create(dao);
        assertEquals(expected, result);
    }

    @Test
    public void testDeleteStatement() throws QueryBuilderException {
        String expected = "DELETE FROM " + dao.tableName() + " WHERE ID = ?";
        String result = new DeleteStatementBuilder().create(dao);
        assertEquals(expected, result);
    }


}
