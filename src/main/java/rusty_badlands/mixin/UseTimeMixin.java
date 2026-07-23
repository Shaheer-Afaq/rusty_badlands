package rusty_badlands.mixin;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rusty_badlands.ModAttachments;
import rusty_badlands.effects.ModEffects;

@Mixin(ItemStack.class)
public abstract class UseTimeMixin {

    @Shadow
    public abstract int getUseDuration(LivingEntity user);

    @Inject(method = "getUseDuration", at = @At("TAIL"), cancellable = true)
    private void modifyEatDuration(LivingEntity user, CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        ItemUseAnimation anim = stack.getUseAnimation();

        if (anim == ItemUseAnimation.EAT || anim == ItemUseAnimation.DRINK){
            if (user.hasEffect(ModEffects.TETANUS)){
                float tetanusStrength = (float) user.getEffect(ModEffects.TETANUS).getAmplifier() / 11;
                System.out.println(tetanusStrength);
                cir.setReturnValue((int) (cir.getReturnValue() * (1 + tetanusStrength * 1.7)));
            }
        }
    }

    @Inject(method = "onUseTick", at = @At("HEAD"), cancellable = true)
    private void fixSoundTiming(Level level, LivingEntity livingEntity, int ticksRemaining, CallbackInfo ci) {
        ItemStack stack = (ItemStack) (Object) this;
        Consumable consumable = stack.get(DataComponents.CONSUMABLE);
        if (consumable != null && livingEntity.hasEffect(ModEffects.TETANUS)){
            int totalDuration = this.getUseDuration(livingEntity);
            int itemUsedForTicks = totalDuration - ticksRemaining;
            int waitTicksBeforeUseEffects = (int)(ticksRemaining * 0.21875F);
            boolean isValidTime = itemUsedForTicks > waitTicksBeforeUseEffects;
            if (isValidTime && ticksRemaining % 4 == 0){
                consumable.emitParticlesAndSounds(livingEntity.getRandom(), livingEntity, stack, 5);
                ci.cancel();
            }
        }
    }
}
