package menuItemList;

import exceptions.AdNotFoundException;
import model.Ad;
import storage.AdStorage;

import java.util.List;
import java.util.Scanner;

public class SearchAction extends MenuAction {
    private Scanner scanner;
    private AdStorage storage;

    public SearchAction(Scanner scanner, AdStorage storage) {
        super("4: Search Ads");
        this.scanner = scanner;
        this.storage = storage;
    }

    @Override
    public void execute() {
        System.out.println("Please enter title or ID to find Ad:");
        String searchText = scanner.nextLine();

        boolean onlyDigits = true;
        for (int i = 0; i < searchText.length(); i++) {
            if (!Character.isDigit(searchText.charAt(i))) {
                onlyDigits = false;
            }
        }

        if (onlyDigits && !searchText.isBlank()) {
            int idNumber = Integer.parseInt(searchText);
            try {
                Ad foundId = storage.findById(idNumber);
                System.out.println(foundId);
            } catch (AdNotFoundException e) {
                System.out.println("Ads ID is not found");
            }
            return;
        }

        if (searchText.isBlank()) {
            System.out.println("Please enter title");
            return;
        }

        List<Ad> foundAds = storage.findByTitle(searchText);
        if (foundAds.isEmpty()) {
            System.out.println("Ad is not found");
        } else {
            for (Ad ad : foundAds) {
                System.out.println(ad);
            }
        }
    }
}