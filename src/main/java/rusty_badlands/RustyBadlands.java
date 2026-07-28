package rusty_badlands;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rusty_badlands.blocks.ModBlockEntities;
import rusty_badlands.blocks.ModBlocks;
import rusty_badlands.effects.ModEffects;
import rusty_badlands.entities.ModEntityTypes;
import rusty_badlands.items.ModItems;
import rusty_badlands.utils.TaskScheduler;

public class RustyBadlands implements ModInitializer {
	public static final String MOD_ID = "rusty_badlands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.initialize();
		ModBlockEntities.initialize();
		ModItems.initialize();
		ModParticles.initialize();
		ModEffects.initialize();
		ModEvents.initialize();
		ModEntityTypes.initialize();
		TaskScheduler.initialize();
		ModSounds.initialize();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
