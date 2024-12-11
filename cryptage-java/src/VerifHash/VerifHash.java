package VerifHash;

import Md5.Md5;
import Sha256.Sha256;
import enums.HashMethod;

import java.util.Objects;

public class VerifHash {
    /**
     * Verifies if the hash of the input matches the given trueHash using the specified method.
     *
     * @param input     The input string to hash.
     * @param trueHash  The expected hash value to compare against.
     * @param method    The hashing method to use (e.g., MD5, SHA-256).
     * @return          A message indicating whether the hash matches or an error occurred.
     */
    public static String isTrueHash(String input, String trueHash, HashMethod method) {
        // Validate inputs
        if (Objects.isNull(input)|| Objects.isNull(trueHash) || Objects.isNull(method)) {
            return "Error: Input, hash, or method cannot be null";
        }

        // Compute the hash based on the specified method
        String computedHash = new String();

        switch (method) {
            case Md5:
                computedHash = Md5.createHashWithMd5(input);
                break;
            case SHA256:
                computedHash = Sha256.createHashWithSha256(input);
                break;
           default:
                return "Error: Unsupported hashing method";
        }

        // Compare the computed hash with the provided trueHash
        if (computedHash.equals(trueHash)) {
            return "It's a true message: the hash matches.";
        } else {
            return "It's a false message: the hash does not match.";
        }
    }
}
