package main;

import com.tdl.utils.Role;
import com.tdl.utils.Employee;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    public void testIdAssignment() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //You need to get the ID from the Employee, but you cannot access it
        // because it is behind a protected method - getId()
        // to get access in order to write unit tests we can get access via reflection which is implemented bellow
        Method method = Employee.class.getDeclaredMethod("getId"); // we get the method by providing the mame
        method.setAccessible(true); //we make it accessible

        //TODO: create the necessary instances for unit test here
        Employee employee1 = new Employee("Test", "Test", Role.HR);
        Employee employee2 = new Employee("Test2", "Test2", Role.QA);
        // bellow variables are casted - (int) appended due to method.invoke() returning an Object not int
        // we know that getId() returns an int, so we can successfully cast it to an int
        int id1 = (int) method.invoke(employee1); //we call the method (remember its getID() on the instance of the object that we pass to the invocation
        int id2 = (int) method.invoke(employee2); //same here for the other instance

        //TODO: complete the unit tests by adding asserts
        assertEquals(1, id1);
        assertEquals(2, id2);
    }
}
