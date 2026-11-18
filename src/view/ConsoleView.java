package view;
import actions.*;
import storage.AdStorage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    private AdStorage storage;

    public ConsoleView(AdStorage storage) {
        this.scanner = new Scanner(System.in);
        this.storage = storage;
    }

    public void showMenu() {
        List<MenuAction> actions = new ArrayList<>();
        actions.add(new AddAction(scanner, storage));
        actions.add(new ShowAllAction(storage));
        actions.add(new DeleteAction(scanner, storage));
        actions.add(new SearchAction(scanner, storage));
        actions.add(new ModifyAction(scanner, storage));
        actions.add(new LoadAdsAction(scanner, storage));
        actions.add(new SaveAdsAction(scanner, storage));
        actions.add(new DeleteAllFreeAction(scanner, storage));
        actions.add(new ExitAction(scanner));

        int menu = -1;
        do {
            System.out.println("\n--- MENU ---");
            for (int i = 0; i < actions.size(); i++) {
                System.out.println(actions.get(i).getName());
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

            if (menu > 0 && menu <= actions.size()) {
                actions.get(menu - 1).execute();
            } else {
                System.out.println("Incorrect input");
            }

        } while (true);
    }
}