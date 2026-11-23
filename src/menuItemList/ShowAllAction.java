package menuItemList;

import model.Ad;
import storage.AdStorage;

import java.util.List;

public class ShowAllAction extends MenuAction {
    private AdStorage storage;

   public ShowAllAction(AdStorage storage) {
       super("2: Show all ads");
       this.storage = storage;
   }

   @Override
   public void execute () {
       List<Ad> all = storage.getAllAds();
       if (all.isEmpty()) {
           System.out.println("model.Ad list is empty");
           return;
       } else {
           for (Ad ad : all) {
               System.out.println(ad);
           }
       }
   }
   }

