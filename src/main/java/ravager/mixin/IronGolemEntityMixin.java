package ravager.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ravager.power.RavagerPowers;

@Mixin(IronGolemEntity.class)
public abstract class IronGolemEntityMixin extends GolemEntity {

  public IronGolemEntityMixin(EntityType<? extends IronGolemEntity> type, World world) {
    super(type, world);
  }

  @Inject(method = "initGoals", at = @At("HEAD"), cancellable = true)
  protected void initGoals(CallbackInfo ci) {
    targetSelector.add(
        /* priority */ 3,
        new FollowTargetGoal<>(
            /* mob */ this,
            PlayerEntity.class,
            /* reciprocalChance */ 5,
            /* checkVisibility */ true,
            /* checkCanNavigate */ false,
            RavagerPowers.ENEMY_OF_THE_STATE::isActive));
  }
}
