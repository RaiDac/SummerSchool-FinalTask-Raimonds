package com.tdl.utils;

public class Employee {
    private static int counter = 1;
    private int id;
    private String name;
    private String surname;
    private Role role;

    public Employee(String name, String surname, Role role) {
        id = counter++;
        setName(name);
        setSurname(surname);
        this.role = role;
    }

    protected int getId() {
        return id;
    }

    protected String getName() {
        return name;
    }

    protected String getSurname() {
        return surname;
    }

    protected Role getRole() {
        return role;
    }

    protected void setName(String newName) {
        name = newName;
    }

    protected void setSurname(String newSurname) {
        surname = newSurname;
    }
}
