package src.main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Employee {
    private final String name;
    private final List<Employee> reports;
    private final int empNo;
    private int commitCount;
    private String employeeID;
    static Random random = new Random();
    static private int empCounter = 1;

    public Employee() {
        this(random.nextInt(50));
    }

    public Employee(int commits) {
        this(NameHelper.getRandomName(), commits);
    }

    public Employee(String name, int commits) {
        this.name = name;
        this.reports = new ArrayList<>();
        this.commitCount = commits;
        this.empNo = empCounter++;
        this.employeeID = EmployeeIDGenerator.generateUniqueEmployeeID();
    }

    public void addReport(Employee employee) {
        reports.add(employee);
    }

    public List<Employee> getReports() {
        return reports;
    }

    public String getName() {
        return name;
    }

    public int getCommits() {
        return commitCount;
    }

    public String getEmployeeID() { return employeeID; }
    @Override
    public String toString() {
        return this.getName() + " - " + this.employeeID + (reports.size() > 0 ? " (" + reports.size() + ")" : "");
    }
}
