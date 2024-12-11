package cli.AlgoListMenuCLI;

import cli.PasswordStorageCLI.SavingPasswordStorageCLI;
import enums.StorageActionsTypes;

import java.util.Scanner;


/*
    * This class is the main controller for the AlgoListMenuCLI.
    * It will display the main menu and call the appropriate sub-menu.
    * It will display all algorithms available to decrypt and encrypt
 */
public class AlgoListMenuCLI {
    public static void main(StorageActionsTypes action) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an algorithm or option to encrypt your password:");
            System.out.println("1. no algorithm");
            System.out.println("2. Return to main menu");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    SavingPasswordStorageCLI.savePassword(action);
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
