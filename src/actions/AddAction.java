package actions;

import model.Ad;
import storage.AdStorage;

import java.util.Scanner;

public class AddAction extends MenuAction {
    private Scanner scanner;
    private AdStorage storage;
    private int nextId = 1;

    public AddAction(Scanner scanner, AdStorage storage) {
        super("Add action");
        this.scanner = scanner;
        this.storage = storage;

    }

    @Override
    public void execute() {
        System.out.println("Please enter model.Ad's title");
        String title = scanner.nextLine();
        while (title.isBlank()) {
            System.out.println("Please enter title, it cannot be null");
            title = scanner.nextLine();
        }

        System.out.println("Please enter price $");
        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Price must be a number");
            return;
        }
        int price = scanner.nextInt();
        scanner.nextLine();

        // nextId должен быть больше любого существующего id
        for (Ad ad : storage.getAllAds()) {
            if (ad.getId() >= nextId) {
                nextId = ad.getId() + 1;
            }
        }

        storage.addAd(new Ad(nextId, title, price));
        System.out.println("model.Ad is added");
        nextId += 1;

    }
}
