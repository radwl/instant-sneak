package net.radwl.instantsneak.config;

import com.mojang.datafixers.util.Pair;
import net.radwl.instantsneak.InstantSneakClient;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean ANIMATION_ENABLED;
    public static double ANIMATION_SPEED;
    public static double SNEAK_HEIGHT;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();
        CONFIG = SimpleConfig.of(InstantSneakClient.MOD_ID + "config").provider(configs).request();
        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("animation.enabled", false), "Toggles the sneak animation");
        configs.addKeyValuePair(new Pair<>("animation.speed", 0.5), "Changes the animation speed | [0.0 to 1.0]");
        configs.addKeyValuePair(new Pair<>("sneak.height", 0.75), "Represents the sneak height | [0.0 to 1.0] |  0 is normal;  1 is the old height.");
    }

    private static void assignConfigs() {
        ANIMATION_ENABLED = CONFIG.getOrDefault("animation.enabled", false);
        ANIMATION_SPEED = CONFIG.getOrDefault("animation.speed", 0.5);
        SNEAK_HEIGHT = CONFIG.getOrDefault("sneak.height", 0.75);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}