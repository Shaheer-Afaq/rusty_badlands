package rusty_badlands.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import rusty_badlands.entities.RustyBallEntity;

public class RustyBallItem extends Item {
    public RustyBallItem(Properties properties) {
        super(properties
                .stacksTo(16)
                .useCooldown(1)
        );
    }

    @Override
    public InteractionResult use(final Level level, final Player player, final InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            RustyBallEntity entity = new RustyBallEntity(player, level, stack);
            entity.setItem(stack);
            entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5f, 0.1f);
            level.addFreshEntity(entity);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.PLAYERS);
        }
        if (!player.getAbilities().instabuild) stack.shrink(1);
        return InteractionResult.SUCCESS;
    }

}
