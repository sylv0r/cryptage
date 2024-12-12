package cli.EncryptMenuCLI;

import enums.AlgoAvailable;

/*
    * This class is the main controller for the DecryptMenuCLI.
    * It will call the appropriate algorithm to decrypt the password
 */
public class DecryptClass {
    public static String decrypt(String password, String key, AlgoAvailable algo) {
        switch (algo) {
            case ROTX:
                return RotX.RotX.decrypte(password, Integer.parseInt(key));
            case POLYBIUS:
                return Polybius.Polybius.decryptPolybius(password);
            case ENIGMA:
                return Enigma.Enigma.main(password);
            case VIGENERE:
                return Vigenere.Vigenere.decryption(password, key);
            default:
                return null;
        }
    }
}
