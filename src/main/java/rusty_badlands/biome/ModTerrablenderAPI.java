package rusty_badlands.biome;

import net.minecraft.resources.Identifier;
import rusty_badlands.RustyBadlands;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, "overworld"), 20));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, RustyBadlands.MOD_ID, ModSurfaceRules::makeRules);
    }
}
