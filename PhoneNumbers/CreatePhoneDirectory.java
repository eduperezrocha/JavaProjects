 /*
 *
 * Description: 
 * This program creates a dictionary with an entry of name and phone number.
 * It can save the file in multi-line format, remove an entry, search or even display 
 * all entries that were added. These all can be done with an easy to use MENU.
 * 
 * Date: November 15th 2023
 * Authors: Eduardo Perez Rocha
 * 
 */

import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class CreatePhoneDirectory {
    private static PhoneDirectory directory = new PhoneDirectory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String fileName;
        String name;
        int choice;
        do {
            System.out.println("----------------");
            System.out.println("|     MENU     |");
            System.out.println("----------------");
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
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    fileName = getFileName();
                    loadFromFile(fileName);
                    break;
                case 2:
                    addOrChangeEntry();
                    break;
                case 3:
                    System.out.print("Enter the name of contact you wish to remove: ");
                    name = scanner.nextLine();
                    directory.removeEntry(name);
                    System.out.println(name + " removed from the directory");
                    break;
                case 4:
                    System.out.print("Enter the name you wish to search: ");
                    name = scanner.nextLine();
                    if (directory.searchEntry(name) == null) {
                        System.out.println("ERROR: name \"" + name + "\" Not Found, please try again \n");
                        addOrChangeEntry();
                    }
                    else{
                        System.out.println("Name: " + name + "found");
                    }
                    System.out.println("Press Enter to go back to Menu...");
                    scanner.nextLine();
                    break;
                case 5:
                    directory.displayAllEntries();
                    System.out.println("\nDisplaying all entries...");
                    System.out.println("Press Enter to go back to Menu...");
                    scanner.nextLine();
                    break;
                case 6:
                    fileName = getFileName();
                    saveFileEntries(fileName);
                    break;
                case 7:
                    directory.clear();
                    System.out.println("Deleting directory...");
                    System.out.println("A new directory has been created");
                    System.out.println("Press Enter to go back to Menu...");
                    scanner.nextLine();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    directory.clear();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 8);
    }

    private static void loadFromFile(String filePath) {
        try {
            directory.clear();
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
            System.out.println("File opened successfully");
            br.close();
        }
        catch (Exception ex) {
            System.out.println(ex+" file open failed");
        }
    }

    public static void addOrChangeEntry() {
        Scanner input = new Scanner(System.in);
        int choice;
        String name;
        String phone;
        do {
            System.out.println("1. Enter one if you wish to add an entry");
            System.out.println("2. Enter two if you wish to change an entry");
            System.out.println("3. Enter three to go back to Menu");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 1:
                    System.out.print("Enter the name: ");
                    name = input.nextLine();
                    System.out.print("Enter the phone number: ");
                    phone = input.nextLine();
                    directory.addOrChangeEntry(name, phone);
                    System.out.println("Name: " + name + " and Phone: " + phone + " added.");
                    break;
                case 2:
                    System.out.print("Enter the name: ");
                    name = input.nextLine();
                    if (directory.searchEntry(name) == null) {
                        System.out.println("ERROR: name \"" + name + "\" Not Found, please try again \n");
                        addOrChangeEntry();
                    }
                    else {
                        System.out.println("Enter the new phone number: ");
                        phone = input.nextLine();
                        directory.addOrChangeEntry(name, phone);
                        System.out.print("\n" + name +  "new phone number is " + phone );
                    }
                    break;
                case 3:
                    break;
            } 
        }while(choice != 3);
    }

    private static void saveFileEntries(String filePath) throws IOException {
        Scanner input = new Scanner(System.in);
        int choice;
        List<DirectoryEntry> entries = directory.getAllEntries();
        while (true) {
            System.out.println("1. Enter one if you wish your file to be replaced");
            System.out.println("2. Enter two if you wish your file to be appended");
            System.out.println("3. Enter three if you wish to return to the menu");
            choice = input.nextInt();
            input.nextLine(); 
    
            switch (choice) {
                case 1:
                    System.out.println("Replacing file...");
                    try (PrintWriter prw = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
                        for (DirectoryEntry entry : entries) {
                            prw.println(entry.getName());
                            prw.println(entry.getNumber());
                            prw.println(); 
                        }
                    }
                    System.out.println("File replaced");
                    return; 
    
                case 2:
                    System.out.println("Appending file...");
                    try (PrintWriter prw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
                        for (DirectoryEntry entry : entries) {
                            prw.println(entry.getName());
                            prw.println(entry.getNumber());
                            prw.println(); 
                        }
                    }
                    System.out.println("File appended");
                    return; 
    
                case 3:
                    System.out.println("Returning to the menu...");
                    return;
    
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    

    public static String getFileName() {
        System.out.print("Please enter the name of the file: ");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
