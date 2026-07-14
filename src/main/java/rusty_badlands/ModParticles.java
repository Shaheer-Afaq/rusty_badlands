package rusty_badlands;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ModParticles{

    public static final SimpleParticleType RUST = register("rust");

    public static void initialize(){

    }
    public static SimpleParticleType register(String name){
        SimpleParticleType simpleParticleType = FabricParticleTypes.simple();
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, name), simpleParticleType);
        return simpleParticleType;
    }

}
