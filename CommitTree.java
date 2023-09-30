import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Employee {
    private String firstName;
    private String lastName;
    private List<Employee> subordinates;
    private double salesTotal;

    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subordinates = new ArrayList<>();
        this.salesTotal = 0.0;
    }

    public void addSubordinate(Employee employee) {
        subordinates.add(employee);
    }

    public List<Employee> getSubordinates() {
        return subordinates;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalesTotal() {
        return salesTotal;
    }

    public void addToSalesTotal(double salesAmount) {
        salesTotal += salesAmount;
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
        System.out.println(indent + manager.getFirstName() + " " + manager.getLastName() + " (Sales: $" + manager.getSalesTotal() + ")");
        for (Employee employee : manager.getSubordinates()) {
            traverseHierarchy(employee, indent + "  ");
        }
    }

    public static double aggregateSales(Employee manager) {
        double totalSales = manager.getSalesTotal();
        for (Employee employee : manager.getSubordinates()) {
            totalSales += aggregateSales(employee);
        }
        return totalSales;
    }

    public static void main(String[] args) {
        Employee ceo = new Employee(getRandomFirstName(), getRandomLastName() + " (CEO)");
        ceo.addToSalesTotal(50000.0);

        Employee manager1 = new Employee(getRandomFirstName(), getRandomLastName() + " (Manager)");
        manager1.addToSalesTotal(20000.0);
        manager1.addSubordinate(new Employee(getRandomFirstName(), getRandomLastName() + " (Employee)"));
        manager1.addSubordinate(new Employee(getRandomFirstName(), getRandomLastName() + " (Employee)"));

        Employee manager2 = new Employee(getRandomFirstName(), getRandomLastName() + " (Manager)");
        manager2.addToSalesTotal(15000.0);
        manager2.addSubordinate(new Employee(getRandomFirstName(), getRandomLastName() + " (Employee)"));

        ceo.addSubordinate(manager1);
        ceo.addSubordinate(manager2);

        traverseHierarchy(ceo, "");

        double totalSales = aggregateSales(ceo);
        System.out.println("Total Sales: $" + totalSales);
    }
}
