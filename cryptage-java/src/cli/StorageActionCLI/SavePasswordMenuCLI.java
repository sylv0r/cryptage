package cli.StorageActionCLI;

import FileManager.FileManager;
import cli.AlgoListMenuCLI.AlgoListMenuCLI;
import cli.CLIController;
import enums.AlgoAvailable;
import enums.StorageActionsTypes;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * SavePasswordMenuCLI is the CLI that handles the saving of a password.
 */
public class SavePasswordMenuCLI {
    public static void main(String encryptedPassword, AlgoAvailable algo, String salt) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the key (the index, to find again your password later) you want for your password :");
        String key = scanner.nextLine();

        // check if the key is valid
        while (!key.matches("[a-zA-Z]+")) {
            System.out.println("Invalid key. Please enter a key that contains only letters:");
            key = scanner.nextLine();
        }

        // add the algo to the key
        if(Objects.isNull(salt)){
            key = key + "-" + algo;
        }else{
            key = key + "_" + salt +  "-" + algo;
        }
        // create the file
        FileManager.createFile(key, encryptedPassword);

        System.out.println("Password saved successfully at key " + key + " with algorithm " + algo);

        CLIController.main(null);
    }
}
