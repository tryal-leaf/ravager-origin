package tryalleaf.ravager.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tryalleaf.ravager.power.RavagerPowers;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {

  @Inject(
      method = "interactBlock",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/client/network/ClientPlayerEntity;shouldCancelInteraction()Z"),
      cancellable = true)
  public void preventBlockInteraction(
      ClientPlayerEntity player,
      ClientWorld world,
      Hand hand,
      BlockHitResult hitResult,
      CallbackInfoReturnable<ActionResult> cir) {
    if (RavagerPowers.NO_OFFHAND.isActive(player) && hand.equals(Hand.OFF_HAND)) {
      cir.setReturnValue(ActionResult.FAIL);
    }
  }
}
