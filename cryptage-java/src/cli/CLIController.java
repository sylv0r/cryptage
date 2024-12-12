package cli;

import cli.AlgoListMenuCLI.AlgoListMenuCLI;
import cli.SteganoMenuCLI.SteganoMenuCLI;
import cli.StorageActionCLI.StorageActionCLI;
import cli.EncryptMenuCLI.EncryptMenuCLI;
import enums.StorageActionsTypes;

import java.util.Scanner;

/*
    * This class is the main controller for the CLI.
    * It will display the main menu and call the appropriate sub-menu.
 */
public class CLIController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Encrypt a Password");
            System.out.println("2. Decrypt a Password");
            System.out.println("3. Save or Get a Password");
            System.out.println("4. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AlgoListMenuCLI.main(StorageActionsTypes.ENCRYPT);
                    break;
                case "2":
                    AlgoListMenuCLI.main(StorageActionsTypes.DECRYPT);
                    break;
                case "3":
                    StorageActionCLI.main();
                    break;
                case "ClementLeBoss" :
                    SteganoMenuCLI.main();
                    break;
                case "4":
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

