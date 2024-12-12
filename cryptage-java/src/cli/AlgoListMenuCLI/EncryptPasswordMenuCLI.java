package cli.AlgoListMenuCLI;

import LFSR.LFSR;
import Md5.Md5;
import Polybius.Polybius;
import RotX.RotX;
import Sha256.Sha256;
import cli.StorageActionCLI.SavePasswordMenuCLI;
import enums.AlgoAvailable;
import enums.LFSROutputType;
import enums.StorageActionsTypes;

import java.util.Scanner;

public class EncryptPasswordMenuCLI {

    private static String password;
    private static String key;
    private static LFSROutputType outputType;
    private static int iterations;

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

            if (algo == AlgoAvailable.ROTX) {
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
                default:
                    System.out.println("This algorithm is not available for encryption.");
                    return;
            }

            switch (action) {
                case SAVE:
                    SavePasswordMenuCLI.main(encryptedPassword, algo);
                    break;
                case ENCRYPT :
                    System.out.println("Your encrypted password is : " + encryptedPassword);
                    return;
            }
        }
    }
}
