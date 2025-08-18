package com.tdl;

import com.tdl.utils.Employee;
import com.tdl.utils.EmployeeManager;
import com.tdl.utils.Role;

import java.util.Objects;
import java.util.Scanner;

public class Main {

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

        final String INVALID_OPTION_MESSAGE = "\n ==== Invalid option. Please enter one of the following OPTIONS.\n";
        final String OPTIONS = "\n1. View all employees \n2. Add new employee" +
                "\n3. Remove employee \n4. View all employees in role \n5. EXIT\n\n";
        final String WELCOME_MESSAGE = "\n ==== Hello, what would you like to do?\n";
        Scanner scanner = new Scanner(System.in);

        Employee employee1 = new Employee("Janis", "Kalnins", Role.QA);
        Employee employee2 = new Employee("Anna", "Liepa", Role.HR);
        EmployeeManager manager = new EmployeeManager();
        manager.addEmployee(employee1);
        manager.addEmployee(employee2);

        while (!exit) {
            System.out.println(WELCOME_MESSAGE + OPTIONS);
            int input = getUserOption(scanner, INVALID_OPTION_MESSAGE, OPTIONS);

            switch (input) {
                case 1:
                    manager.printAllEmployeeInfo();
                    break;

                case 2:
                    //TODO: finish creating an employee by asking user for a role and finally add the employee to the employee manager
                    // Remember: ideally you want to handle the exception for when user enters an invalid role
                    // in which case he is informed about the error he made and can retry
                    System.out.println("Enter name: ");
                    String nameChoice = scanner.nextLine();

                    System.out.println("Enter surname: ");
                    String surnameChoice = scanner.nextLine();

                    Role roleChoice = getRole(scanner);

                    manager.addEmployee(new Employee(nameChoice, surnameChoice, roleChoice));
                    System.out.println("Employee was successfully added.");
                    break;

                case 3:
                    //TODO: remove the employee and inform the user if it was successful or not
                    System.out.println("Remove employee with ID: ");
                    String choice = scanner.nextLine().trim();

                    try {
                        int idChoice = Integer.parseInt(choice);

                        if (manager.removeEmployee(idChoice)) {
                            System.out.println("Employee with id " + idChoice + " has been successfully removed.");
                        }
                        else {
                            System.out.println("Employee with id " + idChoice + " does not exist.");
                        }
                    }
                    catch (Exception e) {
                        System.out.print("Invalid ID format! The ID should consist of a whole number.\n");
                    }
                    break;

                case 4:
                    //TODO: implement user input for role and then display all employees for the specified role
                    Role specificRoleChoice = getRole(scanner);
                    manager.printAllEmployeeInfoByRole(specificRoleChoice.name());
                    break;

                case 5:
                    //TODO: implement exit mechanism
                    exit = true;
                    System.out.print("You've successfully exited the application.\n");
                    break;
            }
        }
    }

    private static int getUserOption(Scanner scanner, String invalidOptionMessage, String options) {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(input);

                if (number >= 1 && number <= 5) {
                    return number;
                } else {
                    System.out.println(invalidOptionMessage + options);
                }
            }
            catch (NumberFormatException e) {
                System.out.println(invalidOptionMessage + options);
            }
        }
    }

    private static Role getRole(Scanner scanner) {
        final String INVALID_ROLE_MESSAGE = "Invalid role! Please enter one of the listed roles.\n";

        while (true) {
            printAllRoles();
            System.out.println("Enter role: ");
            String roleChoice = scanner.nextLine().trim().toUpperCase();

            try {
                return getRoleByInput(roleChoice);
            } catch (IllegalArgumentException e) {
                System.out.println(INVALID_ROLE_MESSAGE);
            }
        }
    }
}