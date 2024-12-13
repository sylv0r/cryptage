package cli.EncryptMenuCLI;

import java.util.Scanner;

/*
    * This class is the main controller for the EncryptMenuCLI.
    * It will display the main menu and call the appropriate sub-menu.
 */
public class EncryptMenuCLI {
    public static void main(String password) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an algorithm or option to encrypt your password:");
            System.out.println("1. no algorithm");
            System.out.println("2. Return to main menu");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("=============================");
                    System.out.println("Your password is: " + password);
                    System.out.println("=============================");
                    break;
                case "2":
                    return;
                case "3":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
