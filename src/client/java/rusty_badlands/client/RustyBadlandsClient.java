package rusty_badlands.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import rusty_badlands.entities.ModEntityTypes;

import static net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents.END_MAIN;

public class RustyBadlandsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ModClientParticles.initialize();
		EntityRenderers.register(ModEntityTypes.RUSTY_BALL, ThrownItemRenderer::new);
	}
}