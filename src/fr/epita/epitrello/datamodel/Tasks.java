package fr.epita.epitrello.datamodel;

public class Tasks {
private String task, description, status, assigned_to, list_name;
public String getTask() {
	return task;
}
public void setTask(String task) {
	this.task = task;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getAssigned_to() {
	return assigned_to;
}
public void setAssigned_to(String assigned_to) {
	this.assigned_to = assigned_to;
}
public String getList_name() {
	return list_name;
}
public void setList_name(String list_name) {
	this.list_name = list_name;
}
public int getEst_time() {
	return est_time;
}
public void setEst_time(int est_time) {
	this.est_time = est_time;
}
public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}
private int est_time, priority;
}
