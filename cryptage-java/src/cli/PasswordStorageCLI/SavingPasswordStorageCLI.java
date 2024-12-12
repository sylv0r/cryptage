package cli.PasswordStorageCLI;

import enums.StorageActionsTypes;

import java.util.Scanner;

/*
    * This class is the main controller for the PasswordStorageCLI.
    * It will display the main menu and call the appropriate sub-menu.
 */
public class SavingPasswordStorageCLI {
    public static void savePassword(StorageActionsTypes action) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter a name for the password key (only tiny letter and symbol \" - \" :");

            String choice = scanner.nextLine();

            if (choice.matches("[a-z-]+")) {
                System.out.println("Password saved.");
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
