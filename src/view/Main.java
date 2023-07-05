package view;

import java.awt.EventQueue;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoView window = new ToDoView();
					window.frmTodoList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
