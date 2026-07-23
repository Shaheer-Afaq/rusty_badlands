package rusty_badlands.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SupportType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.NonNull;
import rusty_badlands.blocks.ModBlocks;
import rusty_badlands.blocks.RustFragmentsBlock;
import rusty_badlands.effects.TetanusEffect;
import rusty_badlands.items.ModItems;

public class RustyBallEntity extends ThrowableItemProjectile {

    public RustyBallEntity(EntityType<RustyBallEntity> type, Level level) {
        super(type, level);
    }

    public RustyBallEntity(final LivingEntity owner, final Level level, final ItemStack itemStack) {
        super(ModEntityTypes.RUSTY_BALL, owner, level, itemStack);
    }

    @Override
    protected @NonNull Item getDefaultItem() {
        return ModItems.RUSTY_BALL;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (this.level().isClientSide()) return;
        if (result.getEntity() instanceof LivingEntity target) {
            target.hurtServer((ServerLevel) this.level(), this.damageSources().thrown(this, this.getOwner()), 0.5f);
            TetanusEffect.increaseDuration(160, target);
            this.level().playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.THORNS_HIT, SoundSource.NEUTRAL);
        }
        this.discard();
    }
    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!(this.level() instanceof ServerLevel serverLevel)) return;

        BlockPos pos = this.blockPosition();

        if (serverLevel.getRandom().nextFloat() < 0.8 &&
            (serverLevel.getBlockState(pos).isAir() || serverLevel.getBlockState(pos).canBeReplaced()) &&
            ModBlocks.RUST_FRAGMENTS.defaultBlockState().canSurvive(serverLevel, pos))
        {
            serverLevel.setBlock(pos, ModBlocks.RUST_FRAGMENTS.defaultBlockState(), Block.UPDATE_ALL);
            serverLevel.levelEvent(2001, pos, Block.getId(ModBlocks.RUST_FRAGMENTS.defaultBlockState()));
            serverLevel.playSound(null, pos, SoundEvents.LEAF_LITTER_PLACE, SoundSource.BLOCKS);
        }
        this.discard();
    }
}
