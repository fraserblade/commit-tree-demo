package com.lbg.cds;

import java.util.Random;

public class CommitTree {

    public static final int MAX_REPORTS = 6;
    public static final int HIERARCHY_DEPTH = 3;
    static Random random = new Random();

    public static void main(String[] args) {

        // Construct the employee hierarchy
        //
        final Employee ceo = new Employee("Charlie Eric Owens (CEO)", 0);
        buildOrgHierarchy(ceo, HIERARCHY_DEPTH);

        // Show the hierarchy
        //
        showHierarchy(ceo);

        // Show DRs only for ceo
        showHierarchy(ceo, false);

        // Walk the hierarchy and total the commits
        //
        System.out.println("Total commits for " + ceo + " " + aggregateCommits(ceo));
        System.out.println("Total commits for " + ceo + " DRs " + aggregateCommits(ceo, false));

        // Same for next report down
        //
        Employee ceoFirstReport = ceo.getReports().get(0);
        System.out.println("Total commits for " + ceoFirstReport + " " + aggregateCommits(ceoFirstReport));

        // Same for last report
        //
        Employee ceoLastReport = ceo.getReports().get(ceo.getReports().size() - 1);
        System.out.println("Total commits for " + ceoLastReport + " " + aggregateCommits(ceoLastReport));

        // Demo getting employee back from map
        //
        System.out.println(ceoLastReport.getEmployeeID());
        Employee e = Employee.map.get(ceoLastReport.getEmployeeID());
        System.out.println(e);
        showHierarchy(e);
    }

    static void buildOrgHierarchy(Employee employee, int depth) {
        if (depth <= 0) {
            return;
        }

        int numReports = random.nextInt(MAX_REPORTS) + 1;
        for (int i = 0; i < numReports; i++) {
            Employee report = new Employee();
            buildOrgHierarchy(report, depth - 1);
            employee.addReport(report);
        }
    }

    static void showHierarchy(Employee manager) {
        showHierarchy(manager, "", true);
    }

    static void showHierarchy(Employee manager, boolean recurse) {
        showHierarchy(manager, "", recurse);
    }

    static void showHierarchy(Employee manager, String indent, boolean recurse) {
        System.out.println(indent + manager + " " + "\t\t(Commits: " + manager.getCommits() + ")");
        for (Employee employee : manager.getReports()) {
            if(recurse) {
                showHierarchy(employee, indent + "   ", true) ;
            }
            else {
                System.out.println("   " + employee  + "\t\t(Commits: " + employee.getCommits() + ")");
            }
        }
    }

    static int aggregateCommits(Employee manager) {
        return aggregateCommits(manager, true);
    }

    static int aggregateCommits(Employee manager, boolean recursive) {
        int totalcommits = manager.getCommits();
        for (Employee employee : manager.getReports()) {
            totalcommits += (recursive ? aggregateCommits(employee) : employee.getCommits());
        }
        return totalcommits;
    }
}
