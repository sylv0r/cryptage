package Vigenere;

public class Vigenere {

    /**
     * Encrypts a given message using the Vigenère cipher with the provided key.
     * @param message The input string to encrypt.
     * @param key The key used for encryption.
     * @return The encrypted message.
     */
    public static String encryption(String message, String key) {
        StringBuilder encryptedMessage = new StringBuilder();
        int keyIndex = 0;
        int keyLength = key.length();

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char keyChar = key.charAt(keyIndex % keyLength);
                int shift = (Character.toLowerCase(keyChar) - 'a');

                // Encrypt the character
                char encryptedChar = (char) ((currentChar - base + shift) % 26 + base);
                encryptedMessage.append(encryptedChar);

                keyIndex++; // Move to the next character in the key
            } else {
                // Non-letter characters remain unchanged
                encryptedMessage.append(currentChar);
            }
        }

        return encryptedMessage.toString();
    }

    /**
     * Decrypts a given message using the Vigenère cipher with the provided key.
     * @param message The input string to decrypt.
     * @param key The key used for decryption.
     * @return The decrypted message.
     */
    public static String decryption(String message, String key) {
        StringBuilder decryptedMessage = new StringBuilder();
        int keyIndex = 0;
        int keyLength = key.length();

        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

            // Check if the character is a letter
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char keyChar = key.charAt(keyIndex % keyLength);
                int shift = (Character.toLowerCase(keyChar) - 'a');

                // Decrypt the character
                char decryptedChar = (char) ((currentChar - base - shift + 26) % 26 + base);
                decryptedMessage.append(decryptedChar);

                keyIndex++; // Move to the next character in the key
            } else {
                // Non-letter characters remain unchanged
                decryptedMessage.append(currentChar);
            }
        }

        return decryptedMessage.toString();
    }
}