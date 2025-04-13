package com.copilotapp.springbootprojectcopilot;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.copilotapp.task.Task;
import com.copilotapp.task.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;

public class TaskManagerTest {
    private TaskManager taskManager;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;
    
    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testAddTask() throws NoSuchFieldException, IllegalAccessException {
        String description = "Test task";
        taskManager.addTask(description);
        
        // Use reflection to access the private 'tasks' field
        Field tasksField = TaskManager.class.getDeclaredField("tasks");
        tasksField.setAccessible(true);
        List<Task> tasks = (List<Task>) tasksField.get(taskManager);
        
        assertEquals(1, tasks.size());
        assertEquals(description, tasks.get(0).getDescription());
    }
    
    @Test
    public void testListTasksEmpty() {
        taskManager.listTasks();
        String output = outContent.toString().trim();
        assertEquals("No tasks available.", output);
    }
    
    @Test
    public void testListTasksWithTasks() {
        taskManager.addTask("Task 1");
        taskManager.addTask("Task 2");
        
        // Clear output to capture only listTasks output
        outContent.reset();
        taskManager.listTasks();
        String output = outContent.toString();
        
        assertTrue(output.contains("Tasks:"));
        assertTrue(output.contains("1. Task 1 (Done: false)"));
        assertTrue(output.contains("2. Task 2 (Done: false)"));
    }
    
    @Test
    public void testMarkTaskAsDoneValidIndex() throws NoSuchFieldException, IllegalAccessException {
        taskManager.addTask("Task 1");
        outContent.reset();
        taskManager.markTaskAsDone(1);
        String output = outContent.toString();
        assertTrue(output.contains("Task marked as done: Task 1"));
        
        // Verify the task is marked as done using reflection
        Field tasksField = TaskManager.class.getDeclaredField("tasks");
        tasksField.setAccessible(true);
        List<Task> tasks = (List<Task>) tasksField.get(taskManager);
        assertTrue(tasks.get(0).isDone());
    }
    
    @Test
    public void testMarkTaskAsDoneInvalidIndex() {
        taskManager.addTask("Task 1");
        outContent.reset();
        taskManager.markTaskAsDone(5);
        String output = outContent.toString().trim();
        assertEquals("Invalid task index.", output);
    }
    @Test
    public void removeTaskTest(){
        taskManager.addTask("Task 1");
        taskManager.addTask("Task 2");
        taskManager.removeTask(1);
        assertEquals(1, taskManager.getTasks().size());
        assertEquals("Task 2", taskManager.getTasks().get(0).getDescription());
    }
}