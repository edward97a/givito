import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import static java.awt.SystemColor.menu;

public class Main {
    public static void main (String[] args) {
//        Ad ad = new Ad (1, "iphone", 2500);
//        System.out.println(ad);

        ArrayList<Ad> AdObjects = new ArrayList<>();
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
                        } }
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

    public static void searchAds (ArrayList<Ad> AdObjects, String searchText) {
        boolean onlyDigits = true;
        for (int i = 0; i <= searchText.length()- 1 ; i++) {
            if (Character.isDigit(searchText.charAt(i)) != true) {
                onlyDigits = false;
            }
        } if (onlyDigits == true) { // if searchText is number
            int idNumber = Integer.parseInt(searchText);
            boolean foundID = false;
            for (int i = 0; i <= AdObjects.size() - 1; i++) {
                Ad currentId = AdObjects.get(i);
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
        if (AdObjects.isEmpty()) { // empty list check
            System.out.println("Ad list is empty");
            return;
        }

        if (searchText.isBlank()) { // empty input check
            System.out.println("Please enter title");
            return;
        }
        for (int i = 0; i <= AdObjects.size() - 1; i++) {
            Ad currentTitle = AdObjects.get(i);
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

   public static void showAllAds (ArrayList<Ad> AdObjects){
        if (AdObjects != null) {
            for (Ad objects : AdObjects) {
                System.out.println(objects);
            }
        } else {
            System.out.println("AdObjects list is empty");
        }
   }

   public static void modifyAd (ArrayList<Ad> AdObjects, int adID) {
        boolean foundID = false;
       for (int i = 0; i <= AdObjects.size() - 1; i++) {
           Ad currentObject = AdObjects.get(i);
           int currentObjectID = currentObject.getId();
           if (currentObjectID == adID) {
               foundID = true;
               System.out.println(currentObject);
               Scanner scanner = new Scanner(System.in);
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
}