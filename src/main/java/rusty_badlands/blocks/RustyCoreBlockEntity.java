package rusty_badlands.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import rusty_badlands.utils.TaskScheduler;

import java.util.List;

public class RustyCoreBlockEntity extends BlockEntity {
    public RustyCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RUSTY_CORE_BLOCK_ENTITY, pos, state);
    }
    public static void tick(Level level, BlockPos myPos, BlockState blockState, RustyCoreBlockEntity blockEntity) {
        if (level.getRandom().nextFloat() < 0.01 && level instanceof ServerLevel serverLevel) {
            if (level.getBlockState(myPos.above()).getBlock() == Blocks.WATER) {
                Vec3 startPos = Vec3.atCenterOf(myPos.above());

                TaskScheduler.schedule((run, self) -> {
                    Vec3 pos = startPos.add(0, 0.7 * run, 0);
                    BlockPos blockPos = BlockPos.containing(pos);
                    BlockState state = level.getBlockState(blockPos);

                    if (state.getCollisionShape(level, blockPos).isEmpty()){
                        double randomOffset = Math.pow(run, 2.1) * 0.001;
                        serverLevel.sendParticles(
                                new DustParticleOptions(0xaa3a14, level.getRandom().nextFloat() + 2.5f),
                                true, true,
                                pos.x, pos.y, pos.z,
                                7 + (int)(18 * randomOffset), randomOffset, 0.3, randomOffset, 0
                        );

                        AABB box = AABB.ofSize(pos, randomOffset * 2 + 0.3, 0.2, randomOffset * 2 + 0.3);

                        List<Entity> entities =  level.getEntities(null, box);
                        for (Entity entity : entities) {
                            entity.push(0, 0.2, 0);
                            if (entity instanceof ServerPlayer player) player.connection.send(new ClientboundSetEntityMotionPacket(player));
                        }
                    } else {
                        TaskScheduler.remove(self);
                    }

                }, 1, 25, true, null);
            }
        }
    }
}
