package com.task.main;

import com.task.DAO.CategoryDAO;
import com.task.DAOimplement.CategoryDAOimplement;
import com.task.Entity.Category;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CategoryMain {
	private static final CategoryDAO categoryDAO = new CategoryDAOimplement();

	public static void categoryManagement(Scanner scanner) {
		boolean exit = false;
		while (!exit) {
			System.out.println("\nSelect an option:");
			System.out.println("1. Add Category");
			System.out.println("2. Retrieve Category");
			System.out.println("3. Update Category");
			System.out.println("4. Delete Category");
			System.out.println("5. Back to main menu");

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
				addCategory(scanner);
				break;
			case 2:
				retrieveCategory(scanner);
				break;
			case 3:
				updateCategory(scanner);
				break;
			case 4:
				deleteCategory(scanner);
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

	private static void addCategory(Scanner scanner) {
		System.out.println("Enter category name:");
		String categoryName = scanner.nextLine();

		Category category = new Category(categoryName);
		categoryDAO.addCategory(category);
		System.out.println("Category added: " + category);
	}

	private static void retrieveCategory(Scanner scanner) {
		System.out.println("Enter category ID:");
		int categoryId;
		try {
			categoryId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid category ID.");
			scanner.nextLine();
			return;
		}

		Category category = categoryDAO.getCategoryById(categoryId);
		if (category != null) {
			System.out.println("Retrieved category: " + category);
		} else {
			System.out.println("Category not found.");
		}
	}

	private static void updateCategory(Scanner scanner) {
		System.out.println("Enter category ID to update:");
		int categoryId;
		try {
			categoryId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid category ID.");
			scanner.nextLine();
			return;
		}

		Category category = categoryDAO.getCategoryById(categoryId);
		if (category == null) {
			System.out.println("Category not found.");
			return;
		}

		System.out.println("Enter new category name:");
		String newCategoryName = scanner.nextLine();

		category.setCategoryName(newCategoryName);
		categoryDAO.updateCategory(category);
		System.out.println("Category updated: " + category);
	}

	private static void deleteCategory(Scanner scanner) {
		System.out.println("Enter category ID to delete:");
		int categoryId;
		try {
			categoryId = scanner.nextInt();
			scanner.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid category ID.");
			scanner.nextLine();
			return;
		}

		Category category = categoryDAO.getCategoryById(categoryId);
		if (category == null) {
			System.out.println("Category not found.");
			return;
		}

		categoryDAO.deleteCategory(categoryId);
		System.out.println("Category deleted: " + category);
	}
}
