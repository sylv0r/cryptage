package cli.AlgoListMenuCLI;

import Enigma.Enigma;
import LFSR.LFSR;
import Md5.Md5;
import Polybius.Polybius;
import RotX.RotX;
import Sha256.Sha256;
import Vigenere.Vigenere;
import cli.StorageActionCLI.SavePasswordMenuCLI;
import enums.AlgoAvailable;
import enums.LFSROutputType;
import enums.StorageActionsTypes;
import cli.ChainMenuCLI.ChainHandler;

import java.util.Optional;
import java.util.Scanner;

/**
 * This class is the main controller for the EncryptPasswordMenuCLI.
 * It will call the appropriate algorithm to encrypt the password
 */
public class EncryptPasswordMenuCLI {

    // Variables
    private static String password;
    private static String key;
    private static LFSROutputType outputType;
    private static int iterations;
    private static String salt = "";

    private static String encryptedPassword;

    public static void main(AlgoAvailable algo, StorageActionsTypes action) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (algo == AlgoAvailable.LFSR) {
                System.out.println("Please enter your seed :");
            } else {
                System.out.println("Please enter your password :");
            }

            password = scanner.nextLine();

            // do the logic for the key and salt
            if (algo == AlgoAvailable.ROTX || algo == AlgoAvailable.VIGENERE) {
                System.out.println("Please enter your key :");
                key = scanner.nextLine();
            } else if (algo == AlgoAvailable.LFSR) {
                System.out.println("Please enter the output type :");
                System.out.println("1. BINARY");
                System.out.println("2. DECIMAL");
                String choice = scanner.nextLine();
                outputType = choice.equals("1") ? LFSROutputType.BINAIRE : LFSROutputType.DECIMAL;
                System.out.println("Please enter the number of iterations :");
                iterations = Integer.parseInt(scanner.nextLine().substring(0, 9));
            } else if (algo == AlgoAvailable.MD5 || algo == AlgoAvailable.SHA256) {
                System.out.println("Do you want to add salt?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    // Generate LFSR output and append to the password
                    salt = LFSR.lfsr(password, 1, LFSROutputType.DECIMAL);
                    password = salt + password;
                    System.out.println("Your salt is : " + salt);
                } else if (!choice.equals("2")) {
                    System.out.println("Invalid choice. Proceeding without salt.");
                }
            }

            switch (algo) {
                case ROTX:
                    encryptedPassword = RotX.encrypte(password, Integer.parseInt(key));
                    break;
                case MD5:
                    encryptedPassword = Md5.createHashWithMd5(password);
                    break;
                case POLYBIUS:
                    encryptedPassword = Polybius.encryptPolybius(password);
                    System.out.println("Your encrypted password is : " + encryptedPassword);
                    break;
                case SHA256:
                    encryptedPassword = Sha256.createHashWithSha256(password);
                    break;
                case LFSR:
                    encryptedPassword = LFSR.lfsr(password, iterations, outputType);
                    break;
                case CHAIN:
                    encryptedPassword = ChainHandler.encryptChain(password);
                    break;
                case ENIGMA:
                    encryptedPassword = Enigma.main(password);
                    break;
                case VIGENERE:
                    encryptedPassword = Vigenere.encryption(password, key);
                    break;
                default:
                    System.out.println("This algorithm is not available for encryption.");
                    return;
            }

            switch (action) {
                case SAVE:
                    SavePasswordMenuCLI.main(encryptedPassword, algo, salt);
                    break;
                case ENCRYPT :
                    System.out.println("Your encrypted password is : " + encryptedPassword);
                    return;
            }
        }
    }
}
