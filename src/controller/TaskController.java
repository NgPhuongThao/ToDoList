package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;

import org.junit.platform.commons.util.StringUtils;

import view.ToDoView;

public class TaskController implements ActionListener, FocusListener{
	public enum State {ADD, ENTER_TEXT_NAME};
	
	private ToDoView view;
	private State state;
	
	public TaskController (ToDoView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) 
			this.state = this.view.getState((JButton) e.getSource());

		switch (state) {
			case ADD:
				this.view.addTask();
				this.view.setTextEntry(ToDoView.ENTER_TASK_NAME);
				break;
				
			
			default:
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.view.getTextEntry().equals(ToDoView.ENTER_TASK_NAME)) this.view.setTextEntry("");
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (StringUtils.isBlank(this.view.getTextEntry())) this.view.setTextEntry(ToDoView.ENTER_TASK_NAME);
		
	}
}
