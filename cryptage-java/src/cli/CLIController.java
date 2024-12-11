package cli;

import cli.StorageActionCLI.StorageActionCLI;
import cli.EncryptMenuCLI.EncryptMenuCLI;

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
            System.out.println("1. Uncrytpe a Password");
            System.out.println("2. Save or Get a Password");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    EncryptMenuCLI.main(choice);
                    break;
                case "2":
                    StorageActionCLI.main();
                    break;
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

