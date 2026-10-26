import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            return objectMapper.readValue(file, new TypeReference<List<Ad>>() {});
        }
    }
