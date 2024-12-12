package cli.StorageActionCLI;

import FileManager.FileManager;
import cli.EncryptMenuCLI.DecryptMenuCLI;
import enums.AlgoAvailable;
import java.util.List;
import java.util.Scanner;

/**
 * GetPasswordMenuCLI is the CLI that handles the getting of a password.
 */
public class GetPasswordMenuCLI {
    public static String main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the password you want to get :");

        // List all the files
        List<String> files = FileManager.listFiles();
        for (int i = 0; i < files.size(); i++) {
            System.out.println((i + 1) + ". " + files.get(i));
        }

        String choice = scanner.nextLine();

        // Get the password on the file selected
        String encryptedPassword = FileManager.getFileContent(files.get(Integer.parseInt(choice) - 1));

        // Decrypt the password
        AlgoAvailable algo = AlgoAvailable.valueOf(files.get(Integer.parseInt(choice) - 1).split("-")[1]);

        return DecryptMenuCLI.main(algo, encryptedPassword);
    }
}

