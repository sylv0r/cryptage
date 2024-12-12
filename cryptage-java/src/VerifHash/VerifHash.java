package VerifHash;

import Md5.Md5;
import Sha256.Sha256;
import enums.AlgoAvailable;
import enums.HashMethod;

import java.util.Objects;

public class VerifHash {
    /**
     * Verifies if the hash of the input matches the given trueHash using the specified method.
     *
     * @param input     The input string to hash.
     * @param trueHash  The expected hash value to compare against.
     * @param method    The hashing method to use (e.g., MD5, SHA-256).
     * @param salt      The salt to apply (if any).
     * @return          A message indicating whether the hash matches or an error occurred.
     */
    public static void isTrueHash(String input, String trueHash, HashMethod method, String salt) {
        // Validate inputs
        if (Objects.isNull(input) || Objects.isNull(trueHash) || Objects.isNull(method)) {
            System.out.println("Error: Input, hash, or method cannot be null");
            return;
        }

        // Compute the hash based on the specified method
        String computedHash;

        // Apply salt if provided
        String testingHash = Objects.isNull(salt) ? input : salt + input;

        switch (method) {
            case MD5:
                computedHash = Md5.createHashWithMd5(testingHash);
                break;
            case SHA256:
                computedHash = Sha256.createHashWithSha256(testingHash);
                break;
            default:
                System.out.println("Error: Unsupported hashing method");
                return;
        }

        // Compare the computed hash with the provided trueHash
        System.out.println("True hash: " + trueHash);  // Debugging
        System.out.println("Computed hash: " + computedHash);  // Debugging

        if (computedHash.equalsIgnoreCase(trueHash.trim())) {  // Ignore case and whitespace
            System.out.println("It's a true message: the hash matches.");
        } else {
            System.out.println("It's a false message: the hash does not match.");
        }
    }
}
