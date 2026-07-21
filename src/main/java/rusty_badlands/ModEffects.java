package rusty_badlands;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import rusty_badlands.effects.TetanusEffect;

public class ModEffects {

    public static final Holder<MobEffect> TETANUS =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, "tetanus"), new TetanusEffect());

    public static void initialize(){

    }
}
