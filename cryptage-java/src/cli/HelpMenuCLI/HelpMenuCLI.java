package cli.HelpMenuCLI;

import cli.CLIController;

import java.util.Scanner;

public class HelpMenuCLI {

    public static void main() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("=======================================");
        System.out.println("              HELP MENU                ");
        System.out.println("=======================================");
        System.out.println("");
        System.out.println("Welcome to the Help Menu!");
        System.out.println("Here are the features and algorithms available in this application:");
        System.out.println("");

        System.out.println("Features:");
        System.out.println("[1] Encrypt a Password: Securely encrypt your passwords using various algorithms.");
        System.out.println("[2] Decrypt a Password: Decrypt your passwords using the corresponding algorithms.");
        System.out.println("[3] Save a Password: Save your encrypted passwords securely.");
        System.out.println("[4] Retrieve a Password: Fetch previously saved passwords.");
        System.out.println("[5] Update or Delete a Password: Future implementations to manage your passwords.");
        System.out.println("");

        System.out.println("Available Algorithms:");
        System.out.println("[1] Rot(X): Rotational cipher where 'X' defines the rotation amount.");
        System.out.println("[2] MD5: Hashing algorithm for creating a 128-bit hash value.");
        System.out.println("[3] Polybius: Encryption based on a 5x5 grid of characters.");
        System.out.println("[4] SHA256: Secure Hash Algorithm producing a 256-bit hash value.");
        System.out.println("[5] AES: Advanced Encryption Standard for secure symmetric encryption.");
        System.out.println("[6] LFSR: Linear Feedback Shift Register, a pseudo-random number generator used for encryption.");
        System.out.println("[7] RC4: Rivest Cipher 4, a symmetric stream cipher.");
        System.out.println("[8] Vigenere: Encryption using a keyword and multiple Caesar ciphers.");
        System.out.println("");

        System.out.println("Usage Tips:");
        System.out.println("- Remember the algorithm used to encrypt your password.");
        System.out.println("- Ensure the image path and output path are correct when using steganography.");
        System.out.println("- For further details on any feature, consult the user manual or contact support.");
        System.out.println("");

        System.out.println("=======================================");
        System.out.println("Press any key to return to the main menu...");
        System.out.println("=======================================");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        if (scanner != null) {
            CLIController.main(null);
        }
    }
}
