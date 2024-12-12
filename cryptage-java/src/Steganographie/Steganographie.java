package Steganographie;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Steganographie {

    /**
     * Encodes a text message into an image by modifying the least significant bits of the pixels.
     *
     * @param imagePath  Path to the input image (source).
     * @param outputPath Path to the output image (with the encoded message).
     * @param message    Message to encode into the image.
     * @throws IOException If an error occurs while reading or writing files.
     */
    public static void encodeMessage(String imagePath, String outputPath, String message) throws IOException {
        // Validate that the message contains only supported characters
        if (!message.matches("^[a-zA-Z0-9.,!?;:()\\[\\]{}'\" \\-_]*$")) {
            throw new IllegalArgumentException("Message contains unsupported characters. Only the following are allowed: " +
                    "letters (a-z, A-Z), digits (0-9), punctuation (.,!?;:()[]{}'\"), spaces, hyphens (-), and underscores (_).");
        }

        // Load the source image
        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth();
        int height = image.getHeight();

        // Convert the message into a UTF-8 byte array
        byte[] messageBytes = message.getBytes("UTF-8");
        int messageLength = messageBytes.length;

        // Check if the image has enough capacity to store the message and the end marker
        if ((messageLength + 1) * 8 > width * height * 3) { // +1 for the end marker
            throw new IllegalArgumentException("Message is too long to fit in the image.");
        }

        int messageIndex = 0; // Current position in the message byte array
        int bitIndex = 0;     // Current position in the byte being encoded
        boolean endMarkerEncoded = false; // Indicates if the end marker has been encoded

        // Traverse the pixels of the image
        outer:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Retrieve the color components of the pixel
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                // Encode message bits into the red component
                if (!endMarkerEncoded) {
                    if (messageIndex < messageBytes.length) {
                        // Insert message bits one by one into the LSB of the red component
                        red = setLSB(red, (messageBytes[messageIndex] >> (7 - bitIndex)) & 1);
                        bitIndex++;
                        if (bitIndex == 8) { // Move to the next byte once all bits are encoded
                            bitIndex = 0;
                            messageIndex++;
                        }
                    } else {
                        // Add the end marker (a byte of 0)
                        red = setLSB(red, 0);
                        bitIndex++;
                        if (bitIndex == 8) {
                            endMarkerEncoded = true;
                        }
                    }
                }

                // Reconstruct the modified pixel with the new color components
                image.setRGB(x, y, (red << 16) | (green << 8) | blue);

                // Stop the process once the end marker is encoded
                if (endMarkerEncoded) {
                    break outer;
                }
            }
        }

        // Save the modified image with the encoded message
        ImageIO.write(image, "png", new File(outputPath));
    }

    /**
     * Modifies the least significant bit (LSB) of a color value.
     *
     * @param color The color component to modify.
     * @param bit   The bit (0 or 1) to insert into the LSB.
     * @return The modified color component.
     */
    private static int setLSB(int color, int bit) {
        return (color & 0xFE) | bit; // Replace the last bit with the given value
    }

    /**
     * Decodes a text message from an image that contains data encoded in the least significant bits of the pixels.
     *
     * @param imagePath Path to the image containing the encoded message.
     * @return The decoded message as a string.
     * @throws IOException If an error occurs while reading the file.
     */
    public static String decodeMessage(String imagePath) throws IOException {
        // Load the image containing the message
        BufferedImage image = ImageIO.read(new File(imagePath));
        int width = image.getWidth();
        int height = image.getHeight();

        StringBuilder message = new StringBuilder(); // Holds the reconstructed message
        int byteBuffer = 0; // Buffer to accumulate bits of a byte
        int bitIndex = 0;   // Current position in the byte being reconstructed

        // Traverse the pixels of the image
        outer:
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Retrieve the red component of the pixel
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xFF;

                // Extract the LSB of the red component and add it to the buffer
                byteBuffer = (byteBuffer << 1) | (red & 1);
                bitIndex++;

                if (bitIndex == 8) { // Once a full byte is reconstructed
                    if (byteBuffer == 0) { // Check if it's the end marker
                        break outer; // Stop reading the message
                    }
                    message.append((char) byteBuffer); // Add the reconstructed byte to the message
                    byteBuffer = 0; // Reset the buffer for the next byte
                    bitIndex = 0;   // Reset the bit index
                }
            }
        }

        return message.toString(); // Return the decoded message
    }
}
