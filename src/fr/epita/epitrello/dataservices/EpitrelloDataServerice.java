package fr.epita.epitrello.dataservices;
import java.sql.SQLException;

import fr.epita.epitrello.datamodel.*;
public class EpitrelloDataServerice {

	public String addUser(String username) {
		//Users.setUser_name(username);
		Users user= new Users();
		user.setUser_name(username);
		EpitrelloJDBCDAO dao=new EpitrelloJDBCDAO();
		dao.createUser(user);
		
		// TODO Auto-generated method stub
		return null;
	}

	public String addList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public String addTask(String list, String name,  long estimatedTime, int priority, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	public String editTask(String task, long estimatedTime, int priority, String description) {
		// TODO Auto-generated method stub
		return null;
	}

	public String assignTask(String task, String user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String printTask(String task) {
		// TODO Auto-generated method stub
		return null;
	}

	public String completeTask(String task) {
		// TODO Auto-generated method stub
		return null;
	}

	public String printUsersByPerformance() {
		// TODO Auto-generated method stub
		//get user list
		//get est_time of each user where status=completed
		//add all the time of different tasks of each user
		
		return null;
	}

	public String printUsersByWorkload() {
		// TODO Auto-generated method stub
		//get user list
		//get est_time of each user
		//add all the time of different tasks of each user
				
		return null;
	}

	public String printUnassignedTasksByPriority() {
		// TODO Auto-generated method stub
		//select tasks where assigned_to=null
		//then order by priority
		return null;
	}

	public String deleteTask(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String printAllUnfinishedTasksByPriority() {
		// TODO Auto-generated method stub
		//select tasks where status=incomplete
		//then order by priority
		return null;
	}

	public String moveTask(String task, String list) {
		// TODO Auto-generated method stub
		return null;
	}

	public String printList(String list) {
		// TODO Auto-generated method stub
		return null;
	}

	public String printAllLists() {
		// TODO Auto-generated method stub
		return null;
	}

	public String printUserTasks(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
