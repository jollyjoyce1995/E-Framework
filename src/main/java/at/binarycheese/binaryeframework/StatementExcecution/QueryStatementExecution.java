package at.binarycheese.binaryeframework.StatementExcecution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import at.binarycheese.binaryeframework.StatementExcecution.DML.SelectAll;
import at.binarycheese.binaryeframework.StatementExcecution.DML.SelectSingle;
import at.binarycheese.binaryeframework.StatementExcecution.DML.StatementType;
import at.grueneis.spengergasse.lesson_plan.domain.BasePersistable;
import at.grueneis.spengergasse.lesson_plan.persistence.jdbc.AbstractDatabaseDao;

public class QueryStatementExecution<T extends BasePersistable> extends
		StatementExcecution<T> {
	private PersistableBindAndMap<T> dao;

	public QueryStatementExecution(PreparedStatement statement,
			AbstractDatabaseDao dao) {
		super(statement);
		// TODO Auto-generated constructor stub
		this.dao = (PersistableBindAndMap<T>) dao;
	}

	public List<T> execute() {
		List<T> entities = new ArrayList<T>();
		try {
			ResultSet resultSet = super.statement.executeQuery();
			while (resultSet.next()) {
				T entity = dao.bind(resultSet);
				entities.add(entity);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entities;
	}

	public T executeSingle(int id) {
		T entity = null;
		try {
			ResultSet resultSet = super.statement.executeQuery();
			entity =  dao.bind(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
}
