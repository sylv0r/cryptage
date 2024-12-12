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
public class VerifyHashMenuCLI {

    private static String password;
    private static String salt;

    /**
     * GetTrueHashMenuCLI is the CLI that handles verifying the hash.
     */
    public static void GetTrueHashMenuCLI(String hash, HashMethod algo) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the password you want to verify :");
        password = scanner.nextLine();

        System.out.println("Please enter the salt if it exists else press enter :");
        salt = scanner.nextLine();

        // Call the VerifHash class to verify the hash
        VerifHash.isTrueHash(password, hash, algo, salt);
    }
}
