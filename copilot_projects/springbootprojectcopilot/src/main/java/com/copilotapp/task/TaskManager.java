/**
 * The TaskManager class is responsible for managing a list of tasks.
 * It provides methods to add tasks, list all tasks, and mark tasks as done.
 */
package com.copilotapp.task;

import java.util.ArrayList;
import java.util.List;



public class TaskManager {

    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Method to add a new task
    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Task added: " + description);
    }
    // Method to remove a task
    public void removeTask(int taskIndex) {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            System.out.println("Invalid task index.");
        } else {
            Task removedTask = tasks.remove(taskIndex - 1);
            System.out.println("Task removed: " + removedTask.getDescription());
        }
    }
    // Method to get the list of tasks
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    // Method to list all tasks
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". " + task.getDescription() + " (Done: " + task.isDone() + ")");
            }
        }
    }

    // Method to mark a task as done
    public void markTaskAsDone(int taskIndex) {
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            System.out.println("Invalid task index.");
        } else {
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);
            System.out.println("Task marked as done: " + task.getDescription());
        }
    }
}