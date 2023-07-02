package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import controller.TaskController;
import controller.TaskController.State;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class ToDoView {
	private TaskController controller;

	JFrame frmTodoList;
	private JTextField taskNameEntry;
	private JTextField taskNameTextfield;
	private DefaultListModel<String> taskListModel;
	private JList<String> taskList;

	/**
	 * Create the application.
	 */
	public ToDoView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTodoList = new JFrame();
		frmTodoList.setResizable(false);
		frmTodoList.setTitle("To-Do List");
		frmTodoList.setBounds(100, 100, 1000, 750);
		frmTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		frmTodoList.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel taskDescriptionPanel = new JPanel();
		mainPanel.add(taskDescriptionPanel, BorderLayout.CENTER);
		taskDescriptionPanel.setLayout(new BorderLayout(0, 0));
		
		taskNameTextfield = new JTextField();
		taskNameTextfield.setForeground(Utils.GENERAL_COLOR);
		taskNameTextfield.setBackground(Utils.BACKGROUND_COLOR);
		taskNameTextfield.setText("Task Name");
		taskNameTextfield.setFont(new Font("Arial", Font.BOLD, 28));
		taskNameTextfield.setEditable(false);
		taskDescriptionPanel.add(taskNameTextfield, BorderLayout.NORTH);
		taskNameTextfield.setColumns(10);
		
		JTextArea taskDescriptionTextfield = new JTextArea();
		taskDescriptionTextfield.setBackground(Utils.BACKGROUND_COLOR);
		taskDescriptionTextfield.setForeground(Utils.GENERAL_COLOR);
		taskDescriptionTextfield.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		taskDescriptionTextfield.setText("Enter task description...");
		taskDescriptionPanel.add(taskDescriptionTextfield, BorderLayout.CENTER);
		
		JPanel ToDoListPanel = new JPanel();
		ToDoListPanel.setBackground(Utils.BACKGROUND_COLOR);
		mainPanel.add(ToDoListPanel, BorderLayout.WEST);
		
		JLabel mainTitle = new JLabel("To-Do List");
		mainTitle.setHorizontalAlignment(SwingConstants.LEFT);
		mainTitle.setForeground(Utils.GENERAL_COLOR);
		mainTitle.setFont(new Font(Utils.POLICE, Font.BOLD, 38));
		
		taskListModel = new DefaultListModel<String>();
		taskList = new JList<String>(taskListModel);
		taskList.setBackground(Utils.BACKGROUND_COLOR);
		taskList.setForeground(Utils.GENERAL_COLOR);
		taskList.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		taskList.setPreferredSize(new Dimension(100,620));
		taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskList.setVisibleRowCount(20);
		ToDoListPanel.setLayout(new BoxLayout(ToDoListPanel, BoxLayout.Y_AXIS));
		ToDoListPanel.add(mainTitle);
		
		JPanel addTaskPanel = new JPanel();
		addTaskPanel.setBackground(Utils.BACKGROUND_COLOR);
		ToDoListPanel.add(addTaskPanel);
		
		taskNameEntry = new JTextField();
		taskNameEntry.setForeground(Utils.GENERAL_COLOR);
		taskNameEntry.setFont(new Font("Arial", Font.PLAIN, 20));
		addTaskPanel.add(taskNameEntry);
		taskNameEntry.setText("Enter your task...");
		taskNameEntry.setColumns(15);
		
		JButton addTaskButton = new JButton("Add");
		addTaskButton.setEnabled(false);
		addTaskButton.setForeground(Utils.BACKGROUND_COLOR);
		addTaskButton.setBackground(Utils.ACCENT_COLOR);
		addTaskButton.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		addTaskPanel.add(addTaskButton);
		ToDoListPanel.add(taskList);
		
		this.controller = new TaskController(this);
		addTaskButton.addActionListener(this.controller);
	}

	public State getState(JButton button) {
		switch (button.getText()) {
		case "Add":
			return State.ADD;
		}
		return null;
	}

	public void addTask() {
		this.taskListModel.addElement(this.taskNameEntry.getText());
	}

}
