package com.adnet.testmvvm.ui.video;

import java.util.Random;

public class VideoIdsProvider {
    private static String[] videoIds = {"ZAzWT8mRoR0", "fTc5tuEn6_U", "XyzaMpAVm3s",};
    private static String[] liveVideoIds = {"hHW1oY26kxQ"};
    private static Random random = new Random();

    public static String getNextVideoId() {
        return videoIds[random.nextInt(videoIds.length)];
    }

    public static String getNextLiveVideoId() {
        return liveVideoIds[random.nextInt(liveVideoIds.length)];
    }
}
