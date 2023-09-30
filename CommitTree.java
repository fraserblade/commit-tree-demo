import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Employee {
    private String firstName;
    private String lastName;
    private List<Employee> directReportsEmployees;
    private double commitCount;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.directReportsEmployees = new ArrayList<>();
        this.commitCount = 0.0;
    }

    public void addSubordinate(Employee employee) {
        directReportsEmployees.add(employee);
    }

    public List<Employee> getReportsEmployees() {
        return directReportsEmployees;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getCommits() {
        return commitCount;
    }

    public void addCommitTotal(double commits) {
        commitCount += commits;
    }
}

public class CommitTree {
    private static final String[] RANDOM_FIRST_NAMES = {
        "Alice", "Bob", "Carol", "David", "Eve", "Frank", "Grace", "Helen", "Isaac", "Jack"
    };
    
    private static final String[] RANDOM_LAST_NAMES = {
        "Smith", "Johnson", "Brown", "Davis", "Wilson", "Anderson", "Clark", "Wright", "Walker", "Hall"
    };

    private static String getRandomFirstName() {
        Random random = new Random();
        int index = random.nextInt(RANDOM_FIRST_NAMES.length);
        return RANDOM_FIRST_NAMES[index];
    }
    
    private static String getRandomLastName() {
        Random random = new Random();
        int index = random.nextInt(RANDOM_LAST_NAMES.length);
        return RANDOM_LAST_NAMES[index];
    }

    public static void traverseHierarchy(Employee manager, String indent) {
        System.out.println(indent + manager.getFirstName() + " " + manager.getLastName() + " (Commits: " + manager.getCommits
    () + ")");
        for (Employee employee : manager.getReportsEmployees()) {
            traverseHierarchy(employee, indent + "  ");
        }
    }

    public static double aggregateCommits(Employee manager) {
        double totalSales = manager.getCommits
    ();
        for (Employee employee : manager.getReportsEmployees()) {
            totalSales += aggregateCommits(employee);
        }
        return totalSales;
    }

    public static void main(String[] args) {
        Employee ceo = new Employee("Charlie", "Nunn" + " (CTO)");
        ceo.addCommitTotal(50000.0);

        Employee manager1 = new Employee(getRandomFirstName(), getRandomLastName() + " (Manager)");
        manager1.addCommitTotal(0);
        manager1.addSubordinate(new Employee(getRandomFirstName(), getRandomLastName() + " (Employee)"));
        manager1.addSubordinate(new Employee(getRandomFirstName(), getRandomLastName() + " (Employee)"));

        Employee manager2 = new Employee(getRandomFirstName(), getRandomLastName() + " (Manager)");
        manager2.addCommitTotal(15000.0);
        manager2.addSubordinate(new Employee(getRandomFirstName(), getRandomLastName() + " (Employee)"));

        ceo.addSubordinate(manager1);
        ceo.addSubordinate(manager2);

        traverseHierarchy(ceo, "");

        double totalSales = aggregateCommits(ceo);
        System.out.println("Total commits for CEO: " + totalSales);
    }
}
