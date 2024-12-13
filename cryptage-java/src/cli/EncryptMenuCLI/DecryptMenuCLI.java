package cli.EncryptMenuCLI;

import Enigma.Enigma;
import cli.VerifHashMenu.VerifyHashMenuCLI;
import enums.AlgoAvailable;
import enums.HashMethod;

import java.util.Scanner;
import cli.ChainMenuCLI.ChainHandler;

/*
    * This class is the main controller for the DecryptMenuCLI.
    * It will call the appropriate algorithm to decrypt the password
 */
public class DecryptMenuCLI {

    private static String key;

    public static String main(AlgoAvailable algo, String encryptedPassword) {
        Scanner scanner = new Scanner(System.in);
        switch (algo) {
            case ROTX, VIGENERE, RC4, AES:
                System.out.println("Please enter your " + algo + " key to decrypt your password:");
                key = scanner.nextLine();
                break;
            case POLYBIUS:
                break;
            case LFSR:
                System.out.println("This algorithm is an random algorithm, you can't decrypt it.");
                return null;
            case SHA256, MD5:
                VerifyHashMenuCLI.GetTrueHashMenuCLI(encryptedPassword, HashMethod.valueOf(algo.toString()));
                return null;
            case CHAIN:
                System.out.println("Enter the chain-encrypted password:");
                String chainEncryptedPassword = scanner.nextLine();
                return ChainHandler.decryptChain(chainEncryptedPassword);
            case ENIGMA:
                System.out.println("Please enter the initial position of the rotors (ex: AAA or AZO):");
                key = scanner.nextLine();
                break;
            default:
                System.out.println("Invalid algorithm");
        }

        return DecryptClass.decrypt(encryptedPassword, key, algo);
    }
}
