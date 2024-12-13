package cli.SteganoMenuCLI;

import Steganographie.Steganographie;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class SteganoMenuCLI {

    private static String imagePath;  // Path to the image
    private static String outputPath; // Path to save the output file
    private static String message;    // Message to hide or retrieve

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.println("            Stegano Menu              ");
        System.out.println("=======================================");
        System.out.println("");

        System.out.println("Select an option:");
        System.out.println("[1] Hide text");
        System.out.println("[2] Unhide text");
        System.out.println("[3] Return to main menu");
        System.out.println("[4] Exit");
        System.out.println("=======================================");

        String choosed = scanner.nextLine();  // User choice

        // Handle different user choices
        switch (choosed) {
            case "1" :
                // Option to hide a message in an image
                System.out.println("Enter the message you want to hide :");
                message = scanner.nextLine();  // Get the message from the user
                message = message.isEmpty() ? "test" : message;  // Set a default message if empty

                System.out.println("Enter your image path (default : ./img/Mona_Lisa.jpg");
                String nextLine = scanner.nextLine();
                imagePath = nextLine.isEmpty() ? "./img/Mona_Lisa.jpg" : nextLine;  // Default image path if empty

                // Automatically set output path to Desktop
                String desktopPath = System.getProperty("user.home") + "/Desktop";
                System.out.println("Enter your output filename (default: /Desktop/Mona_Lisa.png):");
                nextLine = scanner.nextLine();
                outputPath = nextLine.isEmpty() ? Paths.get(desktopPath, "Mona_Lisa.png").toString() : Paths.get(desktopPath, nextLine).toString();  // Set output file path

                // Call the encode function to hide the message in the image
                Steganographie.encodeMessage(imagePath, outputPath, message);
                SteganoMenuCLI.main();  // Return to the main menu
                break;

            case "2":
                // Option to reveal the hidden message from an image
                // Set input path to Desktop
                String desktopPathDecode = System.getProperty("user.home") + "/Desktop";
                System.out.println("Warning if a picture as the same name it will be erased !!!");
                System.out.println("Enter your image filename (default: Mona_Lisa.png):");
                String nextLineDecode = scanner.nextLine();
                imagePath = nextLineDecode.isEmpty() ? Paths.get(desktopPathDecode, "Mona_Lisa.png").toString() : Paths.get(desktopPathDecode, nextLineDecode).toString();  // Default image path if empty
                System.out.println("File search at : " + imagePath);

                // Check if the file exists
                if (!Files.exists(Paths.get(imagePath))) {
                    System.err.println("Error: The specified file does not exist: " + imagePath);
                    break;
                }

                try {
                    // Try to decode the hidden message from the image
                    String hiddenMessage = Steganographie.decodeMessage(imagePath);
                    System.out.println("The hidden message: " + hiddenMessage);  // Display the hidden message
                    SteganoMenuCLI.main();  // Return to the main menu
                } catch (IOException e) {
                    // Handle any error that occurs during decoding
                    System.err.println("An error occurred while decoding the message.");
                    e.printStackTrace();
                }
                break;

            case "3":
                // Option to simply return to the main menu
                break;

            case "4":
                // Option to exit the program
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);  // Exit the program
                break;

            default:
                // Handle invalid choice
                System.out.println("Invalid choice. Please try again.");
                SteganoMenuCLI.main();  // Return to the main menu
                break;
        }
    }
}