public class Main {
    public static void main (String[] args) {
        AdStorage storage = new AdStorage();
        storage.loadAllAds("ads.json");
        ConsoleView view = new ConsoleView(storage);
        view.showMenu();
    }
}

