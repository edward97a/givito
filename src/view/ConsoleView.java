package view;
import menuItemList.*;
import storage.JsonAdRepository;
import storage.AdStorageContract;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    private AdStorageContract storage;

    public ConsoleView(AdStorageContract storage) {
        this.scanner = new Scanner(System.in);
        this.storage = storage;
    }

    public void showMenu() {
        MenuItemProvider menuProvider = new MenuItemProvider(scanner, storage);
        int menu = -1;
        do {
            System.out.println("\n--- MENU ---");
            for (int i = 0; i < menuProvider.size(); i++) {
                System.out.println(menuProvider.getCommand(i).getName());
            }

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Incorrect input");
                continue;
            }

            menu = scanner.nextInt();
            scanner.nextLine();

            if (menu == 0) {
                System.out.println("Please enter file name to save before exit:");
                String path = scanner.nextLine();
                if (!path.isBlank()) {
                    try {
                        storage.saveAllAds(path);
                        System.out.println("Ads are saved");
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                break;
            }

            if (menu > 0 && menu <= menuProvider.size()) {
                menuProvider.getCommand(menu).execute();
            } else {
                System.out.println("Incorrect input");
            }

        } while (true);
    }
}