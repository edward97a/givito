package menuItemList;
import storage.AdStorage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuItemProvider {
   Map<Integer, MenuAction> menuItemList = new HashMap<>();

   public MenuItemProvider (Scanner scanner, AdStorage storage) {
      menuItemList.put(1, new AddAction(scanner, storage));
      menuItemList.put(2, new ShowAllAction(storage));
      menuItemList.put(3, new DeleteAction(scanner, storage));
      menuItemList.put(4, new SearchAction(scanner, storage));
      menuItemList.put(5, new ModifyAction(scanner, storage));
      menuItemList.put(6, new LoadAdsAction(scanner, storage));
      menuItemList.put(7, new SaveAdsAction(scanner, storage));
      menuItemList.put(8, new DeleteAllFreeAction(scanner, storage));
      menuItemList.put(0, new ExitAction(scanner));
   }

      public MenuAction getCommand(int number) {
         return menuItemList.get(number);
      }

      public int size() {
      return menuItemList.size();
      }

}
