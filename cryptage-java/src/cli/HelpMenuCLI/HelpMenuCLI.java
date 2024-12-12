package cli.HelpMenuCLI;

public class HelpMenuCLI {

    public static void main() {
        System.out.println("Welcome to the Help Menu!");
        System.out.println("Here are the features and algorithms available in this application:");
        System.out.println();

        System.out.println("Features:");
        System.out.println("1. Encrypt a Password: Securely encrypt your passwords using various algorithms.");
        System.out.println("2. Decrypt a Password: Decrypt your passwords using the corresponding algorithms.");
        System.out.println("3. Save a Password: Save your encrypted passwords securely.");
        System.out.println("4. Retrieve a Password: Fetch previously saved passwords.");
        System.out.println("5. Update or Delete a Password: Future implementations to manage your passwords.");
        System.out.println();

        System.out.println("Available Algorithms:");
        System.out.println("1. Rot(X): Rotational cipher where 'X' defines the rotation amount.");
        System.out.println("2. MD5: Hashing algorithm for creating a 128-bit hash value.");
        System.out.println("3. Polybius: Encryption based on a 5x5 grid of characters.");
        System.out.println("4. SHA256: Secure Hash Algorithm producing a 256-bit hash value.");
        System.out.println("5. AES: Advanced Encryption Standard for secure symmetric encryption.");
        System.out.println("6. LFSR: Linear Feedback Shift Register, a pseudo-random number generator used for encryption.");
        System.out.println();

        System.out.println("Usage Tips:");
        System.out.println("- Make sure to remember the algorithm used to encrypt your password.");
        System.out.println("- When using steganography, ensure the image path and output path are correct.");
        System.out.println("- For further details on any feature, consult the user manual or contact support.");
        System.out.println();

        System.out.println("To return to the main menu, press any key...");
        try {
            System.in.read(); // Wait for user input to return
        } catch (Exception e) {
            System.err.println("Error reading input.");
        }
    }
}
