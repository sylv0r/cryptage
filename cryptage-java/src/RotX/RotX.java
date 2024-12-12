package RotX;

/**
 * Provides methods to encrypt and decrypt messages using ROTX cipher with a given key.
 * Validates input to handle errors gracefully and prevents crashes.
 */
public class RotX {

    /**
     * Encrypts a message using ROTX encryption with a given key.
     * Validates the input to prevent crashes and returns error messages for invalid input.
     *
     * @param message The message to encrypt. Only letters, spaces, and newlines are allowed.
     * @param key     The encryption key. Must be an integer.
     * @return The encrypted message, or an error message if input is invalid.
     */
    public static String encrypte(String message, int key) {
        // Validate the message
        if (message == null || message.isEmpty()) {
            return "Error: The message cannot be null or empty.";
        }

        // Reduce the key to be between 0 and 25
        key = key % 26;
        if (key < 0) {
            key += 26; // Handle negative keys
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); ++i) {
            char messageChar = message.charAt(i);

            if (messageChar >= 'A' && messageChar <= 'Z') {
                messageChar = (char) (messageChar + key);
                if (messageChar > 'Z') {
                    messageChar = (char) (messageChar - 26); // Stay within uppercase letters
                }
                encryptedMessage.append(messageChar);
            } else if (messageChar >= 'a' && messageChar <= 'z') {
                messageChar = (char) (messageChar + key);
                if (messageChar > 'z') {
                    messageChar = (char) (messageChar - 26); // Stay within lowercase letters
                }
                encryptedMessage.append(messageChar);
            } else if (messageChar == ' ' || messageChar == '\n') {
                // Preserve spaces and newlines
                encryptedMessage.append(messageChar);
            } else {
                return "Error: Invalid character detected in the message. Only letters, spaces, and newlines are allowed.";
            }
        }
        return encryptedMessage.toString();
    }

    /**
     * Decrypts a message encrypted using ROTX with the provided key.
     * Validates the input to prevent crashes and returns error messages for invalid input.
     *
     * @param message The message to decrypt. Only letters, spaces, and newlines are allowed.
     * @param key     The decryption key. Must be an integer.
     * @return The decrypted message, or an error message if input is invalid.
     */
    public static String decrypte(String message, int key) {
        // Validate the message
        if (message == null || message.isEmpty()) {
            return "Error: The message cannot be null or empty.";
        }

        // Reduce the key to be between 0 and 25
        key = key % 26;
        if (key < 0) {
            key += 26; // Handle negative keys
        }

        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); ++i) {
            char messageChar = message.charAt(i);

            if (messageChar >= 'A' && messageChar <= 'Z') {
                messageChar = (char) (messageChar - key);
                if (messageChar < 'A') {
                    messageChar = (char) (messageChar + 26); // Stay within uppercase letters
                }
                decryptedMessage.append(messageChar);
            } else if (messageChar >= 'a' && messageChar <= 'z') {
                messageChar = (char) (messageChar - key);
                if (messageChar < 'a') {
                    messageChar = (char) (messageChar + 26); // Stay within lowercase letters
                }
                decryptedMessage.append(messageChar);
            } else if (messageChar == ' ' || messageChar == '\n') {
                // Preserve spaces and newlines
                decryptedMessage.append(messageChar);
            } else {
                return "Error: Invalid character detected in the message. Only letters, spaces, and newlines are allowed.";
            }
        }
        return decryptedMessage.toString();
    }


}
