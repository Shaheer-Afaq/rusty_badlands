package rusty_badlands;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules {
    public static SurfaceRules.RuleSource makeRules(HolderGetter<Biome> biomeHolderGetter) {
        SurfaceRules.ConditionSource isRustyBadlands = SurfaceRules.isBiome(biomeHolderGetter, ModBiomes.RUSTY_BADLANDS);

        SurfaceRules.RuleSource surface = SurfaceRules.state(ModBlocks.RUSTY_SAND.defaultBlockState());
        SurfaceRules.ConditionSource surfaceCondition = SurfaceRules.stoneDepthCheck(1, false, 0, CaveSurface.FLOOR);

        SurfaceRules.RuleSource subSurface = SurfaceRules.state(Blocks.DYED_TERRACOTTA.red().defaultBlockState());
        SurfaceRules.ConditionSource subSurfaceCondition = SurfaceRules.stoneDepthCheck(4, false, 0, CaveSurface.FLOOR);

        SurfaceRules.RuleSource rustyStone = SurfaceRules.state(ModBlocks.RUSTY_STONE.defaultBlockState());
        SurfaceRules.ConditionSource nearAirFloor = SurfaceRules.stoneDepthCheck(1, false, 0, CaveSurface.FLOOR);
        SurfaceRules.ConditionSource nearAirCeiling = SurfaceRules.stoneDepthCheck(1, false, 0, CaveSurface.CEILING);

        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                isRustyBadlands,
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.abovePreliminarySurface(),
                        SurfaceRules.sequence(
                            SurfaceRules.ifTrue(surfaceCondition, surface),
                            SurfaceRules.ifTrue(subSurfaceCondition, subSurface)
                        )
                    ),
                    SurfaceRules.ifTrue(nearAirCeiling, rustyStone),
                    SurfaceRules.ifTrue(nearAirFloor, rustyStone)
                )
            )
        );
    }
}
