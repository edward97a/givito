import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    private AdStorage storage;
    public ConsoleView(AdStorage storage) {
        this.scanner = new Scanner (System.in);
        this.storage = storage;
    }
    private int nextId = 1;

    public void showMenu() {
        int menu = -1;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1: Add Ad");
            System.out.println("2: Show all Ads");
            System.out.println("3: Delete Ad: Enter ID");
            System.out.println("4: Search by title or ID");
            System.out.println("5: Modify Ad");
            System.out.println("6: Load Ads");
            System.out.println("7: Save Ads");
            System.out.println("8: Delete all free ads");
            System.out.println("0: Exit");

            // безопасное чтение пункта меню
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Incorrect input");
                continue;
            }

            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1: {
                    System.out.println("Please enter Ad's title");
                    String title = scanner.nextLine();
                    while (title.isBlank()) {
                        System.out.println("Please enter title, it cannot be null");
                        title = scanner.nextLine();
                    }

                    System.out.println("Please enter price $");
                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("Price must be a number");
                        break;
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
                    System.out.println("Ad is added");
                    nextId += 1;
                    break;
                }

                case 2: {
                    List<Ad> all = storage.getAllAds();
                    if (all.isEmpty()) {
                        System.out.println("Ad list is empty");
                    } else {
                        for (Ad ad : all) {
                            System.out.println(ad);
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.println("Please enter Ad ID to delete:");
                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("ID must be a number");
                        break;
                    }
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();

                    if (storage.deleteById(deleteId)) {
                        System.out.println("Ad with ID " + deleteId + " is deleted");
                    } else {
                        System.out.println("AD's id is not found");
                    }
                    break;
                }

                case 4: {
                    System.out.println("Please enter title or ID to find Ad:");
                    String searchText = scanner.nextLine();
                    searchAds(searchText);
                    break;
                }

                case 5: {
                    System.out.println("Please enter Ad ID to modify:");
                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("ID must be a number");
                        break;
                    }
                    int searchID = scanner.nextInt();
                    scanner.nextLine();
                    modifyAd(searchID);
                    break;
                }

                case 6: {
                    System.out.println("Please enter file name to load Ads:");
                    String enterLoadFilePath = scanner.nextLine();
                    try {
                        storage.loadAllAds(enterLoadFilePath);
                        System.out.println("File with ads is successfully loaded");
                        System.out.println(storage.getAllAds().size() + " ads are loaded");
                    } catch (Exception e) {
                        System.out.println("Ads load error: " + e.getMessage());
                    }
                    break;
                }

                case 7: {
                    System.out.println("Please enter file name to save Ads:");
                    String enterSaveFilePath = scanner.nextLine();
                    try {
                        storage.saveAllAds(enterSaveFilePath);
                        System.out.println("Ads are saved");
                    } catch (IOException e) {
                        System.out.println("Ads save error: " + e.getMessage());
                    }
                    break;
                }

                case 8: {
                    System.out.println("Please confirm Y/N");
                    String enteredValue = scanner.nextLine();
                    if (enteredValue.equalsIgnoreCase("Y")) {
                        storage.deleteAllFreeAds();
                        System.out.println("Free ads are deleted");
                    }
                    break;
                }

                case 0: {
                    System.out.println("Please enter file name to save before exit:");
                    String enterSaveFilePathOnExit = scanner.nextLine();
                    if (!enterSaveFilePathOnExit.isBlank()) {
                        try {
                            storage.saveAllAds(enterSaveFilePathOnExit);
                            System.out.println("Ads are saved");
                        } catch (IOException e) {
                            System.out.println("Ads save error: " + e.getMessage());
                        }
                    }
                    break;
                }

                default:
                    System.out.println("Incorrect input");
            }
        } while (menu != 0);
    }

    public void modifyAd (int adID) {
        List<Ad> ad = storage.getAllAds();
        boolean foundID = false;
        for (int i = 0; i <= ad.size() - 1; i++) {
            Ad currentObject = ad.get(i);
            int currentObjectID = currentObject.getId();
            if (currentObjectID == adID) {
                foundID = true;
                System.out.println(currentObject);
                System.out.println("Enter price:");
                currentObject.setPrice(scanner.nextInt());
                scanner.nextLine();
                System.out.println("Enter title");
                currentObject.setTitle(scanner.nextLine());
            }
        }
        if (foundID == false) {
            System.out.println("Ad ID is not found");
        }
    }


    public void searchAds (String searchText) {
    List<Ad> ad = storage.getAllAds();
        boolean onlyDigits = true;
        for (int i = 0; i <= searchText.length()- 1 ; i++) {
            if (Character.isDigit(searchText.charAt(i)) != true) {
                onlyDigits = false;
            }
        } if (onlyDigits == true) { // if searchText is number
            int idNumber = Integer.parseInt(searchText);
            boolean foundID = false;
            for (int i = 0; i <= ad.size() - 1; i++) {
                Ad currentId = ad.get(i);
                int adId = currentId.getId();
                if (adId == idNumber) {
                    foundID = true;
                    System.out.println(currentId);
                    return;
                }
            } if (foundID == false){
                System.out.println("Ads ID is not found");
                return;
            }
        }
        boolean found = false;
        if (ad.isEmpty()) { // empty list check
            System.out.println("Ad list is empty");
            return;
        }

        if (searchText.isBlank()) { // empty input check
            System.out.println("Please enter title");
            return;
        }
        for (int i = 0; i <= ad.size() - 1; i++) {
            Ad currentTitle = ad.get(i);
            String title = currentTitle.getTitle();
//            if (currentTitle.getTitle().contains(searchText)) {
            if (title.contains(searchText)) {
                System.out.println(currentTitle);
                found = true;
            }
        } if (found == false) {
            System.out.println("Ad is not found");
        }
    }
}

