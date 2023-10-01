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

    public Employee(int commits) {
        this(NameHelper.getRandomName(), commits);
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
