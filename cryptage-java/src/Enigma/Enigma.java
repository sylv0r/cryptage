package Enigma;

// *
//  * This class is the main controller for the Enigma algorithm.
//  * It will call the appropriate algorithm to encrypt the password
//
public class Enigma {
    private String[] rotors;
    private String reflector;
    private int[] positions;
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Constructor
    public Enigma(String[] rotors, String reflector, char[] initialPositions) {
        this.rotors = rotors;
        this.reflector = reflector;
        this.positions = new int[initialPositions.length];
        for (int i = 0; i < initialPositions.length; i++) {
            this.positions[i] = initialPositions[i] - 'A';
        }

    }

    // Method to encrypt or decrypt a single character
    private char encryptDecryptChar(char charInput) {
        if (!Character.isLetter(charInput)) {
            return charInput;
        }

        // index of the input character in the alphabet
        int index = alphabet.indexOf(Character.toUpperCase(charInput));

        // Forward pass through the 3 rotors
        for (int i = 0; i < rotors.length; i++) {
            index = (index + positions[i] + 26) % 26;
            // index is now the index of the rotor of the character in the alphabet
            index = alphabet.indexOf(rotors[i].charAt(index));
        }

        // Pass through reflector
        index = alphabet.indexOf(reflector.charAt(index));

        // Reverse pass through the 3 rotors
        for (int i = rotors.length - 1; i >= 0; i--) {
            // index is now the index of the character in the rotor
            index = (rotors[i].indexOf(alphabet.charAt(index)) - positions[i] + 26) % 26;
        }

        // Return the character at the index in the alphabet
        return alphabet.charAt(index);
    }

    public String encryptDecrypt(String text) {
        StringBuilder result = new StringBuilder();
        //
        for (char c : text.toCharArray()) {
            result.append(encryptDecryptChar(c));
        }
        return result.toString();
    }

    public static String main(String input, String positions) {

        if (positions.length() != 3) {
            return "Error: positions must be 3 characters long";
        }
        if (!positions.chars().allMatch(Character::isLetter)) {
            return "Error: positions must be letters";
        }
        positions = positions.toUpperCase();

        // init rotors and reflector
        String[] rotors = {
                "EKMFLGDQVZNTOWYHXUSPAIBRCJ",
                "AJDKSIRUXBLHWTMCQGZNPYFVOE",
                "BDFHJLCPRTXVZNYEIWGAKMUSQO"
        };
        String reflector = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        // init rotor positions
        char[] initialPositions = positions.toCharArray();
        // create enigma object
        Enigma enigma = new Enigma(rotors, reflector, initialPositions);
        return enigma.encryptDecrypt(input);
    }


}