import java.util.Arrays;

public class RC4 {
    private final int[] S = new int[256]; // Permutation array
    private final int[] T = new int[256]; // Key-dependent array

    public RC4(byte[] key) {
        // Initialize S and T arrays
        for (int i = 0; i < 256; i++) {
            S[i] = i; // S is filled with values from 0 to 255
            T[i] = key[i % key.length]; // T extend key to match size of S
        }

        // Shuffle S based on T
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + T[i]) % 256; // Calculate new position
            swap(S, i, j); // Swap S[i] & S[j] to shuffle S array
        }
    }

    private void swap(int[] array, int i, int j) {
        // Swap two values in the array
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Encrypt/Decrypt the message
    public byte[] processRC4(byte[] data) {
        int i = 0, j = 0; // Initialize counters
        byte[] result = new byte[data.length]; // Result size matches input

        for (int k = 0; k < data.length; k++) {
            i = (i + 1) % 256; // Increment i
            j = (j + S[i]) % 256; // Update position for swap
            swap(S, i, j); // Swap S[i] and S[j]

            int t = (S[i] + S[j]) % 256; // Select index of flux key
            result[k] = (byte) (data[k] ^ S[t]); // XOR with the key stream
        }
        return result;
    }

    public static void main(String[] args) {
        // Key for RC4
        byte[] key = "SecretKey".getBytes();

        // Create an RC4 instance
        RC4 rc4 = new RC4(key);

        // Encrypt the message
        byte[] message = "MyPassword".getBytes(); // Message to encrypt
        byte[] encrypted = rc4.processRC4(message);
        System.out.println("Encrypted (bytes): " + Arrays.toString(encrypted));

        // Decrypt the message
        RC4 rc4Decrypt = new RC4(key); // Reinitialize RC4 for decryption
        byte[] decrypted = rc4Decrypt.processRC4(encrypted);
        String readableMessage = new String(decrypted); // Convert to readable text

        // Output the decrypted result
        System.out.println("decrypted : " + decrypted);
        System.out.println("decrypted.toString : " + Arrays.toString(decrypted));
        System.out.println("Decrypted (readable): " + readableMessage);
    }
}