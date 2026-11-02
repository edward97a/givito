import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private Scanner scanner;
    private AdStorage storage;
    public ConsoleView(AdStorage storage) {
        this.scanner = new Scanner (System.in);
        this.storage = storage;
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

