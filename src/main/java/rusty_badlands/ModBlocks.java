package rusty_badlands;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import rusty_badlands.blocks.RustFragmentsBlock;

import java.util.function.Function;

public class ModBlocks {

    public static final Block RUSTY_SAND = register(
            "rusty_sand",
            Block::new,
            BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.SAND),
            true
    );
    public static final Block RUSTY_STONE = register(
            "rusty_stone",
            Block::new,
            BlockBehaviour.Properties.of().strength(0.7F).sound(SoundType.SAND),
            true
    );
    public static final Block RUST_FRAGMENTS = register(
            "rust_fragments",
            RustFragmentsBlock::new,
            BlockBehaviour.Properties.of().instabreak().noCollision().sound(SoundType.LEAF_LITTER),
            true
    );

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register((creativeTab) -> {
            creativeTab.accept(ModBlocks.RUSTY_SAND.asItem());
            creativeTab.accept(ModBlocks.RUST_FRAGMENTS.asItem());
        });
    }

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(properties.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, name));
    }
}