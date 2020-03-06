package fr.epita.epitrello.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.epita.epitrello.datamodel.Tasks;

public class testJDBCDAO {
	 private static Connection getConnection() throws SQLException {
			
		   
			String url = "jdbc:h2:~/test3";

			// the user :
			String user = "ansh";

			// the password:
			String password = "aaaa";
			   
			try {
				Class.forName ("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			Connection connection = DriverManager.getConnection(url, user, password);
			
			return connection;
		}
	 public static void main(String[] args) {
		 try {
			   Connection connection = getConnection();
			   String CreateTaskQuery = "CREATE TABLE IF NOT EXISTS USERS(USER_NAME VARCHAR(50) PRIMARY KEY);";
		       String CreateListQuery = "CREATE TABLE IF NOT EXISTS LISTS(LIST_NAME VARCHAR(80) PRIMARY KEY);";
		       String CreateQuery = "CREATE TABLE IF NOT EXISTS TASKS( LIST_NAME VARCHAR(80) FOREIGN KEY REFERENCES LISTS(LIST_NAME) ,TASK VARCHAR(100) PRIMARY KEY, EST_TIME INT, PRIORITY INT, DESCRIPTION VARCHAR(200), ASSIGNED_TO VARCHAR(50) FORIEGN KEY REFERENCES USERS(USER_NAME), STATUS VARCHAR(20));";
		       PreparedStatement initialStatement = connection.prepareStatement(CreateQuery);
		       connection.prepareStatement(CreateTaskQuery).execute();
		       connection.prepareStatement(CreateListQuery).execute();
		       
		       initialStatement.execute();
		       initialStatement.close();
		       
		       	String default_status="incomplete";
				String default_assignment="NULL";
				String name="Code", list="Do Everything",description="write code";
				Long estimatedTime=12L;
				int priority=1;
				
				Tasks task = new Tasks();
				task.setTask(name);
				task.setList_name(list);
				task.setEst_time(estimatedTime);
				task.setDescription(description);
				task.setPriority(priority);
				task.setStatus(default_status);
				task.setAssigned_to(default_assignment);
				
		       String insertQuery = "INSERT INTO TASKS (LIST_NAME ,TASK, EST_TIME, PRIOROITY, DESCRIPTION, ASSIGNED_TO, STATUS) VALUES (?,?,?,?,?,?,?);";
		       PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		       preparedStatement.setString(1, task.getList_name());
		       preparedStatement.setString(2, task.getTask());
		       preparedStatement.setLong(3, task.getEst_time());
		       preparedStatement.setInt(4, task.getPriority());
		       preparedStatement.setString(5, task.getDescription());
		       preparedStatement.setString(6, "NULL");
		       preparedStatement.setString(7,"Incomplete");
		       int id = preparedStatement.executeUpdate();
		       boolean success = id!=0;
		       if(success) {
		    	  	System.out.println("Success");
		       }else {
		    	   System.out.println("Couldn't update");
		       }
		       
		   } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
}
