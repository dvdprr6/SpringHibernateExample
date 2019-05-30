package com.david.springhibernate.test.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import com.ibatis.common.jdbc.ScriptRunner;

public abstract class DbUnitTest extends DBTestCase{
	
	public DbUnitTest(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, TestConstants.JDBC_DRIVER);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, TestConstants.DB_URL);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, TestConstants.DB_USER);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, TestConstants.DB_PASSWORD);
	}
	
	@Override
	protected IDataSet getDataSet() {
		try {
			return new FlatXmlDataSetBuilder().build(new FileInputStream(getPathToDataset()));
		}catch(DataSetException | FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected void initTables() {
		ScriptRunner scriptRunner = new ScriptRunner(SQLConnection.getSQLConnection(), false, false);
		Reader reader;
		try {
			reader = new FileReader(TestConstants.DB_CREATE_SCRIPT_PATH);
			scriptRunner.runScript(reader);
			cleanlyInsertDataSet();
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void dropTables() {
		ScriptRunner scriptRunner = new ScriptRunner(SQLConnection.getSQLConnection(), false, false);
		Reader reader;
		try {
			reader = new FileReader(TestConstants.DB_DROP_SCRIPT_PATH);
			scriptRunner.runScript(reader);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
	

	protected void initializeTableSequence() {
		try {
			Statement sequenceStatement = SQLConnection.getSQLConnection().createStatement();
			ResultSet resultSet = sequenceStatement.executeQuery("SELECT c.relname FROM pg_class c WHERE c.relkind = 'S';");
			
			while(resultSet.next()) {
				String sequence = resultSet.getString("relname");
				String table = sequence.substring(0, sequence.lastIndexOf("_"));
				Statement updateStatement = SQLConnection.getSQLConnection().createStatement();
				updateStatement.executeQuery("SELECT SETVAL('" + sequence + "', (SELECT MAX(id)+1 FROM " + table + "));");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
}
	
	protected abstract String getPathToDataset();
	
	private void cleanlyInsertDataSet() {
		try {
			IDatabaseTester databaseTester = new JdbcDatabaseTester(
					TestConstants.JDBC_DRIVER,
					TestConstants.DB_URL,
					TestConstants.DB_USER,
					TestConstants.DB_PASSWORD);
			
			databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
			databaseTester.setDataSet(getDataSet());
			databaseTester.onSetup();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
