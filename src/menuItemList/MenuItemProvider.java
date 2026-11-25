package menuItemList;
import storage.AdStorage;
import storage.AdStorageContract;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuItemProvider {
   Map<Integer, MenuAction> menuItemList = new HashMap<>();

   public MenuItemProvider (Scanner scanner, AdStorageContract adStorageContract) {
      menuItemList.put(1, new AddAction(scanner, (AdStorage) adStorageContract));
      menuItemList.put(2, new ShowAllAction((AdStorage) adStorageContract));
      menuItemList.put(3, new DeleteAction(scanner, (AdStorage) adStorageContract));
      menuItemList.put(4, new SearchAction(scanner, (AdStorage) adStorageContract));
      menuItemList.put(5, new ModifyAction(scanner, (AdStorage) adStorageContract));
      menuItemList.put(6, new LoadAdsAction(scanner, (AdStorage) adStorageContract));
      menuItemList.put(7, new SaveAdsAction(scanner, (AdStorage) adStorageContract));
      menuItemList.put(8, new DeleteAllFreeAction(scanner, (AdStorage) adStorageContract));
      menuItemList.put(0, new ExitAction(scanner));
   }

      public MenuAction getCommand(int number) {
         return menuItemList.get(number);
      }

      public int size() {
      return menuItemList.size();
      }

}
