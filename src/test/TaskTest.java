package test;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TaskTest {
	private Task testTask;
	private String taskName;
	
	@BeforeEach
	public void setUp() {
		this.taskName = "Testing the Task class";
		this.testTask = new Task (taskName);
	}
	
	@AfterEach
	public void tearDown() {
		this.taskName = null;
		this.testTask = null;
	}
	

	@Test
	@DisplayName("Creation of a task")
	public void test1() {
		assertAll("Creation of a valid task", 
			() -> assertTrue(this.testTask.isChecked() == false,"Valid task checked value when created"), 
			() -> assertTrue(this.testTask.getName() == this.taskName,"Valid task name when created"), 
			() -> assertTrue(this.testTask.getDescription() == "","Valid task description when created"), 
			() -> assertThrows( IllegalArgumentException.class, () -> { new Task(""); }), 
			() -> assertThrows( IllegalArgumentException.class, () -> { new Task(" "); })
		);
	}
	
	@Test
	@DisplayName("Changing task attributes")
	public void test2() {
		assertAll("Creation of a valid task", 
			() -> {
					this.testTask.setCheck(true);
					assertTrue(testTask.isChecked() == true,"Task checked");
				}, 
			() -> {
				this.testTask.setName("Task name changed");
				assertTrue(testTask.getName() == "Task name changed","Task name changed");
			},  
			() -> assertThrows( IllegalArgumentException.class, () -> { this.testTask.setName(""); }), 
			() -> {
				this.testTask.setDescription("Task description changed");
				assertTrue(testTask.getDescription() == "Task description changed","Task description changed");
			});
	}

}
