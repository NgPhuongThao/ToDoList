package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import controller.TaskController;
import controller.TaskController.State;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.Color;

public class ToDoView {
	public static final String ENTER_TASK_NAME = "Enter your task...";
	
	private TaskController controller;
	private JButton addTaskButton;
	JFrame frmTodoList;
	private JTextField taskNameEntry;
	private JTextField taskNameTextfield;
	
	private JPanel listPanel;
	private DefaultListModel<JCheckBox> taskListModel;

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
		frmTodoList.setBounds(100, 100, 1000, 650);
		frmTodoList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Utils.BACKGROUND_COLOR);
		frmTodoList.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(10, 10));
		
		JPanel taskDescriptionPanel = new JPanel();
		mainPanel.add(taskDescriptionPanel, BorderLayout.CENTER);
		taskDescriptionPanel.setLayout(new BorderLayout(0, 0));
		
		taskNameTextfield = new JTextField();
		taskNameTextfield.setForeground(Utils.GENERAL_COLOR);
		taskNameTextfield.setBackground(Utils.BACKGROUND_COLOR);
		taskNameTextfield.setText("Task Name");
		taskNameTextfield.setFont(new Font(Utils.POLICE, Font.BOLD, 28));
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
		mainTitle.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		mainTitle.setHorizontalAlignment(SwingConstants.LEFT);
		mainTitle.setForeground(Utils.GENERAL_COLOR);
		mainTitle.setFont(new Font(Utils.POLICE, Font.BOLD, 38));

		ToDoListPanel.setLayout(new BoxLayout(ToDoListPanel, BoxLayout.Y_AXIS));
		ToDoListPanel.add(mainTitle);
		
		this.listPanel = new JPanel();
		listPanel.setBackground(Utils.BACKGROUND_COLOR);
		this.listPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		this.listPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		taskListModel = new DefaultListModel<>();
		listPanel.setLayout(new GridLayout(0, 1, 0, 0));
		JCheckBoxList list = new JCheckBoxList(taskListModel);
		list.setBackground(Utils.BACKGROUND_COLOR);
		list.setForeground(Utils.GENERAL_COLOR);
		list.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		list.setAlignmentY(Component.TOP_ALIGNMENT);
		list.setAlignmentX(Component.LEFT_ALIGNMENT);
		listPanel.add(list);
		
		JScrollPane scrollPane = new JScrollPane(this.listPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		ToDoListPanel.add(scrollPane);
				
		JPanel addTaskPanel = new JPanel();
		addTaskPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		addTaskPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		addTaskPanel.setBackground(Utils.BACKGROUND_COLOR);
		addTaskPanel.setMaximumSize(new Dimension(350, 20));
		ToDoListPanel.add(addTaskPanel);
		addTaskPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		taskNameEntry = new JTextField();
		taskNameEntry.setForeground(Utils.GENERAL_COLOR);
		taskNameEntry.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		addTaskPanel.add(taskNameEntry);
		taskNameEntry.setText(ENTER_TASK_NAME);
		taskNameEntry.setColumns(15);
		
		addTaskButton = new JButton("Add");
		addTaskButton.setEnabled(false);
		addTaskButton.setForeground(Utils.BACKGROUND_COLOR);
		addTaskButton.setBackground(Utils.ACCENT_COLOR);
		addTaskButton.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		addTaskPanel.add(addTaskButton);
		
		this.controller = new TaskController(this);
		addTaskButton.addActionListener(this.controller);
		this.taskNameEntry.addFocusListener(controller);
	}

	public State getState(JButton button) {
		switch (button.getText()) {
		case "Add":
			return State.ADD;
		}
		return null;
	}

	public void addTask() {
		taskListModel.addElement(new JCheckBox(this.taskNameEntry.getText()));
		
	}

	public void setTextEntry(String newText) {
		if (newText.equals("")) {
			this.addTaskButton.setEnabled(true);
			this.addTaskButton.setBackground(Utils.ACCENT_COLOR);
		}
		else if (newText.equals(ENTER_TASK_NAME)) {
			this.addTaskButton.setEnabled(false);
			this.addTaskButton.setBackground(Utils.GENERAL_COLOR);
		}
		this.taskNameEntry.setText(newText);
	}
	
	public String getTextEntry() {
		return this.taskNameEntry.getText();
	}

}
