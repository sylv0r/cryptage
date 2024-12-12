package cli.StorageActionCLI;

import FileManager.FileManager;

/**
 * GetAllPassword is the CLI that handles the getting of all passwords.
 */
public class GetAllPassword {
    public static void main() {
        FileManager.listFiles().forEach(System.out::println);
    }
}
