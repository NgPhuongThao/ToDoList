package dao;

import java.util.Map;

import model.Task;

public abstract class AbstractTaskService {
	public abstract Task getTaskByName(String name);
	
	public abstract Map <String, Task> getAllTask();

	public abstract void addTask(Task task);
	public abstract void updateTask(Task task);
	public abstract void removeTask(String taskName);
}
