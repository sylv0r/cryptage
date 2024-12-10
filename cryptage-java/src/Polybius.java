public class Polybius {

    // Fills a 5x5 character grid with the given alphabet string in order
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

    // Finds the position of a character in the 5x5 grid
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

    // Encrypts a string using the Polybius cipher
    public static String encryptPolybius(char[][] alphabetGrid, String str) {
        str = str.toUpperCase(); // Convert input to uppercase
        StringBuilder result = new StringBuilder();

        String positionOfV = findPosition(alphabetGrid, 'V'); // Find position of 'V' for handling 'W'

        // Iterate through input characters
        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);

            if (c == 'W') { // Map 'W' to the position of 'V'
                result.append(positionOfV);
            } else if (Character.isLetter(c)) { // Append grid position for valid letters
                result.append(findPosition(alphabetGrid, c));
            }
        }
        return result.toString();
    }

    // Decrypts a string encrypted with the Polybius cipher
    public static String decryptPolybius(char[][] alphabetGrid, String encrypted) {
        StringBuilder result = new StringBuilder();

        // Iterate through pairs of numbers
        for (int index = 0; index < encrypted.length(); index += 2) {
            int row = Character.getNumericValue(encrypted.charAt(index)); // Get row index
            int col = Character.getNumericValue(encrypted.charAt(index + 1)); // Get column index
            result.append(alphabetGrid[row][col]); // Append corresponding character
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String input = "jejsuisuneleveurd'animauxd'ameriquedusudwwww";
        String customAlphabet = "FXTMKPQRCLNDOYEJZUHAGISVB";

        char[][] gridAlphabet = generateGrid(customAlphabet);

        String encrypted = encryptPolybius(gridAlphabet, input);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decryptPolybius(gridAlphabet, encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}