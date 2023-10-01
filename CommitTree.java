import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Employee {
    private final String name;
    private final List<Employee> directReports;
    private int commitCount;
    static Random random = new Random();

    public Employee() {
        this(random.nextInt(50));
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

    public static final int MAX_REPORTS = 10;
    static Random random = new Random();

    public static void traverseHierarchy(Employee manager) {
        traverseHierarchy(manager, "");
    }
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
        final Employee ceo = new Employee("Charlie Eric OWens" + " (CEO)", 0);
        buildOrgHierarchy(ceo);

        // Show the hierarchy
        //
        traverseHierarchy(ceo);

        // Walk the hierarchy and total the commits
        //
        System.out.println("Total commits for " + ceo + ", " + aggregateCommits(ceo));

        // Same for next report down
        //
        Employee ceoFirstReport = ceo.getReports().get(1);
        System.out.println("Total commits for " + ceoFirstReport + ", " + aggregateCommits(ceoFirstReport));
    }

    private static void buildOrgHierarchy(Employee ceo) {
        Employee manager = null;

        random.nextInt();

        for(int i = 0; i < random.nextInt(MAX_REPORTS) + 1; i++ ) {
            manager = new Employee();
            for (int j = 0; j < random.nextInt(MAX_REPORTS) + 1; j++) {
                manager.addReport(new Employee());
            }
            ceo.addReport(manager);
        }
    }
}
