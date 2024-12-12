package FileManager;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileManager {

    // base path for file storage
    private static final String filePath = "keyvault/";

    // Method to create a file
    public static void createFile(String file, String content) {
        file = filePath + file;
        try (FileWriter writerFile = new FileWriter(file);
             BufferedWriter writer = new BufferedWriter(writerFile)) {
            writer.write(content);
            writer.flush(); // Ensures the data is written immediately
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    // Method to delete a file
    public static void deleteFile(String file) {
        file = filePath + file;
        try {
            Files.deleteIfExists(Paths.get(file));
            System.out.println("File deleted successfully at: " + file);
        } catch (IOException e) {
            System.err.println("Error deleting file: " + e.getMessage());
        }
    }

    // Method to get a file and its content
    public static String getFileContent(String file) {
        file = filePath + file;
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + file);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return content.toString();
    }

    // Method to list all files in a directory
    public static List<String> listFiles() {
        List<String> files = new ArrayList<>();
        try (Stream<Path> paths = Files.list(Paths.get(filePath))) {
            paths.filter(Files::isRegularFile) // Only include regular files
                    .forEach(path -> {
                        assert files != null;
                        files.add(path.getFileName().toString());
                    });
        } catch (IOException e) {
            System.err.println("Error listing files in directory: " + e.getMessage());
        }

        return files;
    }
}
