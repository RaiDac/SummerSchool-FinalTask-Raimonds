package main;

import com.tdl.utils.Role;
import com.tdl.utils.Employee;
import com.tdl.utils.EmployeeManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;

public class EmployeeManagerTest {

    @Test
    public void testAddEmployee() {
        //TODO: implement the unit test for addEmployee() function
    }

    @Test
    public void testRemoveEmployee() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //You need to get the ID from the Employee, but you cannot access it
        // because it is behind a protected method - getId()
        // to get access in order to write unit tests we can get access via reflection which is implemented bellow
        Method method = Employee.class.getDeclaredMethod("getId");
        method.setAccessible(true);

        //TODO: create the necessary instances for unit test here
        EmployeeManager manager = new EmployeeManager();
        Employee employee = new Employee("Test", "Test", Role.DEV);
        // bellow variables are casted - (int) appended due to method.invoke() returning an Object not int
        // we know that getId() returns an int, so we can successfully cast it to an int
        int id = (int) method.invoke(employee); //we call the method (remember its getID() on the instance of the object that we pass to the invocation
        manager.removeEmployee(id);

        //TODO: complete the unit tests by adding asserts
        assertEquals(Collections.EMPTY_LIST, manager.getEmployees());
    }
}
