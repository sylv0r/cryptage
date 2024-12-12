package cli.EncryptMenuCLI;

import enums.AlgoAvailable;
import java.util.Scanner;

/*
    * This class is the main controller for the DecryptMenuCLI.
    * It will call the appropriate algorithm to decrypt the password
 */
public class DecryptMenuCLI {

    private static String key;

    public static String main(AlgoAvailable algo, String encryptedPassword) {
        Scanner scanner = new Scanner(System.in);
        switch (algo) {
            case ROTX:
                System.out.println("Please enter your key to decrypt your password:");
                key = scanner.nextLine();
                break;
            case POLYBIUS:
                break;
            case SHA256, MD5, LFSR:
                System.out.println("This algorithm is an hash algorithm, you can't decrypt it.");
                return null;
            default:
                System.out.println("Invalid algorithm");
        }

        return DecryptClass.decrypt(encryptedPassword, key, algo);
    }
}
