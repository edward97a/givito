import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import static java.awt.SystemColor.menu;

public class Main {
    public static void main (String[] args) {
//        Ad ad = new Ad (1, "iphone", 2500);
//        System.out.println(ad);

        List<Ad> AdObjects = new ArrayList<>();
        Map<Integer, Ad> adMap = new HashMap<>();
        int nextId = 1;

        try {
        AdObjects = FileManager.loadAdsFromFile("ads.json");
            System.out.println("File uploaded");
            System.out.println(AdObjects.size() + " ads are loaded");
            int maxIdForAd = 0;
            for (int i = 0; i <= AdObjects.size() - 1; i++) {
            int idForAd = AdObjects.get(i).getId();
            if (idForAd > maxIdForAd) {
                maxIdForAd = idForAd;
            }
            } nextId = maxIdForAd + 1;
        }
        catch (IOException e) {
            System.out.println("File upload failed" + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        int menu;
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
            menu = scanner.nextInt();
            scanner.nextLine();
            switch (menu) {
                case 1:
                    System.out.println("Please enter Ad's title");
                    String title = scanner.nextLine();
                    while (title.isBlank()) {
                        System.out.println("Please enter title, it cannot be null");
                        title = scanner.nextLine();
                    }

                    System.out.println("Please enter price $");
                    int price = scanner.nextInt();

                    AdObjects.add(new Ad(nextId, title, price));
                    System.out.println("Ad is added");
                    nextId += 1;
                break;
                case 2:
                    System.out.println(AdObjects);
                    break;
                case 3:
                    boolean foundId = false;
                    int deleteId = scanner.nextInt();
                    for (int i = 0; i <= AdObjects.size() - 1; i++) {
                        Ad currentAd = AdObjects.get(i);
                        if (deleteId == currentAd.getId()) {
                            AdObjects.remove(i);
                            foundId = true;
                            System.out.println("Ad with ID " + deleteId + " is deleted");
                            break;
                        }
                    }
                    if (foundId == false) {
                        System.out.println("AD's id is not found:");
                    }
                    break;
                case 4:
                    System.out.println("Please enter title to find Ad:");
                    String searchText = scanner.nextLine();
                    searchAds(AdObjects, searchText);
                    break;
                case 5:
                    System.out.println("Please enter Ad ID to modify:");
                    int searchID = scanner.nextInt();
                    modifyAd(AdObjects, searchID);
                    System.out.println("AD is modified");
                    break;
                case 6:
                    System.out.println("Please enter file name to load Ads:");
                    String enterLoadFilePath = scanner.nextLine();
                    try {
                    AdObjects = FileManager.loadAdsFromFile(enterLoadFilePath);
                        System.out.println("File with ads is successfully loaded");
                        System.out.println(AdObjects.size() + " ads are loaded");
                    } catch (IOException e){
                    System.out.println("Ads load error:" + e.getMessage());
                }
                    break;
                case 7:
                    System.out.println("Please enter file name to save Ads:");
                    String enterSaveFilePath = scanner.nextLine();
                    FileManager.saveAdsToFile(AdObjects, enterSaveFilePath);
                    break;
                case 8:
                    System.out.println("Please confirm Y/N");
                    Scanner scanner1 = new Scanner(System.in);
                    String enteredValue = scanner1.nextLine();
                    if (enteredValue.equalsIgnoreCase("Y")) {
                        deleteFreeAds(AdObjects);
                    } else break;

                case 0:
                    System.out.println("Please enter file name to save before exit:");
                    String enterSaveFilePathOnExit = scanner.nextLine();
                    FileManager.saveAdsToFile(AdObjects, enterSaveFilePathOnExit);
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        } while (menu != 0);
    }
}

