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

        String options = "\n1. View all employees \n2. Add new employee" +
                "\n3. Remove employee \n4. View all employees in role \n5. EXIT\n\n";
        String welcomeMessage = "\n ==== Hello, what would you like to do?\n";
        Scanner scanner = new Scanner(System.in);

        Employee employee1 = new Employee("Janis", "Kalnins", Role.QA);
        Employee employee2 = new Employee("Anna", "Liepa", Role.HR);
        EmployeeManager manager = new EmployeeManager();
        manager.addEmployee(employee1);
        manager.addEmployee(employee2);

        while (!exit) {

            System.out.println(welcomeMessage + options);
            int input = scanner.nextInt();

            switch (input){
                case 1:
                    manager.printAllEmployeeInfo();
                    break;

                case 2:
                    System.out.println("Enter name: ");
                    String nameChoice = scanner.next();

                    System.out.println("Enter surname: ");
                    String surnameChoice = scanner.next();

                    String roleChoice = getRole(scanner);
                    //TODO: finish creating an employee by asking user for a role and finally add the employee to the employee manager
                    // Remember: ideally you want to handle the exception for when user enters an invalid role
                    // in which case he is informed about the error he made and can retry
                    break;

                case 3:
                    System.out.println("Remove employee with ID: ");
                    int idChoice = scanner.nextInt();

                    if (manager.removeEmployee(idChoice)) {
                        System.out.println("Employee with id " + idChoice + " has been successfully removed.");
                    }
                    else {
                        System.out.println("Employee with id " + idChoice + " does not exist.");
                    }
                    //TODO: remove the employee and inform the user if it was successful or not
                    break;

                case 4:
                    String specificRoleChoice = getRole(scanner);
                    manager.printAllEmployeeInfoByRole(specificRoleChoice);
                    //TODO: implement user input for role and then display all employees for the specified role
                    break;

                case 5:
                    //TODO: implement exit mechanism
                    exit = true;
                    break;
            }
        }
    }

    private static String getRole(Scanner scanner) {
        String roleChoice;

        do {
            printAllRoles();
            System.out.println("Enter role: ");
            roleChoice = scanner.next();
        } while (!roleChoice.equals(Role.QA.name()) && !roleChoice.equals(Role.DEV.name()) && !roleChoice.equals(Role.HR.name()));

        return roleChoice;
    }
}