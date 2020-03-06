package fr.epita.epitrello.dataservices;

import java.sql.*;

import fr.epita.epitrello.datamodel.Lists;
import fr.epita.epitrello.datamodel.Tasks;
import fr.epita.epitrello.datamodel.Users;

public class EpitrelloJDBCDAO {
	   private static Connection getConnection() throws SQLException {
			
		   
			String url = "jdbc:h2:~/test2";

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

	   public String createUser(Users user) {
		   try {
		   Connection connection = getConnection();
	       String CreateQuery = "CREATE TABLE IF NOT EXISTS USERS(USER_NAME VARCHAR(50) PRIMARY KEY);";
	       String InsertQuery = "INSERT INTO USERS (USER_NAME) VALUES" + "(?)";
	       
	            PreparedStatement createPreparedStatement = connection.prepareStatement(CreateQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
	            
	            PreparedStatement insertPreparedStatement = connection.prepareStatement(InsertQuery);
	            insertPreparedStatement.setString(1, user.getUser_name());
	            insertPreparedStatement.executeUpdate();
	            insertPreparedStatement.close();
	            
	            return "Success";
	        } catch (SQLException e) {
	            return "User already exists";
	        }   
		   
		   
	   }
	   
	   public String createList(Lists list) {
		   try {

		   Connection connection = getConnection();
	       String CreateQuery = "CREATE TABLE IF NOT EXISTS LISTS(LIST_NAME VARCHAR(80) PRIMARY KEY);";
	       String InsertQuery = "INSERT INTO LISTS (LIST_NAME) VALUES" + "(?)";

	            connection.setAutoCommit(false);
	            PreparedStatement createPreparedStatement = connection.prepareStatement(CreateQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();

	            PreparedStatement insertPreparedStatement = connection.prepareStatement(InsertQuery);
	            insertPreparedStatement.setString(1, list.getList_name());
	            insertPreparedStatement.executeUpdate();
	            insertPreparedStatement.close();
	            
	            connection.commit();
	            return "Success";
	        } catch (SQLException e) {
	            return "List already exists";
	        }
   
	   }
	   
	   public String createTask(Tasks task) {
		   try {
			   Connection connection = getConnection();
		       String CreateQuery = "CREATE TABLE IF NOT EXISTS TASKS( LIST_NAME VARCHAR(80) FOREIGN KEY REFERENCES LISTS(LIST_NAME) ,TASK VARCHAR(100) PRIMARY KEY, EST_TIME INT, PRIORITY INT, DESCRIPTION VARCHAR(200), ASSIGNED_TO VARCHAR(50) FORIEGN KEY REFERENCES USERS(USER_NAME), STATUS VARCHAR(20));";
		       PreparedStatement initialStatement = connection.prepareStatement(CreateQuery);
		       initialStatement.execute();
		       initialStatement.close();
		       
		       String insertQuery = "INSERT INTO TASKS (LIST_NAME ,TASK, EST_TIME, PRIOROITY, DESCRIPTION, ASSIGNED_TO, STATUS) VALUES (?,?,?,?,?,?,?);";
		       PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		       preparedStatement.setString(1, task.getList_name());
		       preparedStatement.setString(2, task.getTask());
		       preparedStatement.setLong(3, task.getEst_time());
		       preparedStatement.setInt(4, task.getPriority());
		       preparedStatement.setString(5, task.getDescription());
		       preparedStatement.setString(6, task.getAssigned_to());
		       preparedStatement.setString(7,task.getStatus());
		       int id = preparedStatement.executeUpdate();
		       boolean success = id!=0;
		       if(success) {
		    	   return "Success";
		       }else {
		    	   return "Task already exist";
		       }
		       
		   } catch (SQLException e) {
	            return "Task already exists";
	        }
	   }
	   
	 
	   public String editTask(Tasks edit_task) {
		   try {
		   Connection connection = getConnection();
		   String query = "UPDATE TASKS SET EST_TIME=(?), PRIORITY=(?), SET DESCRIPTION=(?) WHERE TASK=(?);";
		   PreparedStatement preparedStatement = connection.prepareStatement(query);
		   preparedStatement.setLong(1, edit_task.getEst_time());
		   preparedStatement.setInt(2, edit_task.getPriority());
		   preparedStatement.setString(3, edit_task.getDescription());
		   preparedStatement.setString(4, edit_task.getTask());
		   int id = preparedStatement.executeUpdate();
	       boolean success = id!=0;
	       if(success) {
	    	   return "Success";
	       }else {
	    	   return "error";
	       }
		   }catch(SQLException e) {
			   return "error";
		   }
	   }

	   public String assignTask(Tasks assign) {
		   try {
			   Connection connection = getConnection();
			   String query = "UPDATE TASKS SET ASSIGNED_TO=(?) WHERE TASK=(?);";
			   PreparedStatement preparedStatement = connection.prepareStatement(query);
			   preparedStatement.setString(1, assign.getAssigned_to());
			   preparedStatement.setString(2, assign.getTask());
			   int id = preparedStatement.executeUpdate();
		       boolean success = id!=0;
		       if(success) {
		    	   return "Success";
		       }else {
		    	   return "User does not exist";
		       }
			   }catch(SQLException e) {
				   return "error";
			   }
			  	   }

	public String printTask(String task) {
		try {
			   Connection connection = getConnection();
			   String query = "SELECT TASK, DESCRIPTION, PRIORITY, EST_TIME, ASSIGNED_TO FROM TASKS WHERE TASK=(?)";
			   PreparedStatement preparedStatement = connection.prepareStatement(query);//create statement
			   
			   preparedStatement.setString(1, task);//set input parameter
			   ResultSet rs = preparedStatement.executeQuery();//extract data from rs
			   String task_name, desc,assigned_to;
			   String response="";
			   int priority, time;
			   while (rs.next()) {
				   task_name=rs.getString(1);
				   desc = rs.getString(2);
				   priority = rs.getInt(3);
				   time = rs.getInt(4);
				   assigned_to = rs.getString(5);
				   response = task_name+"\n"+desc+"\n"+"Priority: "+priority+"\n"+"Estimated Time: "+time+"\n"+assigned_to;
				}

			   connection.close();
			   return response;
			   }
		catch(SQLException e) {
            return "Task does not exist";
			
		}	
	}

	public String completeTask(String task) {
		try {
			   Connection connection = getConnection();
			   String query = "UPDATE TASKS SET STATUS=(?) WHERE TASK=(?);";
			   PreparedStatement preparedStatement = connection.prepareStatement(query);
			   preparedStatement.setString(1, "COMPLETE");
			   preparedStatement.setString(2, task);
			   int id = preparedStatement.executeUpdate();
		       boolean success = id!=0;
		       if(success) {
		    	   return "Success";
		       }else {
		    	   return "User does not exist";
		       }
			   }catch(SQLException e) {
				   return "error";
			   }
	}

	public String printPerformance() {
		// TODO Auto-generated method stub
		return null;
	}

	public String printWorkload() {
		// TODO Auto-generated method stub
		return null;
	}

	public String printUnassignedPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteTask(String task) {
		try {
			   Connection connection = getConnection();
			   String query = "DELETE FROM TASKS WHERE TASK=(?);;";
			   PreparedStatement preparedStatement = connection.prepareStatement(query);
			   preparedStatement.setString(1, task);
			   preparedStatement.executeUpdate();
			   return "Success";
		}catch(SQLException e) {
				   return "error";
			   }
	}
	

	public String printUnfinishedPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	public String moveTask(Tasks move_task) {
		try {
			   Connection connection = getConnection();
			   String query = "UPDATE TASKS SET LIST_NAME=(?) WHERE TASK=(?);";
			   PreparedStatement preparedStatement = connection.prepareStatement(query);
			   preparedStatement.setString(1, move_task.getList_name());
			   preparedStatement.setString(2, move_task.getTask());
			   preparedStatement.executeUpdate();
			   return "Success";
		}catch(SQLException e) {
				   return "error";
			   }
	
	}

	public String print_list(String list) {
		try {
			   Connection connection = getConnection();
			   String query = "SELECT TASK, DESCRIPTION, PRIORITY, EST_TIME, ASSIGNED_TO, LIST_NAME FROM TASKS WHERE LIST_NAME=(?)";
			   PreparedStatement preparedStatement = connection.prepareStatement(query);//create statement
			   
			   preparedStatement.setString(1, list);//set input parameter
			   ResultSet rs = preparedStatement.executeQuery();//extract data from rs
			   String task_name, desc,assigned_to;
			   String response="List "+list;
			   int priority, time;
			   while (rs.next()) {
				   task_name=rs.getString(1);
				   desc = rs.getString(2);
				   priority = rs.getInt(3);
				   time = rs.getInt(4);
				   assigned_to = rs.getString(5);
				   response = response+"\n"+priority+" | "+task_name+" | "+assigned_to+" | "+time+"h";
				}

			   connection.close();
			   return response;
			   }
		catch(SQLException e) {
         return "List does not exist";
			
		}
	}

	public String printAllLists() {
		try {
			   Connection connection = getConnection();
			   String allList= "SELECT * FROM LISTS;";
			   String query = "SELECT TASK, DESCRIPTION, PRIORITY, EST_TIME, ASSIGNED_TO, LIST_NAME FROM TASKS WHERE LIST_NAME=(?)";
			   PreparedStatement preparedStatement1 = connection.prepareStatement(allList);//create statement
			   ResultSet rs1 = preparedStatement1.executeQuery();
			   String response="";
			   while (rs1.next()) {
				   
			   String list_name= rs1.getString(1);
			   PreparedStatement preparedStatement = connection.prepareStatement(query);//create statement
			   
			   preparedStatement.setString(1, list_name);//set input parameter
			   ResultSet rs = preparedStatement.executeQuery();//extract data from rs
			   String task_name, desc,assigned_to;
			   response="List "+list_name;
			   int priority, time;
			   while (rs.next()) {
				   task_name=rs.getString(1);
				   desc = rs.getString(2);
				   priority = rs.getInt(3);
				   time = rs.getInt(4);
				   assigned_to = rs.getString(5);
				   response = response+"\n"+priority+" | "+task_name+" | "+assigned_to+" | "+time+"h";
				}
			   }
			   connection.close();
			   return response;
			   }
		catch(SQLException e) {
      return "List does not exist";
			
		}
	}

	public String printUserTasks(String user) {
		// TODO Auto-generated method stub
		return null;
	}
	  
}
