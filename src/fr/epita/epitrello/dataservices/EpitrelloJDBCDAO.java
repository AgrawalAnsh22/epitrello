package fr.epita.epitrello.dataservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.epita.epitrello.datamodel.Lists;
import fr.epita.epitrello.datamodel.Tasks;
import fr.epita.epitrello.datamodel.Users;

public class EpitrelloJDBCDAO {
	 // JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	  // static final String DB_URL = ;  
	   
	   //  Database credentials 
	   static final String USER = "ansh"; 
	   static final String PASS = "";
	   	   
	   public static int createUser(Users user) throws SQLException {
		   Connection connection = getConnection();
	       PreparedStatement createPreparedStatement = null;
	       PreparedStatement insertPreparedStatement = null;
//	       PreparedStatement selectPreparedStatement = null;
	       String CreateQuery = "CREATE TABLE IF NOT EXISTS USERS(USER_NAME VARCHAR(50) PRIMARY KEY);";
	       String InsertQuery = "INSERT INTO USERS" + "(USER_NAME) values" + "(?)";
//         String SelectQuery = "select * from PERSON";
	        try {
	            connection.setAutoCommit(false);
	            createPreparedStatement = connection.prepareStatement(CreateQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
	           
	            insertPreparedStatement = connection.prepareStatement(InsertQuery);
	            insertPreparedStatement.setString(1, user.getUser_name());
	            insertPreparedStatement.executeUpdate();
	            insertPreparedStatement.close();
	            connection.commit();
	        } catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        
	        }   
		   
		   return 0;
		   
	   }
	   
	   public int addList(Lists list) throws SQLException {
		   Connection connection = getConnection();
	       PreparedStatement createPreparedStatement = null;
	       PreparedStatement insertPreparedStatement = null;
	       String CreateQuery = "CREATE TABLE IF NOT EXISTS LISTS(LIST_NAME VARCHAR(80) PRIMARY KEY);";
	       String InsertQuery = "INSERT INTO LISTS" + "(LIST_NAME) values" + "(?)";
	        try {
	            connection.setAutoCommit(false);
	            createPreparedStatement = connection.prepareStatement(CreateQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
	           
	            insertPreparedStatement = connection.prepareStatement(InsertQuery);
	            insertPreparedStatement.setString(1, list.getList_name());
	            insertPreparedStatement.executeUpdate();
	            insertPreparedStatement.close();
	            connection.commit();
	        } catch (SQLException e) {
	            System.out.println("Exception Message " + e.getLocalizedMessage());
	        
	        }  
		   return 0;
   
	   }
	   
	   public int addTask(Tasks task) {
			return 0;
 
	   }
	   
	  
	   private static Connection getConnection() throws SQLException {
			// to connect to the database you need 4 information :
			// the url : TODO : change hardcoded values by values from the configuration
			// file
			String url = "jdbc:h2:~/test";

			// the user :
			String user = "ansh";

			// the password:
			String password = "";

			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		}
	  
}
