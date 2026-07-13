package rusty_badlands;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomes {
    public static final ResourceKey<Biome> RUSTY_BADLANDS = ResourceKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, "rusty_badlands")
    );
}
