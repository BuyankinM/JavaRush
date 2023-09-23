package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage storage;
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 500, 100, 7 * 60));
        videos.add(new Advertisement(someContent, "Second Video", 2000, 10, 4 * 60));
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
        videos.add(new Advertisement(someContent, "Fourth Video", 200, 2, 5 * 60));
        videos.add(new Advertisement(someContent, "Fifth Video", 20000, 0, 2 * 60));
        videos.add(new Advertisement(someContent, "Шестой ролик", 20000, 100, 2 * 60));
        videos.add(new Advertisement(someContent, "Седьмой ролик", 2000, 100, 2 * 60));
    }

    public static AdvertisementStorage getInstance() {
        if (storage == null) {
            storage = new AdvertisementStorage();
        }
        return storage;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
