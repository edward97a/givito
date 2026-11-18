package actions;

import storage.AdStorage;

import java.util.Scanner;
import java.io.IOException;

public class SaveAdsAction extends MenuAction {
    private Scanner scanner;
    private AdStorage storage;

    public SaveAdsAction(Scanner scanner, AdStorage storage) {
        super("7: Save Ads");
        this.scanner = scanner;
        this.storage = storage;
    }

    @Override
    public void execute() {
        System.out.println("Please enter file name to save Ads:");
        String enterSaveFilePath = scanner.nextLine();
        try {
            storage.saveAllAds(enterSaveFilePath);
            System.out.println("Ads are saved");
        } catch (IOException e) {
            System.out.println("Ads save error: " + e.getMessage());
        }
    }
}