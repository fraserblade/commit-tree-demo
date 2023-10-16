package src.main.java;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EmployeeIDGenerator {

    private static Set<String> generatedIDs = new HashSet<>();
    private static Random random = new Random();

    public static String generateUniqueEmployeeID() {
        String employeeID;
        do {
            employeeID = generateRandomEmployeeID();
        } while (generatedIDs.contains(employeeID));

        generatedIDs.add(employeeID);
        return employeeID;
    }

    private static String generateRandomEmployeeID() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
