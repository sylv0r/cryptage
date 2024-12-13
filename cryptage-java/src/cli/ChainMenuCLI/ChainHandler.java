package cli.ChainMenuCLI;

import RotX.RotX;
import Md5.Md5;
import Polybius.Polybius;
import Sha256.Sha256;
import LFSR.LFSR;
import enums.LFSROutputType;
import RC4.RC4;
import Vigenere.Vigenere;
import Enigma.Enigma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChainHandler {

    // Encrypt a password using a chain of algorithms
    public static String encryptChain(String password) {
        Scanner scanner = new Scanner(System.in);
        String currentPassword = password; // Start with the initial password
        List<String> algorithmNames = new ArrayList<>(); // List to store applied algorithms

        while (true) {
            // Display the current state of the encrypted password
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("=======================================");
            System.out.println("        Current Encrypted Password     ");
            System.out.println("=======================================");
            System.out.println("Current encrypted password: " + currentPassword);
            System.out.println("");
            System.out.println("Please choose the next algorithm or option:");
            System.out.println("[1] ROTX");
            System.out.println("[2] Polybius");
            System.out.println("[3] Vigenere");
            System.out.println("[4] RC4");
            System.out.println("[5] AES");
            System.out.println("[6] Stop chaining and save");
            System.out.println("=======================================");


            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Apply ROTX encryption with a user-provided key
                    System.out.println("Enter key for ROTX:");
                    String rotKey = scanner.nextLine();
                    try {
                        currentPassword = RotX.encrypte(currentPassword, Integer.parseInt(rotKey));
                        algorithmNames.add("ROTX");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid key. ROTX encryption skipped.");
                    }
                    break;

                case "2":
                    // Apply Polybius encryption
                    currentPassword = Polybius.encryptPolybius(currentPassword);
                    algorithmNames.add("POLYBIUS");
                    break;

                case "3":
                    // Apply Vigenere encryption with a user-provided key
                    System.out.println("Enter key for Vigenere:");
                    String vigenereKey = scanner.nextLine();
                    currentPassword = Vigenere.decryption(currentPassword, vigenereKey);
                    algorithmNames.add("VIGENERE");
                    break;

                case "4":
                    // Apply RC4 encryption with a user-provided key
                    System.out.println("Enter key for RC4:");
                    String rc4Key = scanner.nextLine();
                    currentPassword = RC4.encryptRC4(currentPassword, rc4Key);
                    algorithmNames.add("RC4");
                    break;

                case "5":
                    // Placeholder for AES encryption (not implemented)
                    System.out.println("AES encryption is not yet implemented.");
                    break;

                case "6":
                    // End the chaining process and display the final encrypted password
                    System.out.println("Chaining complete. Final encrypted password: " + currentPassword);

                    // Build and return a formatted string with algorithms in the filename
                    return String.join("-", algorithmNames) + ":" + currentPassword;

                default:
                    // Handle invalid user input
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Decrypt a password using a chain of algorithms
    public static String decryptChain(String encryptedPassword) {
        Scanner scanner = new Scanner(System.in);

        // Ask the user for the file name to extract the chain of algorithms
        System.out.println("Please enter the file name used to save the password :");
        String fileName = scanner.nextLine();

        // Validate the file name
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Error: File name is empty. Please try again.");
            return null;
        }

        // Extract the algorithms from the file name
        String[] parts = fileName.split("-");
        if (parts.length < 2) {
            System.out.println("Invalid file name format. No algorithms found.");
            return null;
        }

        // Retrieve the list of algorithms, excluding the key (first part)
        String[] algorithms = new String[parts.length - 1];
        System.arraycopy(parts, 1, algorithms, 0, algorithms.length);

        // Decrypt the password
        String currentPassword = encryptedPassword;

        for (int i = algorithms.length - 1; i >= 0; i--) { // Process algorithms in reverse order
            String algo = algorithms[i];
            switch (algo) {
                case "ROTX":
                    // Apply ROTX decryption with a user-provided key
                    System.out.println("Enter key for ROTX decryption:");
                    String rotKey = scanner.nextLine();
                    try {
                        currentPassword = RotX.decrypte(currentPassword, Integer.parseInt(rotKey));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid key. ROTX decryption skipped.");
                    }
                    break;

                case "POLYBIUS":
                    // Apply Polybius decryption
                    currentPassword = Polybius.decryptPolybius(currentPassword);
                    break;

                case "MD5":
                case "SHA256":
                    // Hash functions are not decryptable
                    System.out.println(algo + " cannot be decrypted as it is a hash function.");
                    return null;

                default:
                    // Handle unknown algorithms
                    System.out.println("Unknown algorithm: " + algo);
                    return null;
            }
        }

        // Display the final decrypted password
        System.out.println("Decryption complete. Final password: " + currentPassword);
        return currentPassword;
    }
}