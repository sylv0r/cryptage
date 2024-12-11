package RotX;

public class RotX {

    /**
     * Encrypts a message using ROTX encryption with a given key.
     * Returns an encrypted string or an error message if the input is invalid.
     */
    public static String encrypte(String message, int key) {
        String Original_Message; // The original message passed by the user
        StringBuilder Encrypted_Message = new StringBuilder(); // StringBuilder to build the encrypted message
        char Message_Char; // The character being processed in the loop

        // Validate that the input contains only valid characters (letters, spaces, and newlines)
        if (!message.matches("[a-zA-Z \\n]+")) {
            return "Error: Message must contain only letters (A-Z, a-z), spaces, or newlines.";
        }

        // Convert the message to uppercase to simplify processing
        Original_Message = message.toUpperCase();

        // Encrypt the message character by character
        for (int i = 0; i < Original_Message.length(); ++i) {
            Message_Char = Original_Message.charAt(i); // Get each character from the message

            // Check if the character is an uppercase letter
            if (Message_Char >= 'A' && Message_Char <= 'Z') {
                // Apply the key (shift) to the character
                Message_Char = (char) (Message_Char + key);

                // Wrap around if the character goes beyond 'Z'
                if (Message_Char > 'Z') {
                    Message_Char = (char) (Message_Char - 'Z' + 'A' - 1);
                }

                // Append the encrypted character to the result
                Encrypted_Message.append(Message_Char);
            } else {
                // If it's a space or newline, append it unchanged
                Encrypted_Message.append(Message_Char);
            }
        }

        // Return the encrypted message as a string
        return Encrypted_Message.toString();
    }

    /**
     * Decrypts a message encrypted using ROTX with the provided key.
     * Returns a decrypted string or an error message if the input is invalid.
     */
    public static String decrypte(String message, int key) {
        String Original_Message; // The encrypted message passed by the user
        StringBuilder Decrypted_Message = new StringBuilder(); // StringBuilder to build the decrypted message
        char Message_Char; // The character being processed in the loop

        // Validate that the input contains only valid characters (letters, spaces, and newlines)
        if (!message.matches("[a-zA-Z \\n]+")) {
            return "Error: Message must contain only letters (A-Z, a-z), spaces, or newlines.";
        }

        // Convert the message to uppercase to simplify processing
        Original_Message = message.toUpperCase();

        // Decrypt the message character by character
        for (int i = 0; i < Original_Message.length(); ++i) {
            Message_Char = Original_Message.charAt(i); // Get each character from the message

            // Check if the character is an uppercase letter
            if (Message_Char >= 'A' && Message_Char <= 'Z') {
                // Reverse the key (shift) applied to the character
                Message_Char = (char) (Message_Char - key);

                // Wrap around if the character goes before 'A'
                if (Message_Char < 'A') {
                    Message_Char = (char) (Message_Char + 'Z' - 'A' + 1);
                }

                // Append the decrypted character to the result
                Decrypted_Message.append(Message_Char);
            } else {
                // If it's a space or newline, append it unchanged
                Decrypted_Message.append(Message_Char);
            }
        }

        // Return the decrypted message as a string
        return Decrypted_Message.toString();
    }
}
