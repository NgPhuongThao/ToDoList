package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.junit.platform.commons.util.StringUtils;

import model.Task;
import view.ToDoView;
import view.Utils;

public class TaskController implements ActionListener, FocusListener, ListSelectionListener{
	public enum State {ADD, ENTER_TEXT_NAME, SAVE};
	
	private ToDoView view;
	private State state;
	
	private Map<String, Task> taskMap;
	private String currentSelectedTask;
	
	public TaskController (ToDoView view) {
		this.view = view;
		this.taskMap = new HashMap<>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) 
			this.state = this.view.getState((JButton) e.getSource());

		switch (state) {
			case ADD:
				this.view.addTask();
				taskMap.put(this.view.getTextEntry(), new Task(this.view.getTextEntry()));
				this.view.setTextEntry(Utils.ENTER_TASK_NAME);
				break;
				
			case SAVE:
				// Update task
				Task task = this.taskMap.get(this.currentSelectedTask);
				
				String[] descriptionPanel = this.view.getDescriptionPanel();
				
				task.setName(descriptionPanel[0]);
				task.setDescription(descriptionPanel[1]);
				
				// Update task map
				taskMap.remove(this.currentSelectedTask);
				taskMap.put(task.getName(), task);
				break;
				
			default:
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.view.getTextEntry().equals(Utils.ENTER_TASK_NAME)) this.view.setTextEntry("");
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (StringUtils.isBlank(this.view.getTextEntry())) this.view.setTextEntry(Utils.ENTER_TASK_NAME);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		JList<JCheckBox> list = (JList<JCheckBox>) e.getSource();
		if (!e.getValueIsAdjusting() && list.getSelectedValue() != null) {//This line prevents double events
			// Get task name and description to display on the left panel
			this.currentSelectedTask = list.getSelectedValue().getText();
			String selectedTaskDescription = this.taskMap.get(this.currentSelectedTask).getDescription();
			
			this.view.setDescriptionPanel(this.currentSelectedTask, selectedTaskDescription);
	    }
	}
}
