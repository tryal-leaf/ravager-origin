package tryalleaf.ravager.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tryalleaf.ravager.power.RavagerPowers;

@Mixin(PigEntity.class)
public class PigEntityMixin {

  @Redirect(
      method = "interactMob",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/entity/player/PlayerEntity;startRiding(Lnet/minecraft/entity/Entity;)Z",
          ordinal = 0))
  public boolean startRiding(PlayerEntity player, Entity entity) {
    if (RavagerPowers.HEAVYWEIGHT.isActive(player)) {
      return false;
    }
    return player.startRiding(entity);
  }
}
