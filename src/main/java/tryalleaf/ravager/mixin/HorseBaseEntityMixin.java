package tryalleaf.ravager.mixin;

import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tryalleaf.ravager.power.RavagerPowers;

@Mixin(HorseBaseEntity.class)
public class HorseBaseEntityMixin {

  @Inject(method = "putPlayerOnBack", at = @At(value = "INVOKE"), cancellable = true)
  protected void preventRiding(PlayerEntity player, CallbackInfo ci) {
    if (RavagerPowers.HEAVYWEIGHT.isActive(player)) {
      ci.cancel();
    }
  }
}
