import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommitTree {

    public static final int MAX_REPORTS = 10;
    static Random random = new Random();

    public static void main(String[] args) {

        // Construct the employee hierarchy
        //
        final Employee ceo = new Employee("Charlie Eric Owens (CEO)", 0);
        buildOrgHierarchy(ceo);

        // Show the hierarchy
        //
        traverseHierarchy(ceo);

        // Walk the hierarchy and total the commits
        //
        System.out.println("Total commits for " + ceo + " " + aggregateCommits(ceo));

        // Same for next report down
        //
        Employee ceoFirstReport = ceo.getReports().get(0);
        System.out.println("Total commits for " + ceoFirstReport + " " + aggregateCommits(ceoFirstReport));

        // Same for last report
        //
        Employee ceoLastReport = ceo.getReports().get(ceo.getReports().size() - 1);
        System.out.println("Total commits for " + ceoLastReport + " " + aggregateCommits(ceoLastReport));

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

    private static void traverseHierarchy(Employee manager) {
        traverseHierarchy(manager, "");
    }
    private static void traverseHierarchy(Employee manager, String indent) {
        System.out.println(indent + manager + " " + "\t\t(Commits: " + manager.getCommits() + ")");
        for (Employee employee : manager.getReports()) {
            traverseHierarchy(employee, indent + "  ");
        }
    }

    private static int aggregateCommits(Employee manager) {
        int totalcommits = manager.getCommits();
        for (Employee employee : manager.getReports()) {
            totalcommits += aggregateCommits(employee);
        }
        return totalcommits;
    }
}
