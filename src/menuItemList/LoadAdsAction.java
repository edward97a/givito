package menuItemList;

import storage.AdStorageContract;
import storage.JsonAdRepository;

import java.util.Scanner;

    public class LoadAdsAction extends MenuAction {
        private Scanner scanner;
        private AdStorageContract storage;

        public LoadAdsAction (Scanner scanner, AdStorageContract storage) {
            super("6: Load Ads");
            this.scanner = scanner;
            this.storage = storage;
        }

        @Override
        public void execute() {
            System.out.println("Please enter file name to load Ads:");
            String enterLoadFilePath = scanner.nextLine();
            try {
                storage.loadAllAds(enterLoadFilePath);
                System.out.println("File with ads is successfully loaded");
                System.out.println(storage.getAllAds().size() + " ads are loaded");
            } catch (Exception e) {
                System.out.println("Ads load error: " + e.getMessage());
            }
        }
    }
