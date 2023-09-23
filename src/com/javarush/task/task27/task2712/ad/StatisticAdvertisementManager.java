package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager advertisementManager;
    private static AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        if (advertisementManager == null) {
            advertisementManager = new StatisticAdvertisementManager();
        }
        return advertisementManager;
    }

    public List<Advertisement> getAvaliableVideos(boolean isActive) {
        List<Advertisement> res = new ArrayList<>();
        for (Advertisement video : advertisementStorage.list()) {
            if (video.isActive() == isActive) res.add(video);
        }
        return res;
    }
}
