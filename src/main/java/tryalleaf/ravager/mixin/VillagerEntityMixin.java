package tryalleaf.ravager.mixin;

import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tryalleaf.ravager.power.RavagerPowers;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {

  @Inject(method = "interactMob", at = @At(value = "INVOKE"), cancellable = true)
  public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
    if (RavagerPowers.ENEMY_OF_THE_STATE.isActive(player)) {
      cir.setReturnValue(ActionResult.FAIL);
    }
  }
}
