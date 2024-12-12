package cli.StorageActionCLI;

import FileManager.FileManager;
import cli.EncryptMenuCLI.DecryptMenuCLI;
import enums.AlgoAvailable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * GetPasswordMenuCLI is the CLI that handles the retrieval of a password.
 */
public class GetPasswordMenuCLI {
    public static String main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the password you want to retrieve:");

        // List all the files
        List<String> files = FileManager.listFiles();
        for (int i = 0; i < files.size(); i++) {
            System.out.println((i + 1) + ". " + files.get(i));
        }

        String choice = scanner.nextLine();

        try {
            // Validate choice and retrieve the selected file name
            int fileIndex = Integer.parseInt(choice) - 1;
            if (fileIndex < 0 || fileIndex >= files.size()) {
                System.out.println("Invalid choice. Please select a valid file.");
                return null;
            }
            String selectedFile = files.get(fileIndex);

            // Retrieve the encrypted password from the selected file
            String encryptedPassword = FileManager.getFileContent(selectedFile);

            // Extract the list of algorithms from the file name
            String[] parts = selectedFile.split("-");
            if (parts.length < 2) {
                System.out.println("Invalid file format. No algorithms found.");
                return null;
            }

            // Collect algorithms into a list (in reverse order for decryption)
            List<AlgoAvailable> algorithms = new ArrayList<>();
            for (int i = parts.length - 1; i > 0; i--) {
                try {
                    AlgoAvailable algo = AlgoAvailable.valueOf(parts[i].toUpperCase());
                    algorithms.add(algo);
                } catch (IllegalArgumentException e) {
                    System.out.println("Unknown algorithm: " + parts[i]);
                    return null;
                }
            }

            // Decrypt the password by applying each algorithm in reverse order
            String currentPassword = encryptedPassword;
            for (AlgoAvailable algo : algorithms) {
                System.out.println("Current password: " + currentPassword);
                currentPassword = DecryptMenuCLI.main(algo, currentPassword);

                // If decryption fails, notify and stop
                if (currentPassword == null) {
                    System.out.println("Decryption failed for algorithm: " + algo);
                    return null;
                }
            }

            // Return the fully decrypted password
            System.out.println("Decryption complete. Final password: " + currentPassword);
            return currentPassword;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        return null;
    }
}