package cli.StorageActionCLI;

import FileManager.FileManager;
import RotX.RotX;
import Md5.Md5;
import Polybius.Polybius;
import enums.AlgoAvailable;

import java.util.List;
import java.util.Scanner;

/**
 * GetPasswordMenuCLI is the CLI that handles the getting of a password.
 */
public class GetPasswordMenuCLI {
    public static String main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the password you want to get:");

        // List all the files
        List<String> files = FileManager.listFiles(); // Obtenir la liste des fichiers enregistrés
        for (int i = 0; i < files.size(); i++) {
            System.out.println((i + 1) + ". " + files.get(i)); // Afficher les fichiers disponibles
        }

        // Lire le choix de l'utilisateur
        String choice = scanner.nextLine();

        // Obtenir le fichier sélectionné
        String fileName = files.get(Integer.parseInt(choice) - 1);
        String encryptedPassword = FileManager.getFileContent(fileName); // Lire le contenu du fichier

        // Extraire les algorithmes du nom du fichier
        String[] parts = fileName.split("-");
        if (parts.length < 2) {
            System.out.println("Invalid file format. No algorithms found.");
            return null;
        }

        // Récupérer la liste des algorithmes en excluant la clé (première partie)
        String[] algorithms = new String[parts.length - 1];
        System.arraycopy(parts, 1, algorithms, 0, algorithms.length);

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
                    System.out.println("MD5 cannot be decrypted as it is a hash function.");
                    return null;

                case "SHA256":
                    System.out.println("SHA256 cannot be decrypted as it is a hash function.");
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