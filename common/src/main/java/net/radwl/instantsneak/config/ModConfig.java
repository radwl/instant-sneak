package net.radwl.instantsneak.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {

    @Entry() public static boolean INSTANT = true;
    @Entry(isSlider = true, min = 0.5, max = 2.0) public static double ANIMATION_SPEED = 1.0;
    @Entry(isSlider = true, min = 0.0, max = 0.75) public static double SNEAK_HEIGHT = 0.7;

}
