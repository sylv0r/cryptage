package Md5;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
    //this function returns a hash of a message
    public static String createHashWithMd5(String input) {
        System.out.println("Creating hash with MD5 " + input);
        try {
            //static getInstance() method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //calculating message digest of an input that return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            //converting byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            //converting message digest into hex value
            StringBuilder hashtext = new StringBuilder(no.toString(16));
            while (hashtext.length() < 32) {
                //if hashtext does not contain 32 characters, complete them with the missing 0s.
                hashtext.insert(0, "0");
            }
            //return hashtext in String format
            return hashtext.toString();
        }
        //for specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
