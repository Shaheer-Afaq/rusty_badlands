package rusty_badlands;

import net.minecraft.resources.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.fromNamespaceAndPath(RustyBadlands.MOD_ID, "overworld"), 10));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, RustyBadlands.MOD_ID, ModSurfaceRules::makeRules);
    }
}
