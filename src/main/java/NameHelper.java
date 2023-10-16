package src.main.java;

import java.util.Random;

public class NameHelper {

    static Random random = new Random();

    static final String[] RANDOM_FIRST_NAMES = {
            "Alice", "Bob", "Carol", "David", "Eve", "Frank", "Grace", "Helen", "Isaac", "Jack", "Vijah", "Allan", "Mark"
    };
    static final String[] RANDOM_LAST_NAMES = {
            "Smith", "Johnson", "Brown", "Davis", "Wilson", "Anderson", "Clark", "Wright", "Walker", "Hall", "Singh", "Howell"
    };

    static String getRandomName() {
        String firstName = RANDOM_FIRST_NAMES[random.nextInt(RANDOM_FIRST_NAMES.length)];
        String lastName = RANDOM_LAST_NAMES[random.nextInt(RANDOM_LAST_NAMES.length)];
        return firstName + " " + lastName;
    }
}