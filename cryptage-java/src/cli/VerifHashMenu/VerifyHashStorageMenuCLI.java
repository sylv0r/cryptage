package cli.VerifHashMenu;

import FileManager.FileManager;
import VerifHash.VerifHash;
import enums.AlgoAvailable;
import enums.HashMethod;

import java.util.List;
import java.util.Scanner;

/**
 * This class is the main controller for the VerifyHashMenuCLI.
 * It will call the appropriate algorithm to verify the hash
 */
public class VerifyHashStorageMenuCLI {

    private static String fileName;
    private static String password;
    private static String hash;
    private static String salt;
    private static HashMethod algo;
    private static String encryptedPassword;

    /**
     * GetTrueHashMenuCLI is the CLI that handles verifying the hash from a file.
     */
    public static void GetTrueHashMenuCLI() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the password you want to get:");

        // Get the list of files
        List<String> files = FileManager.listFiles();

        // Display only files that contain a hash
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).contains(AlgoAvailable.MD5.toString())
                    || files.get(i).contains(AlgoAvailable.SHA256.toString())) {
                continue;
            } else {
                files.remove(i);
            }
        }
        for (int i = 0; i < files.size(); i++) {
            System.out.println((i + 1) + ". " + files.get(i));
        }

        String choice = scanner.nextLine();

        // Set the file name to the selected file
        fileName = files.get(Integer.parseInt(choice) - 1);
        // Get the password from the selected file
        encryptedPassword = FileManager.getFileContent(fileName);
        // Extract algorithm name from the file name
        algo = HashMethod.valueOf(fileName.split("-")[1]);
        // Extract the salt from the file name if it exists
        salt = fileName.contains("_") ? fileName.split("_")[1].split("-")[0] : null;

        System.out.println("Please enter the password you want to verify:");
        password = scanner.nextLine();

        VerifHash.isTrueHash(password, encryptedPassword, algo, salt);
    }
}
