package net.radwl.instantsneak;

import net.fabricmc.api.ClientModInitializer;
import net.radwl.instantsneak.config.ModConfigs;

public class InstantSneakClient implements ClientModInitializer {
	public static final String MOD_ID = "instant-sneak";

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModConfigs.registerConfigs();
	}
}