package com.task.main;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		System.out.println("Welcome to the Task Management System!");

		while (!exit) {
			System.out.println("Select an option:");
			System.out.println("1. Manage Users");
			System.out.println("2. Manage Categories");
			System.out.println("3. Manage Tasks");
			System.out.println("4. Manage Comments");
			System.out.println("5. Exit");

			int option = scanner.nextInt();
			scanner.nextLine();

			switch (option) {
			case 1:
				UserMain.userManagement(scanner);
				break;
			case 2:
				CategoryMain.categoryManagement(scanner);
				break;
			case 3:
				TaskMain.taskManagement(scanner);
				break;
			case 4:
				CommentMain.commentManagement(scanner);
				break;

			case 5:
				exit = true;
				System.out.println("Exiting Task Management System. Goodbye!");
				break;
			default:
				System.out.println("Invalid option. Please select a valid option.");
				break;
			}
		}
		scanner.close();
	}
}
