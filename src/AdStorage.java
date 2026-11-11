import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class AdStorage
{
   private List<Ad> adList;
   private Map<Integer, Ad> adMap;

   public AdStorage () {
       adList = new ArrayList<>();
       adMap = new HashMap<>();
   }

   public void addAd(Ad ad) {
    adList.add(ad);
    adMap.put(ad.getId(), ad);
   }

   public List<Ad> getAllAds() {

       return adList;
   }

   public Ad findById(int id) {
       if (adMap.get(id) == null) {
           throw new AdNotFoundException(id + " " + "is missing");
       }
       return adMap.get(id);

   }

    public List<Ad> findByTitle(String title) {
        List<Ad> result = new ArrayList<>();
        for (int i = 0; i <= adList.size() - 1 ; i++) {
            if(adList.get(i).getTitle().toLowerCase().contains(title.toLowerCase())) {
            result.add(adList.get(i));
            }
        }
        return result;
    }

   public boolean deleteById(int id) {
       for (int i = 0; i <= adList.size() - 1; i++) {
           int currentId = adList.get(i).getId();
           if (currentId == id) {
               adList.remove(i);
               adMap.remove(id);
               return true;
           }
       }
       return false;
   }

   public void deleteAllFreeAds() {
       Iterator<Ad> it = adList.iterator();
       while (it.hasNext()) {
           Ad ad = it.next();
           if (ad.getPrice() <= 0) {
               adMap.remove(ad.getId());
               it.remove();
           }
       }
   }

   public void saveAllAds(String filepath) throws IOException {
    FileManager.saveAdsToFile(adList, filepath);
   }

   public void loadAllAds(String filepath) {
       try {
           List<Ad> newLoadedAds = FileManager.loadAdsFromFile(filepath);
           adList.clear();
           adMap.clear();
           for (int i = 0; i <= newLoadedAds.size() - 1; i++) {
               addAd(newLoadedAds.get(i));
           }
       } catch (IOException e) {
           System.out.println("File cannot be loaded" + e.getMessage());
       }
   }
}
