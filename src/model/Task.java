package model;

import org.junit.platform.commons.util.StringUtils;

public class Task {
	private boolean checked;
	private String name;
	private String description;
	
	/* Creates a Task
	 * Input :
	 * 		name, String, task name
	 * Error :
	 * 		name can not be blank 
	*/
	public Task (String name) throws IllegalArgumentException {
		if (StringUtils.isBlank(name)) throw new IllegalArgumentException("Task name can not be blank.");
		
		this.checked = false;
		this.name = name;
		this.description = "";
	}
	
	public void setCheck(boolean checkState){
		this.checked = checkState;
	}
	
	public void setName(String name) throws IllegalArgumentException {
		if (StringUtils.isBlank(name)) throw new IllegalArgumentException("Task name can not be empty.");
		this.name = name;
	}
	
	public void setDescription(String description) { 
		this.description = description; 
	}

	public boolean isChecked () {
		return this.checked;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return "{\"name\":\"" + this.name 
				+ "\",\"checked\":\"" + this.checked 
				+ "\",description:\"" + this.description + "\"}";
	}
}
