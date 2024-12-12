package cli.ChainMenuCLI;

import RotX.RotX;
import Md5.Md5;
import Polybius.Polybius;
import Sha256.Sha256;
import LFSR.LFSR;
import enums.LFSROutputType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChainHandler {

    // Encrypt a password using a chain of algorithms
    public static String encryptChain(String password) {
        Scanner scanner = new Scanner(System.in);
        String currentPassword = password;
        List<String> algorithmNames = new ArrayList<>();

        while (true) {
            System.out.println("\nCurrent encrypted password: " + currentPassword);
            System.out.println("Please choose the next algorithm or option:");
            System.out.println("1. ROTX");
            System.out.println("2. MD5");
            System.out.println("3. Polybius");
            System.out.println("4. Sha256");
            System.out.println("5. AES");
            System.out.println("6. LFSR");
            System.out.println("7. Stop chaining and save");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter key for ROTX:");
                    String rotKey = scanner.nextLine();
                    try {
                        currentPassword = RotX.encrypte(currentPassword, Integer.parseInt(rotKey));
                        algorithmNames.add("ROTX");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid key. ROTX encryption skipped.");
                    }
                    break;

                case "2":
                    currentPassword = Md5.createHashWithMd5(currentPassword);
                    algorithmNames.add("MD5");
                    break;

                case "3":
                    currentPassword = Polybius.encryptPolybius(currentPassword);
                    algorithmNames.add("POLYBIUS");
                    break;

                case "4":
                    currentPassword = Sha256.createHashWithSha256(currentPassword);
                    algorithmNames.add("SHA256");
                    break;

                case "5":
                    System.out.println("AES encryption is not yet implemented.");
                    break;

                case "6":
                    System.out.println("Enter seed for LFSR:");
                    String seed = scanner.nextLine();
                    System.out.println("Enter output type (1 for BINARY, 2 for DECIMAL):");
                    LFSROutputType outputType = scanner.nextLine().equals("1") ? LFSROutputType.BINAIRE : LFSROutputType.DECIMAL;
                    System.out.println("Enter number of iterations:");
                    try {
                        int iterations = Integer.parseInt(scanner.nextLine());
                        currentPassword = LFSR.lfsr(seed, iterations, outputType);
                        algorithmNames.add("LFSR");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. LFSR encryption skipped.");
                    }
                    break;

                case "7":
                    System.out.println("Chaining complete. Final encrypted password: " + currentPassword);

                    // Build and return a formatted string with algorithms in the filename
                    return String.join("-", algorithmNames) + ":" + currentPassword;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static String decryptChain(String encryptedPassword) {
        Scanner scanner = new Scanner(System.in);

        // Demander le nom du fichier pour extraire les algorithmes
        System.out.println("Please enter the file name used to save the password (e.g., toto-ROTX-POLYBIUS-MD5):");
        String fileName = scanner.nextLine();

        // Validation du nom du fichier
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Error: File name is empty. Please try again.");
            return null;
        }

        // Extraire les algorithmes depuis le nom du fichier
        String[] parts = fileName.split("-");
        if (parts.length < 2) {
            System.out.println("Invalid file name format. No algorithms found.");
            return null;
        }

        // Récupérer la liste des algorithmes en excluant la clé (première partie)
        String[] algorithms = new String[parts.length - 1];
        System.arraycopy(parts, 1, algorithms, 0, algorithms.length);
        System.out.println("algoritmhs: " + algorithms);

        // Décryptage du mot de passe
        String currentPassword = encryptedPassword;

        for (int i = algorithms.length - 1; i >= 0; i--) { // Parcours inverse des algorithmes
            String algo = algorithms[i];
            switch (algo) {
                case "ROTX":
                    System.out.println("Enter key for ROTX decryption:");
                    String rotKey = scanner.nextLine();
                    try {
                        currentPassword = RotX.decrypte(currentPassword, Integer.parseInt(rotKey));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid key. ROTX decryption skipped.");
                    }
                    break;

                case "POLYBIUS":
                    currentPassword = Polybius.decryptPolybius(currentPassword);
                    break;

                case "MD5":
                case "SHA256":
                    System.out.println(algo + " cannot be decrypted as it is a hash function.");
                    return null;

                default:
                    System.out.println("Unknown algorithm: " + algo);
                    return null;
            }
        }

        System.out.println("Decryption complete. Final password: " + currentPassword);
        return currentPassword;
    }

}