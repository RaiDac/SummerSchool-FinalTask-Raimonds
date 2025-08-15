package com.tdl.utils;

import java.util.ArrayList;

public class EmployeeManager {

    ArrayList<Employee> employees = new ArrayList<Employee>();

    //TODO: add constructor here

    private String getEmployeeInfo(Employee e) {
        return "[Employee - id: " + e.getId() + " name: '"
                + e.getName() + "' surname: '" + e.getSurname() + "' role: " + e.getRole().name() + "]";
    }

    public ArrayList<Employee> getEmployees() {
        //TODO: implement functionality
    }

    public void addEmployee(Employee e) {
        //TODO: implement functionality
    }

    public boolean removeEmployee(int id) {
        //TODO: implement functionality
    }

    public void printAllEmployeeInfo(){
        System.out.println(" ===== Employees ====");
        for (Employee e: this.employees) {
            System.out.println(getEmployeeInfo(e));
        }
        System.out.println(" ====================");
    }

    public void printAllEmployeeInfoByRole(String role) {
        System.out.println(" ===== " + role + " ====");
        for (Employee e: this.employees) {
            if (e.getRole().name().equals(role))
                System.out.println(getEmployeeInfo(e));
        }
        System.out.println(" ====================");
    }
}
