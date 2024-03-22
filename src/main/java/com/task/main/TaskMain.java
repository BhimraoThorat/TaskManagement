package com.task.main;

import com.task.DAO.TaskDAO;
import com.task.DAO.UserDAO;
import com.task.DAO.CategoryDAO;
import com.task.DAOimplement.TaskDAOimplement;
import com.task.DAOimplement.UserDAOimplement;
import com.task.DAOimplement.CategoryDAOimplement;
import com.task.Entity.Category;
import com.task.Entity.Task;
import com.task.Entity.User;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskMain {
	private static final TaskDAO taskDAO = new TaskDAOimplement();
	private static final UserDAO userDAO = new UserDAOimplement();
	private static final CategoryDAO categoryDAO = new CategoryDAOimplement();

	public static void taskManagement(Scanner scanner) {
		boolean exit = false;
		while (!exit) {
			System.out.println("\nSelect an option:");
			System.out.println("1. Add Task");
			System.out.println("2. Retrieve Task");
			System.out.println("3. Retrieve All Tasks");
			System.out.println("4. Update Task");
			System.out.println("5. Delete Task");
			System.out.println("6. Back to main menu");

			int option;
			try {
				option = scanner.nextInt();
				scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
				continue;
			}

			switch (option) {
			case 1:
				addTask(scanner);
				break;
			case 2:
				retrieveTask(scanner);
				break;
			case 3:
				retrieveAllTasks();
				break;
			case 4:
				updateTask(scanner);
				break;
			case 5:
				deleteTask(scanner);
				break;
			case 6:
				exit = true;
				break;
			default:
				System.out.println("Invalid option. Please select a valid option.");
				break;
			}
		}
	}

	private static void addTask(Scanner scanner) {
		System.out.println("Enter title:");
		String title = scanner.nextLine();

		System.out.println("Enter description:");
		String description = scanner.nextLine();

		System.out.println("Enter due date (YYYY-MM-DD):");
		LocalDate dueDate;
		try {
			dueDate = LocalDate.parse(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
			return;
		}

		System.out.println("Enter priority:");
		String priority = scanner.nextLine();

		System.out.println("Enter status:");
		String status = scanner.nextLine();

		User assignedTo = retrieveUser(scanner);
		if (assignedTo == null) {
			System.out.println("User not found. Task cannot be assigned.");
			return;
		}

		Category category = retrieveCategory(scanner);
		if (category == null) {
			System.out.println("Category not found. Task cannot be assigned.");
			return;
		}

		Task task = new Task(title, description, dueDate, priority, status, assignedTo, category);
		taskDAO.addTask(task);
		System.out.println("Task added: " + task);
	}

	private static void retrieveTask(Scanner scanner) {
		System.out.println("Enter task ID:");
		int taskId;
		try {
			taskId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid task ID.");
			scanner.nextLine();
			return;
		}

		Task task = taskDAO.getTaskById(taskId);
		if (task != null) {
			System.out.println("Retrieved task: " + task);
		} else {
			System.out.println("Task not found.");
		}
	}

	private static void retrieveAllTasks() {
	    List<Task> tasks = taskDAO.getAllTasks();
	    if (tasks.isEmpty()) {
	        System.out.println("No tasks found.");
	    } else {
	        System.out.println("All tasks:");
	        System.out.printf("%-10s | %-20s | %-10s | %-12s | %-15s | %-15s | %-20s | %-20s\n",
	                "Task ID", "Title", "Due Date", "Priority", "Status", "Assigned To", "Category", "Created At");
	        System.out.println("-".repeat(120));
	        for (Task task : tasks) {
	            System.out.printf("%-10d | %-20s | %-10s | %-12s | %-15s | %-15s | %-20s | %-20s\n",
	                    task.getTaskId(), task.getTitle(), task.getDueDate(), task.getPriority(),
	                    task.getStatus(), task.getAssignedTo().getUsername(), task.getCategory().getCategoryName(),
	                    task.getCreatedAt());
	        }
	    }
	}




	private static void updateTask(Scanner scanner) {
		System.out.println("Enter task ID:");
		int taskId;
		try {
			taskId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid task ID.");
			scanner.nextLine();
			return;
		}

		Task existingTask = taskDAO.getTaskById(taskId);
		if (existingTask == null) {
			System.out.println("Task not found.");
			return;
		}

		System.out.println("Enter new title:");
		String title = scanner.nextLine();

		System.out.println("Enter new description:");
		String description = scanner.nextLine();

		System.out.println("Enter new due date (YYYY-MM-DD):");
		LocalDate dueDate;
		try {
			dueDate = LocalDate.parse(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
			return;
		}

		System.out.println("Enter new priority:");
		String priority = scanner.nextLine();

		System.out.println("Enter new status:");
		String status = scanner.nextLine();

		User assignedTo = retrieveUser(scanner);
		if (assignedTo == null) {
			System.out.println("User not found. Task cannot be assigned.");
			return;
		}

		Category category = retrieveCategory(scanner);
		if (category == null) {
			System.out.println("Category not found. Task cannot be assigned.");
			return;
		}

		Task updatedTask = new Task();
		updatedTask.setTaskId(taskId);
		updatedTask.setTitle(title);
		updatedTask.setDescription(description);
		updatedTask.setDueDate(dueDate);
		updatedTask.setPriority(priority);
		updatedTask.setStatus(status);
		updatedTask.setAssignedTo(assignedTo);
		updatedTask.setCategory(category);

		taskDAO.updateTask(updatedTask);
		System.out.println("Task updated successfully.");
	}

	private static void deleteTask(Scanner scanner) {
		System.out.println("Enter task ID:");
		int taskId;
		try {
			taskId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid task ID.");
			scanner.nextLine();
			return;
		}

		Task existingTask = taskDAO.getTaskById(taskId);
		if (existingTask == null) {
			System.out.println("Task not found.");
			return;
		}

		taskDAO.deleteTask(taskId);
		System.out.println("Task deleted successfully.");
	}

	private static User retrieveUser(Scanner scanner) {
		System.out.println("Enter user ID:");
		int userId;
		try {
			userId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid user ID.");
			scanner.nextLine();
			return null;
		}

		return userDAO.getUserById(userId);
	}

	private static Category retrieveCategory(Scanner scanner) {
		System.out.println("Enter category ID:");
		int categoryId;
		try {
			categoryId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid category ID.");
			scanner.nextLine();
			return null;
		}

		return categoryDAO.getCategoryById(categoryId);
	}
}
