package rusty_badlands.client.data_generation;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;
import rusty_badlands.blocks.ModBlocks;
import rusty_badlands.items.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createRotatedVariantBlock(ModBlocks.RUSTY_SAND);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.RUSTY_STONE, TexturedModel.CUBE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.RUSTY_BALL, ModelTemplates.FLAT_ITEM);
    }

    @Override
    public String getName() {
        return "ModelProvider";
    }
}