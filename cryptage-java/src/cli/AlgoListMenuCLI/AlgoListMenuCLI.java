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
            System.out.println("");
            System.out.println("");
            System.out.println("");
            if (action == StorageActionsTypes.DECRYPT) {
                System.out.println("=======================================");
                System.out.println(" Please choose a decryption algorithm:");
                System.out.println("=======================================");
            } else {
                System.out.println("=======================================");
                System.out.println(" Please choose an encryption or hashing algorithm:");
                System.out.println("=======================================");
            }

            System.out.println("Available encryption algorithms:");
            System.out.println("[1] Rot(X)");
            System.out.println("[2] Polybius");
            System.out.println("[3] Vigenere");
            System.out.println("[4] RC4");
            System.out.println("[5] AES");
            System.out.println("");

            System.out.println("Available hashing algorithms:");
            if (action == StorageActionsTypes.DECRYPT) {
                System.out.println("[6] Verify integrity of Md5");
            } else {
                System.out.println("[6] Md5");
            }
            if (action == StorageActionsTypes.DECRYPT) {
                System.out.println("[7] Verify integrity of Sha256");
            } else {
                System.out.println("[7] Sha256");
            }
            System.out.println("");

            System.out.println("Available random generator algorithms:");
            System.out.println("[8] LFSR");
            System.out.println("");

            if (action != StorageActionsTypes.DECRYPT) {
                System.out.println("Other options:");
                System.out.println("[9] Chain Encrypt");
            }
            System.out.println("");

            System.out.println("[10] Return to main menu");
            System.out.println("[11] Exit");
            System.out.println("=======================================");


            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    algoSelected = AlgoAvailable.ROTX;
                    break;
                case "2":
                    algoSelected = AlgoAvailable.POLYBIUS;
                    break;
                case "3":
                    algoSelected = AlgoAvailable.VIGENERE;
                    break;
                case "4":
                    algoSelected = AlgoAvailable.RC4;
                    break;
                case "5":
                    algoSelected = AlgoAvailable.AES;
                    break;
                case "6":
                    algoSelected = AlgoAvailable.MD5;
                    break;
                case "7":
                    algoSelected = AlgoAvailable.SHA256;
                    break;
                case "8":
                    algoSelected = AlgoAvailable.LFSR;
                    break;
                case "9":
                    if (action == StorageActionsTypes.ENCRYPT) {
                        System.out.println("Invalid choice. Please try again.");
                        continue;
                    }
                    algoSelected = AlgoAvailable.CHAIN;
                    break;
                case "10":
                    CLIController.main(null);
                case "11":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            switch (action) {
                case SAVE, ENCRYPT:
                    EncryptPasswordMenuCLI.main(algoSelected, action);
                    break;
                case DECRYPT:
                    System.out.println("Please enter your encrypted password or hash:");
                    String encryptedPassword = scanner.nextLine();
                    String decryptedPassword = DecryptMenuCLI.main(algoSelected, encryptedPassword);
                    System.out.println("Your decrypted password is: " + decryptedPassword);
                    break;
            }
        }
    }
}
