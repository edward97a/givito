import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileManager {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void saveAdsToFile (List<Ad> AdsObjects, String filepath){
        File file = new File(filepath);
        try {
            objectMapper.writeValue(file, AdsObjects);
        } catch (IOException e) {
            System.out.println("Error read the file:" + e.getMessage());
        }
    }

    public static List<Ad> loadAdsFromFile(String filepath) throws IOException {
        File file = new File(filepath);
            List<Ad> loadedAds = objectMapper.readValue(file, new TypeReference<List<Ad>>() {});
            Set<Integer> unicIdCheck = new HashSet<>();
        int maxIdcount = 0;
        for (int i = 0; i <= loadedAds.size() - 1; i++) {
            int currentId = loadedAds.get(i).getId();
            if (maxIdcount < currentId) {
                maxIdcount = currentId;
            }
        }
            for (int i = 0; i <= loadedAds.size() - 1; i++) {
                boolean idcheck = unicIdCheck.add(loadedAds.get(i).getId());
                if (!idcheck) {
                    maxIdcount += 1;
                    loadedAds.get(i).setId(maxIdcount);
                }
        }
            return loadedAds;
        }

    }
