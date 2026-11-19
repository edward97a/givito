import storage.AdStorage;
import view.ConsoleView;

public class Main {
    private static final String filename = "ads.json";
    public static void main (String[] args) {
        AdStorage storage = new AdStorage();
        storage.loadAllAds(filename);
        ConsoleView view = new ConsoleView(storage);
        view.showMenu();
    }
}

