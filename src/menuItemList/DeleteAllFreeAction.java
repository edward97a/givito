package menuItemList;

import storage.AdStorage;

import java.util.Scanner;

public class DeleteAllFreeAction extends MenuAction {
    private Scanner scanner;
    private AdStorage storage;

    public DeleteAllFreeAction(Scanner scanner, AdStorage storage) {
        super("8: Delete all free ads");
        this.scanner = scanner;
        this.storage = storage;
    }

    @Override
    public void execute() {
        System.out.println("Please confirm Y/N");
        String enteredValue = scanner.nextLine();
        if (enteredValue.equalsIgnoreCase("Y")) {
            storage.deleteAllFreeAds();
            System.out.println("Free ads are deleted");
        }
    }
}