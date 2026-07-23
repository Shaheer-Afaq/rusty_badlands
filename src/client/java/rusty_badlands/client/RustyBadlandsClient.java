package rusty_badlands.client;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import rusty_badlands.entities.ModEntityTypes;

public class RustyBadlandsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModClientParticles.initialize();
		EntityRenderers.register(ModEntityTypes.RUSTY_BALL, ThrownItemRenderer::new);
	}
}