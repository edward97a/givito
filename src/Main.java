import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

import static java.awt.SystemColor.menu;

public class Main {
    public static void main (String[] args) {
//        Ad ad = new Ad (1, "iphone", 2500);
//        System.out.println(ad);

        ArrayList<Ad> AdObjects = new ArrayList<>();
        int nextId = 1;

        Scanner scanner = new Scanner(System.in);
        int menu;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1: Add Ad");
            System.out.println("2: Show all Ads");
            System.out.println("3: Delete Ad: Enter ID");
            System.out.println("4: Search by title");
            System.out.println("0: Exit");
            menu = scanner.nextInt();
            scanner.nextLine();
            switch (menu) {
                case 1:
                    System.out.println("Please enter Ad's title");
                    String title = scanner.nextLine();

                    int price = scanner.nextInt();
                    System.out.println("Please enter price");

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
                        System.out.println("AD's id is not found");
                    }
                    break;
                case 4:
                    System.out.println("Please enter title to find Ad");
                    String searchText = scanner.nextLine();
                    searchAds(AdObjects, searchText);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        } while (menu != 0);
    }

    public static void searchAds (ArrayList<Ad> AdObjects, String searchText) {
        for (int i = 0; i <= AdObjects.size() - 1; i++) {
            Ad currentTitle = AdObjects.get(i);
            String title = currentTitle.getTitle();
//            if (currentTitle.getTitle().contains(searchText)) {
            if (title.contains(searchText)) {
                System.out.println(currentTitle);
            }
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
}