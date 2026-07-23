package rusty_badlands.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import rusty_badlands.ModAttachments;
import rusty_badlands.effects.ModEffects;

@Mixin(LivingEntity.class)
public abstract class ConsumeTimeMixin {

    @Shadow
    protected ItemStack useItem;

    @Shadow
    protected int useItemRemaining;

    @Inject(method = "startUsingItem", at = @At("TAIL"))
    private void modifyEatDuration(InteractionHand hand, CallbackInfo ci) {
        ItemUseAnimation anim = this.useItem.getUseAnimation();
        if (anim == ItemUseAnimation.EAT || anim == ItemUseAnimation.DRINK){
            LivingEntity entity = (LivingEntity) (Object) this;
            if (entity.hasEffect(ModEffects.TETANUS)){
                float tetanusStrength = (float) entity.getEffect(ModEffects.TETANUS).getAmplifier() / 50;                System.out.println(tetanusStrength);
                this.useItemRemaining = (int) (this.useItemRemaining * (1 + tetanusStrength * 1.5));
            }
        }
    }
}
