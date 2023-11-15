import java.util.List;
import java.util.Scanner;
import java.io.*;

public class CreateDirectory {
    private static PhoneDirectory directory = new PhoneDirectory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String fileName;
        int choice;
        do {
            System.out.println("1. Load from file");
            System.out.println("2. Add or change an entry");
            System.out.println("3. Remove an entry");
            System.out.println("4. Search for an entry");
            System.out.println("5. Display all entries");
            System.out.println("6. Save the current phone directory to file");
            System.out.println("7. Create a new phone directory (and remove the current one)");
            System.out.println("8. Quit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (choice) {
                case 1:
                    fileName = getFileName();
                    loadFromFile(fileName);
                    break;
                case 2:
                    addOrChangeEntry();
                    break;
                case 3:
                    directory.removeEntry(null);
                    break;
                case 4:
                    directory.searchEntry(null);
                    break;
                case 5:
                    directory.displayAllEntries();
                    break;
                case 6:
                    fileName = getFileName();
                    saveFileEntries(fileName);
                    break;
                case 7:
                    
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 8);
    }

    private static void loadFromFile(String filePath) {
        try {
            BufferedReader br = new BufferedReader(
                 new FileReader(filePath));
            String name;
            System.out.println("Opening file...");
            while ((name = br.readLine()) != null) {
                if (!name.isEmpty()) {
                    String number = br.readLine();
                    directory.addOrChangeEntry(name, number);
                }
            }
            directory.displayAllEntries();
            br.close();
        }
        catch (Exception ex) {
            System.out.println(ex+" file open failed");
        }
    }

    public static void addOrChangeEntry() {
        Scanner input = new Scanner(System.in);
        scanner.nextLine(); 
        int choice;
        System.out.println("1. Enter one if you wish to add an entry");
        System.out.println("2. Enter two if you wish to change an entry");
        do {
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 1:
                    System.out.println("Enter name");
                    System.out.println("Enter phone");
                case 2:
                    System.out.println("Enter phone");

            }
        }
    }

    private static void saveFileEntries(String filePath) throws IOException{
        Scanner input = new Scanner(System.in);
        int choice;
        List<DirectoryEntry> entries = directory.getAllEntries();
        do {
            choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("1. Enter one if you wish your file to be replaced");
            System.out.println("2. Enter two if you wish your file to be appended");
            switch(choice) {
                case 1:
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                        PrintWriter prw= new PrintWriter (filePath);
                        for (DirectoryEntry entry : entries) {
                            prw.println(entry.toString());          
                            prw.close();
                        }
                        entries.clear();
                    } 
                    break;
                case 2:
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                        for (DirectoryEntry entry : entries) {
                            bw.write(entry.toString());
                        }
                        entries.clear();
                    } 
                    break; 
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 3);
    }


    public static String getFileName() {
        System.out.print("Please enter the name of the file: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}

