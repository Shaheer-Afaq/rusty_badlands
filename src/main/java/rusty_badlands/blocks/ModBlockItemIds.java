package rusty_badlands.blocks;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import rusty_badlands.RustyBadlands;

public class ModBlockItemIds {
    private static BlockItemId create(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, name);
        return BlockItemId.create(id, id);
    }
}