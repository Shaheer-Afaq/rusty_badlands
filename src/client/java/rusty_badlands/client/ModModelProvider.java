package rusty_badlands.client;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.TexturedModel;
import rusty_badlands.ModBlocks;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialBlock(ModBlocks.RUSTY_SAND, TexturedModel.CUBE_TOP_BOTTOM);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }

    @Override
    public String getName() {
        return "ModelProvider";
    }
}