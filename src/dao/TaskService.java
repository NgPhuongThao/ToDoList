package dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Task;

public class TaskService extends AbstractTaskService {
	private static final String DATA_PATH = (new File("").getAbsolutePath()) + "/res/todos.json";
	private static final JSONParser PARSER = new JSONParser();
	
	public TaskService () {
		
	}

	@Override
	public Task getTaskByName(String name) {
		try {
			JSONArray data = (JSONArray) PARSER.parse(new FileReader(DATA_PATH));
			for (Object o : data) {
				JSONObject jsonObject = (JSONObject) o;
				
				if (jsonObject.get("name").equals(name)) {
					Task task = new Task((String) jsonObject.get("name"));
					task.setCheck((boolean) jsonObject.get("checked"));
					task.setDescription((String) jsonObject.get("description"));
					
					return task;
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Map<String, Task> getAllTask() {
		try {
			JSONArray data = (JSONArray) PARSER.parse(new FileReader(DATA_PATH));
			
			Map<String, Task> allTasks = new HashMap<>();
			
			for (Object o : data) {
				JSONObject jsonObject = (JSONObject) o;

				Task task = new Task((String) jsonObject.get("name"));
				task.setCheck((boolean) jsonObject.get("checked") ? true : false);
				task.setDescription((String) jsonObject.get("description"));
					
				allTasks.put(task.getName(), task);
			}
			return allTasks;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addTask(Task task) {
		removeTask(task.getName());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", task.getName());
		jsonObject.put("checked", task.isChecked());
		jsonObject.put("description", task.getDescription());

		FileWriter file;
		try {
			JSONArray data = (JSONArray) PARSER.parse(new FileReader(DATA_PATH));
			String newFileContent = data.toString().substring(0, data.toString().length() - 1);
			newFileContent = newFileContent + "," + jsonObject.toJSONString() + "]";
			
			// Write in file
			file = new FileWriter(DATA_PATH);
			file.write(newFileContent);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateTask(Task task) {
		removeTask(task.getName());
		addTask(task);
	}

	@Override
	public void removeTask(String taskName) {
		try {
			JSONArray data = (JSONArray) PARSER.parse(new FileReader(DATA_PATH));
			
			for (Object o : data) {
				JSONObject jsonObject = (JSONObject) o;
				
				if (taskName.equals((String) jsonObject.get("name").toString())) {
					data.remove(o);
					
					// Write in file
					FileWriter file = new FileWriter(DATA_PATH);
					file.write(data.toJSONString());
					file.close();
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
