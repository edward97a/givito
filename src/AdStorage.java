import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util. Map;
public class AdStorage
{
   private List<Ad> adList;
   private Map<Integer, Ad> adMap;

   public AdStorage () {
       adList = new ArrayList<>();
       adMap = new HashMap<>();
   }

   public void add(Ad ad) {
   }

   public List<Ad> getAllAds() {
        return null;
   }

   public Ad findById(int id) {
       return null;
   }

   public void deleteById(int id) {
   }

   public List<Ad> findByTitle(String title) {
       return null;
   }

   public void deleteAllFreeAds() {

   }

   public void saveAllAds(String filepath) {

   }

   public void loadAllAds(String filepath) {

   }

}
