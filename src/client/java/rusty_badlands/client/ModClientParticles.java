package rusty_badlands.client;

import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.ParticleProvider;
import rusty_badlands.ModParticles;

public class ModClientParticles{

    public static void initialize(){
        ParticleProviderRegistry.getInstance().register(
                ModParticles.RUST,
                FallingLeavesParticle.CherryProvider::new
        );
    }
}
