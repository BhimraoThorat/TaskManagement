package com.task.main;
import java.time.LocalDateTime;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.task.DAO.CommentDAO;
import com.task.DAO.TaskDAO;
import com.task.DAO.UserDAO;
import com.task.DAOimplement.CommentDAOimplement;
import com.task.DAOimplement.TaskDAOimplement;
import com.task.DAOimplement.UserDAOimplement;
import com.task.Entity.Comment;
import com.task.Entity.Task;
import com.task.Entity.User;



public class CommentMain {
	private static final CommentDAO commentDAO = new CommentDAOimplement();
	private static final TaskDAO taskDAO = new TaskDAOimplement();
	private static final UserDAO userDAO = new UserDAOimplement();

	public static void commentManagement(Scanner scanner) {
		boolean exit = false;
		while (!exit) {
			System.out.println("\nSelect an option:");
			System.out.println("1. Add Comment");
			System.out.println("2. Retrieve Comment");
			System.out.println("3. Retrieve All Comment");
			System.out.println("4. Update Comment");
			System.out.println("5. Delete Comment");
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
				addComment(scanner);
				break;
			case 2:
				retrieveComment(scanner);
				break;
			case 3:
				retrieveAllComment(scanner);
				break;
			case 4:
				updateComment(scanner);
				break;
			case 5:
				deleteComment(scanner);
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

	


	



	private static void addComment(Scanner scanner) {
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
		if (task == null) {
			System.out.println("Task not found. Comment cannot be added.");
			return;
		}

		System.out.println("Enter user ID:");
		int userId;
		try {
			userId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid user ID.");
			scanner.nextLine();
			return;
		}

		User user = userDAO.getUserById(userId);
		if (user == null) {
			System.out.println("User not found. Comment cannot be added.");
			return;
		}

		System.out.println("Enter comment text:");
		String commentText = scanner.nextLine();

		LocalDateTime createdAt = LocalDateTime.now();
		Comment comment = new Comment(task, user, commentText, createdAt);
		commentDAO.addComment(comment);
		System.out.println("Comment added: " + comment);
	}

	private static void retrieveComment(Scanner scanner) {
		System.out.println("Enter comment ID:");
		int commentId;
		try {
			commentId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid comment ID.");
			scanner.nextLine();
			return;
		}

		Comment comment = commentDAO.getCommentById(commentId);
		if (comment != null) {
			System.out.println("Retrieved comment: " + comment);
		} else {
			System.out.println("Comment not found.");
		}
	}
	private static void retrieveAllComment(Scanner scanner) {
	    List<Comment> comments = commentDAO.getAllComments();
	    if (comments.isEmpty()) {
	        System.out.println("No comments found.");
	    } else {
	        System.out.println("All comments:");
	        System.out.printf("%-10s | %-20s | %-20s | %-20s | %-10s\n",
	                "Comment ID", "Text", "User ID", "Task ID", "Created At");
	        System.out.println("-".repeat(90));
	        for (Comment comment : comments) {
	            System.out.printf("%-10d | %-20s | %-20d | %-20d | %-10s\n",
	                    comment.getCommentId(), comment.getCommentText(), comment.getUser().getUserId(),
	                    comment.getTask().getTaskId(), comment.getCreatedAt());
	        }
	    }
	}



	private static void updateComment(Scanner scanner) {
		System.out.println("Enter comment ID to update:");
		int commentId;
		try {
			commentId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid comment ID.");
			scanner.nextLine();
			return;
		}

		Comment comment = commentDAO.getCommentById(commentId);
		if (comment == null) {
			System.out.println("Comment not found.");
			return;
		}

		System.out.println("Enter new comment text:");
		String newCommentText = scanner.nextLine();

		comment.setCommentText(newCommentText);
		commentDAO.updateComment(comment);
		System.out.println("Comment updated: " + comment);
	}

	private static void deleteComment(Scanner scanner) {
		System.out.println("Enter comment ID to delete:");
		int commentId;
		try {
			commentId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid comment ID.");
			scanner.nextLine();
			return;
		}

		Comment comment = commentDAO.getCommentById(commentId);
		if (comment == null) {
			System.out.println("Comment not found.");
			return;
		}

		commentDAO.deleteComment(commentId);
		System.out.println("Comment deleted: " + comment);
	}

}
