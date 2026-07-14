package rusty_badlands.client;

import net.fabricmc.api.ClientModInitializer;

public class RustyBadlandsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModClientParticles.initialize();
	}
}