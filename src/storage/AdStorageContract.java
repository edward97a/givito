package storage;
import exceptions.AdNotFoundException;
import model.Ad;

import java.io.IOException;
import java.util.List;

public interface AdStorageContract {
    void addAd(Ad ad);
    List<Ad> getAllAds();
    Ad findById(int id) throws AdNotFoundException;
    List<Ad> findByTitle(String title);
    boolean deleteById(int id);
    void deleteAllFreeAds();
    void saveAllAds(String filepath) throws IOException;
    void loadAllAds(String filepath);
    }


