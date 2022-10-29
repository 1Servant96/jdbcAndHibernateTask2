package peaksoft;

import peaksoft.service.UserServiceImpl;
import java.util.Scanner;

public class Main {
    public static Scanner scannerS = new Scanner(System.in);
    public static Scanner scannerN = new Scanner(System.in);

    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        while (true) {
            queries();
            String inputS = scannerS.nextLine();
            switch (inputS) {
                case "1" -> userService.createUsersTable();
                case "2" -> userService.saveUser(scannerS.nextLine(), scannerS.nextLine(), scannerN.nextByte());
                case "3" -> System.out.println(userService.getAllUsers());
                case "4" -> userService.cleanUsersTable();
                case "5" -> userService.dropUsersTable();
                case "6" -> userService.removeUserById(scannerN.nextLong());
            }
            if (inputS.equals("x"))
                break;
        }
    }

    public static void queries() {
        System.out.println("Press 1 to create a users table\n" +
                "Press 2 to save user\n" +
                "Press 3 to get all users\n" +
                "Press 4 to clean users table\n" +
                "Press 5 to drop users table\n" +
                "Press 6 to remove user by id\n" +
                "Press 7 to finish the program\n" +
                "-----------------------------------"
        );
    }
}
