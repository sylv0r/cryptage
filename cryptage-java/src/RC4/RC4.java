package RC4;

import java.util.Base64;

public class RC4 {
    private final int[] S = new int[256]; // Array contain int from 0 to 255
    private final int[] T = new int[256]; // Array contain key's byte to match size of S

    // Initialize RC4
    public RC4(byte[] key) {
        // Initialize S and T arrays
        for (int i = 0; i < 256; i++) {
            S[i] = i; // S is filled with values from 0 to 255
            T[i] = key[i % key.length]; // T extends the key to match the size of S
        }

        // Shuffle S based on the values in T
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) % 256; // Calculate the new position using modular arithmetic
            swap(S, i, j); // Swap S[i] and S[j] to randomize S
        }
    }

    // Swap 2 value of an array
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Encrypt or decrypt a message using the RC4 algorithm
    public byte[] processRC4(byte[] input) {
        int i = 0;
        int j = 0;
        byte[] result = new byte[input.length]; // Create a result array of the same size as the input

        for (int k = 0; k < input.length; k++) {
            i = (i + 1) % 256; // Increment i
            j = (j + S[i]) % 256; // Update j based on S[i]
            swap(S, i, j); // Swap S[i] and S[j]

            // Generate the keystream byte
            int t = (S[i] + S[j]) % 256; // Calculate the index for the keystream byte
            result[k] = (byte) (input[k] ^ S[t]); // XOR the input byte with the keystream byte
        }
        return result;
    }

    // Encrypt a plaintext message using RC4 and return a Base64-encoded string
    public static String encryptRC4(String plaintext, String key) {
        byte[] keyBytes = key.getBytes(); // Convert the key to bytes
        byte[] plaintextBytes = plaintext.getBytes(); // Convert the plaintext to bytes

        RC4 rc4 = new RC4(keyBytes); // Initialize RC4 with the key
        byte[] encrypted = rc4.processRC4(plaintextBytes); // Encrypt the plaintext

        // Encode the encrypted bytes into a Base64 string
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt a Base64-encoded encrypted message using RC4
    public static String decryptRC4(String encryptedBase64, String key) {
        byte[] keyBytes = key.getBytes(); // Convert the key to bytes
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedBase64); // Decode the Base64 string to bytes

        RC4 rc4 = new RC4(keyBytes); // Initialize RC4 with the key
        byte[] decrypted = rc4.processRC4(encryptedBytes); // Decrypt the message

        // Convert the decrypted bytes back to a string
        return new String(decrypted);
    }

    // Used for testing
//    public static void main(String[] args) {
//        String key = "SecretKey";
//        String password = "MyPasswordRC4";
//
//        // Encrypt
//        String encrypted = RC4.encryptRC4(password, key);
//        System.out.println("Encrypted (Base64): " + encrypted);
//
//        // Decrypt
//        String decrypted = RC4.decryptRC4(encrypted, key);
//        System.out.println("Decrypted: " + decrypted);
//    }
}