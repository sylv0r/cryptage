package cli.StorageActionCLI;

import cli.AlgoListMenuCLI.AlgoListMenuCLI;
import cli.EncryptMenuCLI.DecryptMenuCLI;
import enums.StorageActionsTypes;

import java.util.Scanner;

/*
    * This class is the main controller for the StorageActionCLI.
    * It will display the main menu and call the appropriate sub-menu.
 */
public class StorageActionCLI {
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose if you want to save or get or delete or update a password:");
            System.out.println("1. Save a new Password");
            System.out.println("2. Get a Password");
            System.out.println("3. Delete a Password");
            System.out.println("4. Update a Password");
            System.out.println("5. Go back to main menu");
            System.out.println("6. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AlgoListMenuCLI.main(StorageActionsTypes.SAVE);
                    break;
                case "2":
                    System.out.println("your password is: " + GetPasswordMenuCLI.main());
                    break;
                case "3":
                    System.out.println("Not implemented yet.");
                    break;
                case "4":
                    System.out.println("Not implemented yet.");
                    break;
                case "5":
                    return;
                case "6":
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
