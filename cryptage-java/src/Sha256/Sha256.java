package Sha256;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
    /**
     * Generates a SHA-256 hash from the input string.
     *
     * @param input The string to be hashed.
     * @return The SHA-256 hash as a hexadecimal string.
     */
    public static String createHashWithSha256(String input) {
        try {
            // Initialize a MessageDigest instance with the SHA-256 algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Compute the hash of the input string and get the byte array
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array into a BigInteger to simplify the hexadecimal conversion
            BigInteger bigIntegerHash = new BigInteger(1, messageDigest);

            // Convert the BigInteger into a hexadecimal string
            String hexString = bigIntegerHash.toString(16);

            // Ensure the hexadecimal string is 64 characters long by padding with leading zeros
            return String.format("%064x", bigIntegerHash);
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where the SHA-256 algorithm is not available
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
}
