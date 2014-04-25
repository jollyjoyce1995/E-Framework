package at.grueneis.spengergasse.lesson_plan.persistence.jdbc;

/*
 * Joachim Grüneis
 * Copyright (C) 2013
 * All rights reserved.
 */

import at.eframework.QueryBuilderException;
import at.eframework.QueryStatementFactory;
import at.eframework.TableMapper;
import at.grueneis.spengergasse.lesson_plan.domain.BasePersistable;
import at.grueneis.spengergasse.lesson_plan.persistence.jdbc.DatabaseDao;
import at.grueneis.spengergasse.lesson_plan.persistence.jdbc.LessonPlanDataAccessException;
import at.grueneis.spengergasse.registry.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.lang.reflect.*;

/**
 * "template pattern"
 */
public abstract class AbstractDatabaseDao<T extends BasePersistable> implements DatabaseDao<T>, TableMapper {

    private final Connection connection;
    private String FIND_ALL;
    private String FIND_BY_ID;
    private String INSERT;
    private String UPDATE;
    private String DELETE;
    private Map<String, PreparedStatement> prepMap;
    private Class type;
    

    public AbstractDatabaseDao(Connection connection) throws QueryBuilderException {
        this.connection = connection;
        FIND_ALL = "Find all Objects";
        FIND_BY_ID = "Find Object by ID";
        INSERT = "Insert Object";
        UPDATE = "Update Object";
        DELETE ="Delete Object";
        fillMap();
        this.type = ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    public void fillMap() throws QueryBuilderException
    {
    	QueryStatementFactory a = new QueryStatementFactory();
    	prepMap.put(FIND_ALL, a.findAllStatement(this, connection));
    	prepMap.put(FIND_BY_ID, a.findByIdStatement(this, connection));
    	prepMap.put(INSERT, a.insertStatement(this, connection));
    	prepMap.put(UPDATE, a.updateStatement(this, connection));
       	prepMap.put(DELETE,  a.deleteStatement(this, connection));
    	
    }
    protected Connection connection() {
        try {
            if (connection == null) {
                throw new IllegalStateException("Connection must not be null");
            }
            if (connection.isClosed()) {
                throw new IllegalStateException("Connection must not be closed");
            }
        } catch (SQLException e) {
            throw new LessonPlanDataAccessException("Failed to validate connection", e);
        }
        return connection;
    }

   
    
    protected abstract String idColumnName();

    protected abstract ArrayList<String> otherColumnNames();

    private String columnList() {
        StringBuffer columnList = new StringBuffer();
        columnList.append(idColumnName());
        for (String columnName : otherColumnNames()) {
            columnList.append(", ").append(columnName);
        }
        return columnList.toString();
    }

    public abstract String tableName();

    public abstract T bind(ResultSet resultSet);

    protected abstract void setValuesOfOtherColumnsIntoStatement(PreparedStatement preparedStatement, T entity);

    public final List<T> findAll() {
       QueryStatementExecution<T> a = new QueryStatementExecution<T>(prepMap.get(FIND_ALL), this);
       return a.execute();
       
    }

    public final T findById(Long id) {
    	try{
    		Registry.getInstance().get(id, type);
    		QueryStatementExecution<T> a = new QueryStatementExecution<T>(prepMap.get(FIND_BY_ID), this);
       	 	return a.executeSingle(id);
    	}
    	catch(EntityNotFoundException e){
    		System.err.print("Das Objekt mit dieser ID ist nicht in der Registry");
    	}
    	
    	
    	 
    }

    public void save(T t) throws EntityAlreadyAddedException{
        if (t.getId() == null) {	
        	//t.setId(idGen);
        	Registry.getInstance().add((EFPersistable)t);
        	DMLStatementExecution<T> a = new DMLStatementExecution<T>(prepMap.get(INSERT)),new Insert());
        	a.execute(t);
      
        } else {
        	Registry.getInstance().forceAdd((EFPersistable)t);
        	DMLStatementExecution<T> a = new DMLStatementExecution<T>(prepMap.get(UPDATE)),new Update());
        	a.execute(t);

        }
    }

    
    public void delete(T t) throws EntityNotFoundException {
    	
    		Registry.getInstance().delete((EFPersistable)t);
    		DMLStatementExecution<T> a = new DMLStatementExecution<T>(prepMap.get(DELETE)),new Delete());
    		a.execute(t);
    	
    }

    public void delete(Long id) throws EntityNotFoundException {
    	
    		Registry.getInstance().delete(id, type);
    		T t = findById(id);
    		DMLStatementExecution<T> a = new DMLStatementExecution<T>(prepMap.get(DELETE)),new Delete());
    		a.execute(t);  	
        
    }

    public abstract PreparedStatement addValuesToStatement(PreparedStatement P);
    protected abstract PreparedStatement addValuesANDIDToStatement(PreparedStatement P);


}
