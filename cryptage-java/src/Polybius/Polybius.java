package Polybius;

public class Polybius {

    /**
     * Generates a 5x5 grid of characters from a custom alphabet.
     * @param customAlphabet The custom alphabet to generate the grid from.
     * @return A char[][] array representing the 5x5 grid.
     */
    private static char[][] generateGrid(String customAlphabet) {
        char[][] alphabetGrid = new char[5][5]; // Initialize 5x5 grid
        int index = 0;

        // Fill the grid row by row
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                alphabetGrid[i][j] = customAlphabet.charAt(index++);
            }
        }

        return alphabetGrid;
    }

    /**
     * Finds the position of a character in a 5x5 grid.
     * @param grid The 5x5 grid to search in.
     * @param c The character to find.
     * @return A string representing the row and column index of the character.
     */
    private static String findPosition(char[][] grid, char c) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == c) {
                    return i + "" + j;
                }
            }
        }
        return ""; // Return empty string if character not found
    }

    /**
     * Encrypts a string using the Polybius cipher.
     * @param str The string to encrypt.
     * @return The encrypted string.
     */
    public static String encryptPolybius(String str) {
        str = str.toUpperCase(); // Convert input to uppercase
        StringBuilder result = new StringBuilder();
        String customAlphabet = "FXTMKPQRCLNDOYEJZUHAGISVB";
        char[][] alphabetGrid = generateGrid(customAlphabet);

        String positionOfV = findPosition(alphabetGrid, 'V'); // Find position of 'V' for handling 'W'

        // Iterate through input characters
        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);

            if (c == 'W') { // Map 'W' to the position of 'V'
                result.append(positionOfV);
                result.append(positionOfV);
            } else if (Character.isLetter(c)) { // Append grid position for valid letters
                result.append(findPosition(alphabetGrid, c));
            }
        }
        return result.toString();
    }

    /**
     * Decrypts a string using the Polybius cipher.
     * @param encrypted The encrypted string to decrypt.
     * @return The decrypted string.
     */
    public static String decryptPolybius(String encrypted) {
        StringBuilder result = new StringBuilder();
        String customAlphabet = "FXTMKPQRCLNDOYEJZUHAGISVB";
        char[][] alphabetGrid = generateGrid(customAlphabet);

        // Iterate through pairs of numbers
        for (int index = 0; index < encrypted.length(); index += 2) {
            int row = Character.getNumericValue(encrypted.charAt(index)); // Get row index
            int col = Character.getNumericValue(encrypted.charAt(index + 1)); // Get column index
            result.append(alphabetGrid[row][col]); // Append corresponding character
        }

        return result.toString();
    }

// Used for Testing
//    public static void main(String input) {
//        char[][] gridAlphabet = generateGrid(customAlphabet);
//
//        String encrypted = encryptPolybius(input);
//        System.out.println("Encrypted: " + encrypted);
//
//        String decrypted = decryptPolybius(encrypted);
//        System.out.println("Decrypted: " + decrypted);
//    }
}