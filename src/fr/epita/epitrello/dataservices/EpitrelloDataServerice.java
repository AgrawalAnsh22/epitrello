package fr.epita.epitrello.dataservices;
import java.sql.SQLException;

import fr.epita.epitrello.datamodel.*;
public class EpitrelloDataServerice {

	public String addUser(String username) {
		Users user= new Users();
		user.setUser_name(username);
		EpitrelloJDBCDAO dao=new EpitrelloJDBCDAO();
		String response = dao.createUser(user);
				
		return response;
	}

	public String addList(String listName) {
		Lists list= new Lists();
		list.setList_name(listName);
		EpitrelloJDBCDAO dao=new EpitrelloJDBCDAO();
		String response = dao.createList(list);
				
		return response;
	}

	public String addTask(String list, String name,  long estimatedTime, int priority, String description) {
		String default_status="incomplete";
		String default_assignment="NULL";
		Tasks task = new Tasks();
		task.setTask(name);
		task.setList_name(list);
		task.setEst_time(estimatedTime);
		task.setPriority(priority);
		task.setStatus(default_status);
		task.setDescription(description);
		task.setAssigned_to(default_assignment);
		
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.createTask(task);
		return response;
	}

	public String editTask(String task, long estimatedTime, int priority, String description) {
		Tasks edit_task = new Tasks();
		edit_task.setTask(task);
		edit_task.setEst_time(estimatedTime);
		edit_task.setPriority(priority);
		edit_task.setDescription(description);
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.editTask(edit_task);
		
		return response;
	}

	public String assignTask(String task, String user) {
		Tasks assign = new Tasks();
		assign.setAssigned_to(user);
		assign.setTask(task);
		
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.assignTask(assign);
		return response;
	}
	
	public String printTask(String task) {
		
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.printTask(task);
		return response;
	}

	public String completeTask(String task) {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.completeTask(task);
		return response;
	}

	public String printUsersByPerformance() {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.printPerformance();
		return response;
	}

	public String printUsersByWorkload() {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.printWorkload();
		return response;
	}

	public String printUnassignedTasksByPriority() {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.printUnassignedPriority();
		return response;
	}

	public String deleteTask(String task) {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.deleteTask(task);
		return response;
	}

	public String printAllUnfinishedTasksByPriority() {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.printUnfinishedPriority();
		return response;
	}

	public String moveTask(String task, String list) {
		Tasks move_task = new Tasks();
		move_task.setTask(task);
		move_task.setList_name(list);
		
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.moveTask(move_task);
		return response;
	}

	public String printList(String list) {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.print_list(list);
		return response;
	}

	public String printAllLists() {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.printAllLists();
		return response;
	}

	public String printUserTasks(String user) {
		EpitrelloJDBCDAO dao= new EpitrelloJDBCDAO();
		String response= dao.printUserTasks(user);
		return response;
	}

}
