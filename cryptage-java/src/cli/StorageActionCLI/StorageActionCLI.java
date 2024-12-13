package cli.StorageActionCLI;

import cli.AlgoListMenuCLI.AlgoListMenuCLI;
import cli.VerifHashMenu.VerifyHashStorageMenuCLI;
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
            System.out.println("");
            System.out.println("");
            System.out.println("=======================================");
            System.out.println("  Please choose an option for password management:");
            System.out.println("=======================================");
            System.out.println("");

            System.out.println("Available options:");
            System.out.println("[1] Save a new Password");
            System.out.println("[2] Get a Password");
            System.out.println("[3] Verify your Password hash in a file");
            System.out.println("");

            System.out.println("Not implemented yet:");
            System.out.println("[4] Delete a Password");
            System.out.println("[5] Update a Password");
            System.out.println("");

            System.out.println("=======================================");
            System.out.println("[6] Go back to the main menu");
            System.out.println("[7] Exit");
            System.out.println("=======================================");


            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AlgoListMenuCLI.main(StorageActionsTypes.SAVE);
                    break;
                case "2":
                    System.out.println("=============================");
                    System.out.println("Your password is: " + GetPasswordMenuCLI.main());
                    System.out.println("=============================");
                    break;
                case "3":
                    VerifyHashStorageMenuCLI.GetTrueHashMenuCLI();
                    break;
                case "4":
                    System.out.println("Not implemented yet.");
                    break;
                case "5":
                    System.out.println("Not implemented yet.");
                    break;
                case "6":
                    return;
                case "7":
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
