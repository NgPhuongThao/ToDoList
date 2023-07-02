package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.ToDoView;

public class TaskController implements ActionListener{
	public enum State {ADD};
	
	private ToDoView view;
	private State state;
	
	public TaskController (ToDoView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.state = this.view.getState((JButton) e.getSource());
		
		switch (state) {
		case ADD:
			this.view.addTask();
			break;
			
		default:
		}
	}
}
