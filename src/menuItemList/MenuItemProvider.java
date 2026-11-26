package menuItemList;
import storage.JsonAdRepository;
import storage.AdStorageContract;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuItemProvider {
   Map<Integer, MenuAction> menuItemList = new HashMap<>();

   public MenuItemProvider (Scanner scanner, AdStorageContract adStorageContract) {
      menuItemList.put(1, new AddAction(scanner, adStorageContract));
      menuItemList.put(2, new ShowAllAction(adStorageContract));
      menuItemList.put(3, new DeleteAction(scanner, adStorageContract));
      menuItemList.put(4, new SearchAction(scanner, adStorageContract));
      menuItemList.put(5, new ModifyAction(scanner, adStorageContract));
      menuItemList.put(6, new LoadAdsAction(scanner, adStorageContract));
      menuItemList.put(7, new SaveAdsAction(scanner, adStorageContract));
      menuItemList.put(8, new DeleteAllFreeAction(scanner, adStorageContract));
      menuItemList.put(0, new ExitAction(scanner));
   }

      public MenuAction getCommand(int number) {
         return menuItemList.get(number);
      }

      public int size() {
      return menuItemList.size();
      }

}
