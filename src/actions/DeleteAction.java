package actions;

import storage.AdStorage;

import java.util.Scanner;

public class DeleteAction extends MenuAction {
    private AdStorage storage;
    private Scanner scanner;
    public DeleteAction(Scanner scanner, AdStorage storage) {
        super("3: Delete Ad");
        this.storage = storage;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Please enter model.Ad ID to delete:");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("ID must be a number");
            return;
        }
        int deleteId = scanner.nextInt();
        scanner.nextLine();

        if (storage.deleteById(deleteId)) {
            System.out.println("model.Ad with ID " + deleteId + " is deleted");
        } else {
            System.out.println("AD's id is not found");
        }
    }
    }
