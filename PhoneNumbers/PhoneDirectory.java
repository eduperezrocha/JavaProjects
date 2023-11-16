import java.util.ArrayList;

public class PhoneDirectory {
    private ArrayList<DirectoryEntry> theDirectory;

    public PhoneDirectory() {
        theDirectory = new ArrayList<>();
    }

    public String addOrChangeEntry(String name, String number) {
        for (DirectoryEntry entry : theDirectory) {
            if (entry.getName().equals(name)) {
                String oldNumber = entry.getNumber();
                entry.setNumber(number);
                return oldNumber;
            }
        }
        theDirectory.add(new DirectoryEntry(name, number));
        return null;
    }

    public DirectoryEntry searchEntry(String name) {
        for (DirectoryEntry entry : theDirectory) {
            if (entry.getName().equals(name)) {
                return entry;
            }
        }
        return null;
    }

    public void clear() {
        theDirectory.clear();
    }

    public DirectoryEntry removeEntry(String name) {
        for (int i = 0; i < theDirectory.size(); i++) {
            if (theDirectory.get(i).getName().equals(name)) {
                return theDirectory.remove(i);
            }
        }
        return null;
    }


    public void displayAllEntries() {
        for (DirectoryEntry entry : theDirectory) {
            System.out.println("Name: " + entry.getName());
            System.out.println("Number: " + entry.getNumber());
            System.out.println(); // empty line between entries
        }
    }
    public ArrayList<DirectoryEntry> getAllEntries() {
        return new ArrayList<>(theDirectory); // return a copy to protect the original list
    }
}
