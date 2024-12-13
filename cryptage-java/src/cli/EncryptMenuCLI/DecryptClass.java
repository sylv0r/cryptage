package cli.EncryptMenuCLI;

import enums.AlgoAvailable;
import javax.crypto.SecretKey;

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
                return Enigma.Enigma.main(password, key);
            case VIGENERE:
                return Vigenere.Vigenere.decryption(password, key);
            case RC4:
                return RC4.RC4.decryptRC4(password, key);
            case AES:
                try {
                    return AES.AES.decrypt(password, key);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            default:
                return null;
        }
    }
}
