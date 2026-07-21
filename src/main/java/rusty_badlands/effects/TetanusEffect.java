package rusty_badlands.effects;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jspecify.annotations.NonNull;
import rusty_badlands.ModAttachments;


public class TetanusEffect extends MobEffect {
    public TetanusEffect() {
        super(MobEffectCategory.HARMFUL, 0x77291b);
    }

    private static final Identifier SPEED_MODIFIER_ID = Identifier.fromNamespaceAndPath("rusty_badlands", "speed_modifier");
    private static final Identifier JUMP_MODIFIER_ID = Identifier.fromNamespaceAndPath("rusty_badlands", "jump_modifier");

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (level.getGameTime() % 20 != 0) return true;

        float tetanusStrength = entity.getAttachedOrCreate(ModAttachments.TETANUSSTRENGTH);

        AttributeInstance speedAttribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        AttributeInstance jumpAttribute = entity.getAttribute(Attributes.JUMP_STRENGTH);

        if (speedAttribute != null) {
            AttributeModifier speedModifier = new AttributeModifier(
                    SPEED_MODIFIER_ID, -tetanusStrength,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            speedAttribute.addOrUpdateTransientModifier(speedModifier);
        }
        if (jumpAttribute != null) {
            AttributeModifier jumpModifier = new AttributeModifier(
                    JUMP_MODIFIER_ID, -tetanusStrength * 0.5,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
            jumpAttribute.addOrUpdateTransientModifier(jumpModifier);
        }

        tetanusStrength += 0.04f;
        tetanusStrength = Math.clamp(tetanusStrength, 0, 0.85f);
        entity.setAttached(ModAttachments.TETANUSSTRENGTH, tetanusStrength);
        return true;
    }

    @Override
    public void onEffectRemoved(@NonNull MobEffectInstance effectInstance, LivingEntity entity) {
        entity.setAttached(ModAttachments.TETANUSSTRENGTH, 0f);
        AttributeInstance speedAttribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttribute != null) speedAttribute.removeModifier(SPEED_MODIFIER_ID);
        AttributeInstance jumpAttribute = entity.getAttribute(Attributes.JUMP_STRENGTH);
        if (jumpAttribute != null) jumpAttribute.removeModifier(JUMP_MODIFIER_ID);
    }

}
