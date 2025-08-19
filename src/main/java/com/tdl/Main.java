package com.tdl;

import com.tdl.utils.Employee;
import com.tdl.utils.EmployeeManager;
import com.tdl.utils.Role;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String ADD_EMPLOYEE_SUCCESS_MESSAGE = "Employee was successfully added.";
    private static final String EXIT_MESSAGE = "You've successfully exited the application.\n";
    private static final String INVALID_ROLE_MESSAGE = "Invalid role! Please enter one of the listed roles.\n";
    private static final String INVALID_OPTION_MESSAGE = "\n ==== Invalid option. Please enter one of the following OPTIONS.\n";
    private static final String OPTIONS = "\n1. View all employees \n2. Add new employee" +
            "\n3. Remove employee \n4. View all employees in role \n5. EXIT\n\n";
    private static final String REMOVE_SUCCESS_MESSAGE = "Employee with id %d has been successfully removed.";
    private static final String REMOVE_NOT_FOUND_MESSAGE = "Employee with id %d does not exist.";
    private static final String REMOVE_INVALID_ID_MESSAGE = "Invalid ID format! The ID should consist of a whole number.\n";
    /**
     Print the enum values so that they can be displayed in console/terminal output
     */
    public static void printAllRoles(){
        System.out.println("Available roles; ");
        int elemCount = Role.values().length;

        for (int i = 0; i < elemCount; i++) {
            if (i == elemCount - 1){
                System.out.print(Role.values()[i] + "\n\n");
            } else {
                System.out.print(Role.values()[i] + ", ");
            }
        }
    }

    /**
     * Return a role object that matches the String value provided
     * @param input role name as String
     * @return Role
     * @throws IllegalArgumentException when the argument input does not match any role
     */
    public static Role getRoleByInput(String input) throws IllegalArgumentException {
        for (Role r: Role.values()) {
            if (Objects.equals(input, r.name())) {
                return r;
            }
        }
        throw new IllegalArgumentException("No such role: '" + input + "' found!");
    }

    public static void main(String[] args) {
        boolean exit = false;

        final String WELCOME_MESSAGE = "\n ==== Hello, what would you like to do?\n";
        Scanner scanner = new Scanner(System.in);

        Employee employee1 = new Employee("Janis", "Kalnins", Role.QA);
        Employee employee2 = new Employee("Anna", "Liepa", Role.HR);
        EmployeeManager manager = new EmployeeManager();
        manager.addEmployee(employee1);
        manager.addEmployee(employee2);

        while (!exit) {
            System.out.println(WELCOME_MESSAGE + OPTIONS);
            int input = getUserOption(scanner);

            switch (input) {
                case 1:
                    manager.printAllEmployeeInfo();
                    break;

                case 2:
                    addEmployee(scanner, manager);
                    break;

                case 3:
                    removeEmployee(scanner, manager);
                    break;

                case 4:
                    Role specificRoleChoice = getRole(scanner);
                    manager.printAllEmployeeInfoByRole(specificRoleChoice.name());
                    break;

                case 5:
                    exit = true;
                    System.out.print(EXIT_MESSAGE);
                    break;
            }
        }
    }

    private static int getUserOption(Scanner scanner) {
        String input;

        do {
            input = scanner.nextLine().trim();

            if (!isValidOption(input)) {
                System.out.println(INVALID_OPTION_MESSAGE + OPTIONS);
            }
        } while (!isValidOption(input));

        return Integer.parseInt(input);
    }

    private static boolean isValidOption(String input) {
        final int MAX_MENU_NUMBER = 5;

        try {
            int number = Integer.parseInt(input);
            return number >= 1 && number <= MAX_MENU_NUMBER;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void addEmployee(Scanner scanner, EmployeeManager manager) {
        System.out.println("Enter name: ");
        String nameChoice = scanner.nextLine();

        System.out.println("Enter surname: ");
        String surnameChoice = scanner.nextLine();

        Role roleChoice = getRole(scanner);

        manager.addEmployee(new Employee(nameChoice, surnameChoice, roleChoice));
        System.out.println(ADD_EMPLOYEE_SUCCESS_MESSAGE);
    }

    private static void removeEmployee(Scanner scanner, EmployeeManager manager) {
        System.out.println("Remove employee with ID: ");
        String choice = scanner.nextLine().trim();

        try {
            int idChoice = Integer.parseInt(choice);

            if (manager.removeEmployee(idChoice)) {
                System.out.printf((REMOVE_SUCCESS_MESSAGE) + "%n", idChoice);
            }
            else {
                System.out.printf((REMOVE_NOT_FOUND_MESSAGE) + "%n", idChoice);
            }
        }
        catch (Exception e) {
            System.out.print(REMOVE_INVALID_ID_MESSAGE);
        }
    }

    private static Role getRole(Scanner scanner) {
        String roleChoice;

        while (true) {
            printAllRoles();
            System.out.println("Enter role: ");
            roleChoice = scanner.nextLine().trim().toUpperCase();

            try {
                return getRoleByInput(roleChoice);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_ROLE_MESSAGE);
            }
        }
    }
}