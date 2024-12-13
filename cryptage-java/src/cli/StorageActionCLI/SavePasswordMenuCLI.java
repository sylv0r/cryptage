package cli.StorageActionCLI;

import FileManager.FileManager;
import enums.AlgoAvailable;

import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

/**
 * SavePasswordMenuCLI is the CLI that handles the saving of a password.
 */
public class SavePasswordMenuCLI {
    public static void main(String encryptedPassword, AlgoAvailable algo, String salt) {
        Scanner scanner = new Scanner(System.in);

        if (encryptedPassword == null) {
            System.out.println("Error: Encrypted data is null. Please try again.");
            cli.CLIController.main(null);
            return;
        }

        System.out.println("Please enter the key (the index, to find again your password later) you want for your password :");
        String key = scanner.nextLine();

        // check if the key is valid
        while (!key.matches("[a-zA-Z]+")) {
            System.out.println("Invalid key. Please enter a key that contains only letters:");
            key = scanner.nextLine();
        }

        String finalPassword;
        String fileName;

        // check if the algorithm is a chain
        if (algo == AlgoAvailable.CHAIN) {
            // split the encryptedData into algorithms and the final password
            String[] parts = encryptedPassword.split(":");
            if (parts.length < 2) {
                System.out.println("Error: Encrypted data format is invalid for chain encryption.");
                cli.CLIController.main(null);
                return;
            }

            String algorithms = parts[0]; // extract the algorithm chain
            finalPassword = parts[1]; // extract the encrypted password

            // add the algo to the key
            if(Objects.isNull(salt)){
                fileName = key + "-" + algorithms;
            }else{
                fileName = key + "_" + salt +  "-" + algorithms;
            }
        } else {
            // for non-chain algorithms, use the algorithm name
            finalPassword = encryptedPassword;
            // add the algo to the key
            if(Objects.isNull(salt)){
                fileName = key + "-" + algo;
            }else{
                fileName = key + "_" + salt +  "-" + algo;
            }
        }

        FileManager.createFile(fileName, finalPassword);

        System.out.println("Password saved successfully at key: " + fileName);
        cli.CLIController.main(null);
    }
}
