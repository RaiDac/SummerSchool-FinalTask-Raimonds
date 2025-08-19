package main;

import com.tdl.utils.Role;
import com.tdl.utils.Employee;
import com.tdl.utils.EmployeeManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EmployeeManagerTest {

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("Test", "Test", Role.QA);
        EmployeeManager manager = new EmployeeManager();

        manager.addEmployee(employee);

        assertEquals(1, manager.getEmployees().size());
        assertTrue(manager.getEmployees().contains(employee));
    }

    @Test
    public void testRemoveEmployee() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //You need to get the ID from the Employee, but you cannot access it
        // because it is behind a protected method - getId()
        // to get access in order to write unit tests we can get access via reflection which is implemented bellow
        Method method = Employee.class.getDeclaredMethod("getId");
        method.setAccessible(true);

        EmployeeManager manager = new EmployeeManager();
        Employee employee1 = new Employee("Test", "Test", Role.DEV);
        Employee employee2 = new Employee("Test2", "Test2", Role.HR);

        manager.addEmployee(employee1);
        manager.addEmployee(employee2);
        // bellow variables are casted - (int) appended due to method.invoke() returning an Object not int
        // we know that getId() returns an int, so we can successfully cast it to an int
        int id = (int) method.invoke(employee1); //we call the method (remember its getID() on the instance of the object that we pass to the invocation

        manager.removeEmployee(id);

        assertEquals(1, manager.getEmployees().size());
        assertFalse(manager.getEmployees().contains(employee1));
        assertTrue(manager.getEmployees().contains(employee2));
    }
}
