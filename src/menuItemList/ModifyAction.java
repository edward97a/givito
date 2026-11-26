package menuItemList;

import exceptions.AdNotFoundException;
import model.Ad;
import storage.AdStorageContract;
import storage.JsonAdRepository;

import java.util.Scanner;

public class ModifyAction extends MenuAction {
    private Scanner scanner;
    private AdStorageContract storage;

    public ModifyAction(Scanner scanner, AdStorageContract storage) {
        super("5: Modify Ad");
        this.scanner = scanner;
        this.storage = storage;
    }

    @Override
    public void execute() {
        System.out.println("Please enter Ad ID to modify:");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("ID must be a number");
            return;
        }
        int searchID = scanner.nextInt();
        scanner.nextLine();

        try {
            Ad currentObject = storage.findById(searchID);
            System.out.println(currentObject);
            System.out.println("Enter price:");
            currentObject.setPrice(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Enter title");
            currentObject.setTitle(scanner.nextLine());
        } catch (AdNotFoundException e) {
            System.out.println("Ad ID is not found");
        }
    }
}