package storage;

import exceptions.AdNotFoundException;
import model.Ad;

import java.io.IOException;
import java.util.*;

import static java.util.Collections.unmodifiableList;

public class AdStorage implements AdStorageContract
{
   private List<Ad> adList;
   private Map<Integer, Ad> adMap;
   FileManager filemanager = new FileManager();

   public AdStorage () {
       adList = new ArrayList<>();
       adMap = new HashMap<>();
   }

   @Override
   public void addAd(Ad ad) {
    adList.add(ad);
    adMap.put(ad.getId(), ad);
   }

   @Override
   public List<Ad> getAllAds() {
       return unmodifiableList(adList);
   }

   @Override
   public Ad findById(int id) {
       if (adMap.get(id) == null) {
           throw new AdNotFoundException(id + " " + "is missing");
       }
       return adMap.get(id);

   }

    @Override
    public List<Ad> findByTitle(String title) {
        List<Ad> result = new ArrayList<>();
        for (int i = 0; i <= adList.size() - 1 ; i++) {
            if(adList.get(i).getTitle().toLowerCase().contains(title.toLowerCase())) {
            result.add(adList.get(i));
            }
        }
        return result;
    }
    @Override
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

   @Override
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
       @Override
       public void saveAllAds(String filepath) throws IOException {
        filemanager.saveAdsToFile(adList, filepath);
       }

       @Override
       public void loadAllAds(String filepath) {
           try {
               List<Ad> newLoadedAds = filemanager.loadAdsFromFile(filepath);
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
