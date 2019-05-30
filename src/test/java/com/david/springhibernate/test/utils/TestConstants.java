package com.david.springhibernate.test.utils;

public class TestConstants {
	public static final String JDBC_DRIVER = "org.postgresql.Driver";
	public static final String DB_URL = "jdbc:postgresql://localhost:7001/rickcarsharing?currentSchema=carsharing";
	public static final String DB_USER = "rickcar";
	public static final String DB_PASSWORD = "wubba-lubba-dub-dub";
	
	public static final String XML_DAO_USER_DATASET = "src/test/resources/META-INF/dataset/dao/daoUser.xml";
	public static final String XML_DAO_CAR_DATASET = "src/test/resources/META-INF/dataset/dao/daoCar.xml";
	public static final String XML_DAO_RESERVATION_DATASET = "src/test/resources/META-INF/dataset/dao/daoReservation.xml";
	public static final String XML_API_LOGIN_DATASET = "src/test/resources/META-INF/dataset/api/apiLogin.xml";
	public static final String XML_API_USER_DATASET = "src/test/resources/META-INF/dataset/api/apiUser.xml";
	public static final String XML_API_CAR_DATASET = "src/test/resources/META-INF/dataset/api/apiCar.xml";
	
	
	public static final String DB_CREATE_SCRIPT_PATH = "/home/david/eclipse-workspace/RickTasticCarSharing/ddl/create-tables.sql";
	public static final String DB_DROP_SCRIPT_PATH = "/home/david/eclipse-workspace/RickTasticCarSharing/ddl/drop-tables.sql";
	
	public static final String BASE_URL = "http://localhost:8080/carsharing/";
	public static final String API_SESSION_URL = BASE_URL + "api/session";
	public static final String API_USER_URL = BASE_URL + "api/user";
	public static final String API_CAR_URL = BASE_URL + "api/car";
	
	public static final String LOGIN_USERNAME = "admin";
	public static final String LOGIN_PASSWORD = "Password01";
	
	public static final String HTTP_ENCODING_JSON = "application/json";
}
