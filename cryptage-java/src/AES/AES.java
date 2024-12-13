package AES;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import LFSR.*;
import enums.LFSROutputType;

/**
 * This class provides AES encryption and decryption functionalities using a key generated
 * dynamically by an LFSR (Linear Feedback Shift Register).
 *
 * The key generation is based on a seed string and the number of iterations, which influences
 * the randomness of the generated AES key. It can be used to securely encrypt and decrypt messages.
 */
public class AES {

    /**
     * Generates an AES key using LFSR (Linear Feedback Shift Register).
     *
     * @param seed The initial value used by the LFSR (seed string).
     * @return A SecretKey object for AES encryption.
     * @throws Exception If the LFSR output is not of the correct size or there are other issues.
     */
    public static SecretKey generateAESKeyWithLFSR(String seed) throws Exception {
        // Generate a binary string using LFSR
        String lfsrOutput = LFSR.lfsr(seed, 3, LFSROutputType.BINAIRE);

        // Ensure the output is 256 bits (32 bytes) for AES
        if (lfsrOutput.length() < 256) {
            lfsrOutput = String.format("%256s", lfsrOutput).replace(' ', '0');
        } else if (lfsrOutput.length() > 256) {
            lfsrOutput = lfsrOutput.substring(0, 256);
        }

        // Convert the binary string to bytes
        byte[] keyBytes = new byte[32]; // AES 256-bit key size
        for (int i = 0; i < 32; i++) {
            keyBytes[i] = (byte) Integer.parseInt(lfsrOutput.substring(i * 8, (i + 1) * 8), 2);
        }

        // Return the SecretKey
        return new SecretKeySpec(keyBytes, "AES");
    }

    /**
     * Encrypts a plaintext message using the AES algorithm.
     *
     * @param plainText The message to be encrypted.
     * @return The encrypted message as a Base64 encoded string.
     * @throws Exception If there is an error during encryption.
     */
    public static String encrypt(String plainText, String key) throws Exception {
        // Generate a random AES key
        SecretKey secretKey = generateAESKeyWithLFSR(key);
        // Initialize the cipher in encryption mode
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the message and return the result as Base64 encoded string
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts an encrypted message using the AES algorithm.
     *
     * @param encryptedText The encrypted message (Base64 encoded).
     * @return The decrypted message as a string.
     * @throws Exception If there is an error during decryption.
     */
    public static String decrypt(String encryptedText, String key) throws Exception {

        SecretKey secretKey = generateAESKeyWithLFSR(key);
        // Initialize the cipher in decryption mode
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode the Base64 encoded message and decrypt it
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }


    /**
     * Example usage of AES with LFSR:
     *
     * // Step 1: Define parameters for LFSR
     * String seed = "secureSeed"; // LFSR seed string
     * int iterations = 100; // Number of LFSR iterations
     * LFSROutputType outputType = LFSROutputType.BINAIRE; // Output type of LFSR
     *
     * // Step 2: Generate AES key using LFSR
     * SecretKey secretKey = AES.generateAESKeyWithLFSR(seed, iterations, outputType);
     *
     * // Step 3: Encrypt a message
     * String message = "Hello, world!";
     * String encryptedMessage = AES.encrypt(message, secretKey);
     * System.out.println("Encrypted Message: " + encryptedMessage);
     *
     * // Step 4: Decrypt the message
     * String decryptedMessage = AES.decrypt(encryptedMessage, secretKey);
     * System.out.println("Decrypted Message: " + decryptedMessage);
     *
     * // Output:
     * // Encrypted Message: [Some Base64 encoded string]
     * // Decrypted Message: Hello, world!
     */
}
