import storage.AdStorageContract;
import storage.JsonAdRepository;
import view.ConsoleView;

public class Main {
    private static final String filename = "ads.json";
    public static void main (String[] args) {
        AdStorageContract storage = new JsonAdRepository();
        storage.loadAllAds(filename);
        ConsoleView view = new ConsoleView(storage);
        view.showMenu();
    }
}

