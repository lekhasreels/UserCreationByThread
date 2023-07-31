package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserCreation {
	public static synchronized void createUser(String username) {
        // Simulating some delay to show parallel access handling
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class UserCreationApp {
	private static final Map<String, Integer> usersMap = new HashMap<>();
    private static final Object lock = new Object();
	private static int userCount;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter username (or 'exit' to quit): ");
            String username = scanner.next();

            if (username.equalsIgnoreCase("exit")) {
                break;
            }

            synchronized (lock) {
                if (usersMap.containsKey(username)) {
                    int count = usersMap.get(username);
                    usersMap.put(username, count + 1);
                    System.out.println("User already exists !... Enter new username... ");
                } else {
                	userCount++;
                    System.out.println("User " + username + " created. Total users: " + userCount);
                    usersMap.put(username, 1);
                    System.out.println("User created successfully.");
                }
            }
        }


        scanner.close();
    }
}