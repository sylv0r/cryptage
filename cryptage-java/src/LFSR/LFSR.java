package LFSR;

import enums.LFSROutputType;

public class LFSR {

    // Converts a string into a bit array
    public static int[] stringToBits(String seed) {
        int[] bits = new int[seed.length() * 8];
        int index = 0;
        for (char c : seed.toCharArray()) {
            String binaryString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            for (char bit : binaryString.toCharArray()) {
                bits[index++] = bit - '0'; // Convert '0' or '1' to an integer
            }
        }
        return bits;
    }

    // Converts and shifts the bit array
    public static String lfsr(String seed, int iterations, LFSROutputType outputType) {
        // Convert the string into bits
        int[] bits = stringToBits(seed);

        // Perform iterations
        for (int i = 0; i < iterations; i++) {
            // Calculate the new bit (second and second-to-last)
            int newBit = (bits[1] + bits[bits.length - 2]) % 2;

            // Shift the bits to the left and add the new bit at the end
            for (int j = 0; j < bits.length - 1; j++) {
                bits[j] = bits[j + 1];
            }
            bits[bits.length - 1] = newBit;
        }

        // Convert the final bit array to a string
        String finalBin = arrayToString(bits);
        String truncatedFinalBin = finalBin.length() > 11 ? finalBin.substring(0, 11) : finalBin;

        // Format the output based on the requested output type
        return outputFormatType(truncatedFinalBin, outputType);
    }

    // Converts a bit array into a readable string
    public static String arrayToString(int[] bits) {
        StringBuilder sb = new StringBuilder();
        for (int bit : bits) {
            sb.append(bit);
        }
        return sb.toString();
    }

    // Formats the output based on the specified type
    public static String outputFormatType(String randomsBits, LFSROutputType outputType) {
        if (LFSROutputType.DECIMAL == outputType) {
            return String.valueOf(Integer.parseInt(randomsBits, 2));
        } else if (LFSROutputType.BINAIRE == outputType) {
            return randomsBits;
        } else {
            return "error";
        }
    }
}
