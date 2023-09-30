import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Employee {
    private final String name;
    private final List<Employee> directReports;
    private int commitCount;
    static Random random = new Random();

    public Employee() {
        this(random.nextInt(100));
    }

    public Employee(int commits){
        this (NameHelper.getRandomName(), commits);
    }

    public Employee(String name, int commits) {
        this.name = name;
        this.directReports = new ArrayList<>();
        this.commitCount = commits;
    }

    public void addReport(Employee employee) {
        directReports.add(employee);
    }

    public List<Employee> getReports() {
        return directReports;
    }

    public String getName() {
        return name;
    }

    public int getCommits() {
        return commitCount;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

public class CommitTree {

    public static final int MAX_REPORTS = 9;
    static Random random = new Random();

    public static void traverseHierarchy(Employee manager, String indent) {
        System.out.println(indent + manager + " " + "\t\t(Commits: " + manager.getCommits() + ")");
        for (Employee employee : manager.getReports()) {
            traverseHierarchy(employee, indent + "  ");
        }
    }

    public static int aggregateCommits(Employee manager) {
        int totalcommits = manager.getCommits();
        for (Employee employee : manager.getReports()) {
            totalcommits += aggregateCommits(employee);
        }
        return totalcommits;
    }

    public static void main(String[] args) {

        // Construct the employee hierarchy
        //
        Employee ceo = new Employee("Charlie Eric OWens" + " (CEO)", 0);
        Employee manager = null;

        for(int i = 0; i < random.nextInt(MAX_REPORTS); i++ ) {
            manager = new Employee();
            for (int j = 0; j < random.nextInt(MAX_REPORTS); j++) {
                manager.addReport(new Employee());
            }
            ceo.addReport(manager);
        }

        // Walk the whole hierarchy
        //
        traverseHierarchy(ceo, "");

        // Walk the hierarchy and total the commits
        //
        int totalCommits = aggregateCommits(ceo);
        System.out.println("Total commits for CEO: " + totalCommits);

        totalCommits = aggregateCommits(manager);
        System.out.println("Total commits for  " + manager + ", " + totalCommits);
    }
}
