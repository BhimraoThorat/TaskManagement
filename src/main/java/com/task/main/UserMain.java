package com.task.main;

import com.task.DAO.UserDAO;
import com.task.DAO.CommentDAO;
import com.task.DAOimplement.UserDAOimplement;
import com.task.DAOimplement.CommentDAOimplement;
import com.task.Entity.Comment;
import com.task.Entity.User;

import java.util.List;
import java.util.Scanner;

public class UserMain {
    private static final UserDAO userDAO = new UserDAOimplement();
    private static final CommentDAO commentDAO = new CommentDAOimplement();

    public static void userManagement(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. Add User");
            System.out.println("2. Retrieve User");
            System.out.println("3. Retrieve All Users");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Back to main menu");

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
                    retrieveAllUser(scanner);
                    break;
                case 4:
                    updateUser(scanner);
                    break;
                case 5:
                    deleteUser(scanner);
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

    private static void retrieveAllUser(Scanner scanner) {
        List<User> users = userDAO.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.println("All users:");
            System.out.printf("%-10s | %-20s | %-20s | %-20s\n",
                    "User ID", "Username", "Email", "Password");
            System.out.println("-".repeat(80));
            for (User user : users) {
                System.out.printf("%-10d | %-20s | %-20s | %-20s\n",
                        user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword());
            }
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

        // Check if the user exists
        User user = userDAO.getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        // Check if there are associated comments
        List<Comment> comments = commentDAO.getCommentsByUserId(userId);
        if (!comments.isEmpty()) {
            System.out.println("Cannot delete the user because there are associated comments.");
            return;
        }

        // If no associated comments, proceed with deletion
        userDAO.deleteUser(userId);
        System.out.println("User deleted: " + user);
    }
}
