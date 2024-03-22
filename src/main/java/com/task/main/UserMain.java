package com.task.main;

import com.task.DAO.UserDAO;
import com.task.DAOimplement.UserDAOimplement;
import com.task.Entity.User;

import java.util.Scanner;

public class UserMain {
	private static final UserDAO userDAO = new UserDAOimplement();

	public static void userManagement(Scanner scanner) {
		boolean exit = false;
		while (!exit) {
			System.out.println("Select an option:");
			System.out.println("1. Add User");
			System.out.println("2. Retrieve User");
			System.out.println("3. Update User");
			System.out.println("4. Delete User");
			System.out.println("5. Back to main menu");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				addUser(scanner);
				break;
			case 2:
				retrieveUser(scanner);
				break;
			case 3:
				updateUser(scanner);
				break;
			case 4:
				deleteUser(scanner);
				break;
			case 5:
				exit = true;
				break;
			default:
				System.out.println("Invalid option. Please select a valid option.");
				break;
			}
		}
	}

	private static void addUser(Scanner scanner) {
		System.out.println("Enter username:");
		String username = scanner.nextLine();

		System.out.println("Enter email:");
		String email = scanner.nextLine();

		System.out.println("Enter password:");
		String password = scanner.nextLine();

		User user = new User(username, email, password);
		userDAO.addUser(user);
		System.out.println("User added: " + user);
	}

	private static void retrieveUser(Scanner scanner) {
		System.out.println("Enter user ID:");
		int userId = scanner.nextInt();
		scanner.nextLine();

		User user = userDAO.getUserById(userId);
		if (user != null) {
			System.out.println("Retrieved user: " + user);
		} else {
			System.out.println("User not found.");
		}
	}

	private static void updateUser(Scanner scanner) {
		System.out.println("Enter user ID:");
		int userId = scanner.nextInt();
		scanner.nextLine();

		User user = userDAO.getUserById(userId);
		if (user != null) {
			System.out.println("Enter new username:");
			String username = scanner.nextLine();

			System.out.println("Enter new email:");
			String email = scanner.nextLine();

			System.out.println("Enter new password:");
			String password = scanner.nextLine();

			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);

			userDAO.updateUser(user);
			System.out.println("User updated: " + user);
		} else {
			System.out.println("User not found.");
		}
	}

	private static void deleteUser(Scanner scanner) {
		System.out.println("Enter user ID:");
		int userId = scanner.nextInt();
		scanner.nextLine();

		User user = userDAO.getUserById(userId);
		if (user != null) {
			userDAO.deleteUser(userId);
			System.out.println("User deleted: " + user);
		} else {
			System.out.println("User not found.");
		}
	}
}
