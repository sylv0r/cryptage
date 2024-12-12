package cli.AlgoListMenuCLI;

import cli.CLIController;
import cli.EncryptMenuCLI.DecryptMenuCLI;
import cli.PasswordStorageCLI.SavingPasswordStorageCLI;
import enums.AlgoAvailable;
import enums.StorageActionsTypes;

import java.util.Scanner;


/*
    * This class is the main controller for the AlgoListMenuCLI.
    * It will display the main menu and call the appropriate sub-menu.
    * It will display all algorithms available to decrypt and encrypt
 */
public class AlgoListMenuCLI {

    private static AlgoAvailable algoSelected;

    public static void main(StorageActionsTypes action) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (action == StorageActionsTypes.DECRYPT) {
                System.out.println("Please choose an algorithm to decrypt your password:");
            } else {
                System.out.println("Please choose an algorithm or option to encrypt your password:");
            }
            System.out.println("1. Rot(X)");
            System.out.println("2. Md5");
            System.out.println("3. Polybius");
            System.out.println("4. Sha256");
            System.out.println("5. AES");
            System.out.println("6. LFSR");
            System.out.println("7. Return to main menu");
            System.out.println("8. Exit");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    algoSelected = AlgoAvailable.ROTX;
                    break;
                case "2":
                    algoSelected = AlgoAvailable.MD5;
                    break;
                case "3":
                    algoSelected = AlgoAvailable.POLYBIUS;
                    break;
                case "4":
                    algoSelected = AlgoAvailable.SHA256;
                    break;
                case "5":
                    algoSelected = AlgoAvailable.AES;
                    break;
                case "6":
                    algoSelected = AlgoAvailable.LFSR;
                    break;
                case "7":
                    CLIController.main(null);
                case "8":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
            }

            switch (action) {
                case SAVE, ENCRYPT:
                    EncryptPasswordMenuCLI.main(algoSelected, action);
                    break;
                case DECRYPT:
                    System.out.println("Please enter your encrypted password:");
                    String encryptedPassword = scanner.nextLine();
                    String decryptedPassword = DecryptMenuCLI.main(algoSelected, encryptedPassword);
                    System.out.println("Your decrypted password is: " + decryptedPassword);
                    break;
            }
        }
    }
}
