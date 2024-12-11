package AES;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import LFSR.*;
import enums.LFSROutputType;

public class AES {

    // Generate an AES key using LFSR
    public static SecretKey generateAESKeyWithLFSR(String seed, int iterations, LFSROutputType outputType) throws Exception {
        // Generate a binary string using LFSR
        String lfsrOutput = LFSR.lfsr(seed, iterations, outputType);

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

    // Encrypt a message using AES
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt a message using AES
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    /*
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
