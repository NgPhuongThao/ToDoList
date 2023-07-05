package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
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
import java.awt.font.TextAttribute;
import java.util.Map;

public class ToDoView {
	private TaskController controller;
	private JButton addTaskButton;
	JFrame frmTodoList;
	private JTextField taskNameEntry;

	private JTextField taskNameTextfield;
	private JTextArea taskDescriptionTextfield;
	private JButton saveTaskButton;

	private JPanel listPanel;
	private DefaultListModel<JCheckBox> taskListModel;
	private JCheckBoxList list;

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
		taskNameTextfield.setEnabled(false);
		taskNameTextfield.setForeground(Utils.GENERAL_COLOR);
		taskNameTextfield.setBackground(Utils.BACKGROUND_COLOR);
		taskNameTextfield.setFont(new Font(Utils.POLICE, Font.BOLD, 28));
		taskDescriptionPanel.add(taskNameTextfield, BorderLayout.NORTH);
		taskNameTextfield.setColumns(10);

		taskDescriptionTextfield = new JTextArea();
		taskDescriptionTextfield.setEnabled(false);
		taskDescriptionTextfield.setBackground(Utils.BACKGROUND_COLOR);
		taskDescriptionTextfield.setForeground(Utils.GENERAL_COLOR);
		taskDescriptionTextfield.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		taskDescriptionPanel.add(taskDescriptionTextfield, BorderLayout.CENTER);

		saveTaskButton = new JButton(Utils.SAVE_CHANGES);
		saveTaskButton.setEnabled(false);
		saveTaskButton.setForeground(Utils.BACKGROUND_COLOR);
		saveTaskButton.setBackground(Utils.GENERAL_COLOR);
		saveTaskButton.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		taskDescriptionPanel.add(saveTaskButton, BorderLayout.SOUTH);

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
		list = new JCheckBoxList(taskListModel);
		list.setBackground(Utils.BACKGROUND_COLOR);
		list.setForeground(Utils.GENERAL_COLOR);
		list.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		list.setAlignmentY(Component.TOP_ALIGNMENT);
		list.setAlignmentX(Component.LEFT_ALIGNMENT);
		listPanel.add(list);

		JScrollPane scrollPane = new JScrollPane(this.listPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
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
		taskNameEntry.setText(Utils.ENTER_TASK_NAME);
		taskNameEntry.setColumns(15);

		addTaskButton = new JButton(Utils.ADD);
		addTaskButton.setEnabled(false);
		addTaskButton.setForeground(Utils.BACKGROUND_COLOR);
		addTaskButton.setBackground(Utils.GENERAL_COLOR);
		addTaskButton.setFont(new Font(Utils.POLICE, Font.PLAIN, 20));
		addTaskPanel.add(addTaskButton);

		// CONTROLLER //
		this.controller = new TaskController(this);

		addTaskButton.addActionListener(this.controller);
		saveTaskButton.addActionListener(this.controller);
		this.taskNameEntry.addFocusListener(controller);
		list.addListSelectionListener(controller);
	}

	public State getState(JButton button) {
		switch (button.getText()) {
		case Utils.ADD:
			return State.ADD;
		case Utils.SAVE_CHANGES:
			return State.SAVE;
		}
		return null;
	}

	public void addTask() {
		taskListModel.add(0, new JCheckBox(this.taskNameEntry.getText()));

	}

	public void addTaskFirst(String taskName) {
		taskListModel.add(0, new JCheckBox(taskName));

	}

	public void addTaskLast(String taskName) {
		JCheckBox checkbox = new JCheckBox(taskName);
		checkbox.setSelected(true);
		taskListModel.addElement(checkbox);

	}

	public void setTextEntry(String newText) {
		if (newText.equals("")) {
			this.addTaskButton.setEnabled(true);
			this.addTaskButton.setBackground(Utils.ACCENT_COLOR);
		} else if (newText.equals(Utils.ENTER_TASK_NAME)) {
			this.addTaskButton.setEnabled(false);
			this.addTaskButton.setBackground(Utils.GENERAL_COLOR);
		}
		this.taskNameEntry.setText(newText);
	}

	public void setDescriptionPanel(String taskName, String taskDescription) {
		this.taskNameTextfield.setText(taskName);
		this.taskDescriptionTextfield.setText(taskDescription);

		this.taskNameTextfield.setEnabled(taskName == null ? false : true);
		this.taskDescriptionTextfield.setEnabled(taskName == null ? false : true);
	}

	public String getTextEntry() {
		return this.taskNameEntry.getText();
	}

	// Returns an array of the description Panel
	public String[] getDescriptionPanel() {
		String[] descriptionPanel = new String[2];

		descriptionPanel[0] = this.taskNameTextfield.getText();
		descriptionPanel[1] = this.taskDescriptionTextfield.getText();

		return descriptionPanel;
	}

	public void setSaveButton(boolean isEnabled) {
		this.saveTaskButton.setEnabled(isEnabled);
		saveTaskButton.setBackground(isEnabled ? Utils.ACCENT_COLOR : Utils.GENERAL_COLOR);
	}
}
