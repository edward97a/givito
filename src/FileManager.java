import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static void saveAdsToFile (ArrayList<Ad> AdsObjects, String filepath){
        File file = new File(filepath);
        try {
            objectMapper.writeValue(file, AdsObjects);
        } catch (IOException e) {
            System.out.println("Error read the file:" + e.getMessage());
        }
    }

    public static ArrayList<Ad> loadAdsFromFile(String filepath) {
        File file = new File(filepath);

        try {
            return objectMapper.readValue(file, new TypeReference<ArrayList<Ad>>() {
            });
        } catch (IOException e) {
            System.out.println("Error load the file" + e.getMessage());
            return new ArrayList<>();
        }
    }
}
