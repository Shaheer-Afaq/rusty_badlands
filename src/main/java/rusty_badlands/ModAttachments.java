package rusty_badlands;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.lwjgl.system.ffm.mapping.Mapping;

import java.util.HashMap;
import java.util.Map;

public class ModAttachments {
    public static final AttachmentType<Float> TETANUSSTRENGTH = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, "tetanus_strength"),
            builder -> builder
                    .initializer(() -> 0f)
                    .persistent(Codec.FLOAT)
    );
}
