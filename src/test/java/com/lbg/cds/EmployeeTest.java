package com.lbg.cds;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.lbg.cds.Employee;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John Doe", 10); // Create an employee for testing
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", employee.getName());
    }

    @Test
    void testGetCommits() {
        assertEquals(10, employee.getCommits());
    }

    @Test
    void testAddReport() {
        Employee report = new Employee("Alice", 5);
        employee.addReport(report);

        assertTrue(employee.getReports().contains(report));
    }

    @Test
    void testEmployeeIDIsNotNull() {
        assertNotNull(employee.getEmployeeID());
    }

    @Test
    void testToString() {
        String expected = "John Doe - " + employee.getEmployeeID();
        assertEquals(expected, employee.toString());
    }

    @Test
    void testFind() {
        String empID = employee.getEmployeeID();

        Employee e = Employee.find(empID);

        assertEquals(e, employee, "Should be same object");
    }
}
