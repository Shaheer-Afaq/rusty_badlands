package rusty_badlands.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import rusty_badlands.utils.TaskScheduler;

import java.util.List;

public class RustyCoreBlock extends Block {
    public RustyCoreBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock())) {
            scheduleNextEruption(level, pos);
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        erupt(level, pos);
        scheduleNextEruption(level, pos);
    }

    private void scheduleNextEruption(Level level, BlockPos pos) {
        int delay = 60 + level.getRandom().nextInt(100);
        System.out.println(delay);
        level.scheduleTick(pos, this, delay);
    }

    private void erupt(ServerLevel level, BlockPos myPos) {
        if (level.getBlockState(myPos.above()).getBlock() == Blocks.WATER) {
            Vec3 startPos = Vec3.atCenterOf(myPos.above());

            TaskScheduler.schedule((run, self) -> {
                Vec3 pos = startPos.add(0, 0.4 * run, 0);
                BlockPos blockPos = BlockPos.containing(pos);
                BlockState state = level.getBlockState(blockPos);

                if (state.getCollisionShape(level, blockPos).isEmpty()){
                    double randomOffset = Math.pow(run, 2.2) * 0.001;
                    level.sendParticles(
                        new DustParticleOptions(0xaa3a14, level.getRandom().nextFloat() + 1.5f),
                        pos.x, pos.y, pos.z, 10 + (int)(24 * randomOffset), randomOffset, 0.1, randomOffset, 0);
                    AABB box = AABB.ofSize(pos, randomOffset * 2 + 0.3, 0.2, randomOffset * 2 + 0.3);

                    List<Entity> entities =  level.getEntities(null, box);
                    for (Entity entity : entities) {
                        entity.push(0, 0.2, 0);
                        if (entity instanceof ServerPlayer player) player.connection.send(new ClientboundSetEntityMotionPacket(player));
                    }
                } else {
                    TaskScheduler.remove(self);
                }

            }, 1, 20, true, null);
        }
    }
}

