package com.tdl.utils;

import java.util.ArrayList;

public class EmployeeManager {

    private ArrayList<Employee> employees = new ArrayList<Employee>();

    public EmployeeManager() {

    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public boolean removeEmployee(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                return true;
            }
        }

        return false;
    }

    public void printAllEmployeeInfo(){
        System.out.println(" ===== Employees ====");
        for (Employee e: this.employees) {
            System.out.println(e);
        }
        System.out.println(" ====================");
    }

    public void printAllEmployeeInfoByRole(String role) {
        System.out.println(" ===== " + role + " ====");
        for (Employee e: this.employees) {
            if (e.getRole().name().equals(role))
                System.out.println(e);
        }
        System.out.println(" ====================");
    }
}
