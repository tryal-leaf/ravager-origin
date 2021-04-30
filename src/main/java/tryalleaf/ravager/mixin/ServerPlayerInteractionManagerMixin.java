package tryalleaf.ravager.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tryalleaf.ravager.power.RavagerPowers;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {

  @Inject(
      method = "interactBlock",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/server/network/ServerPlayerEntity;shouldCancelInteraction()Z"),
      cancellable = true)
  public void preventBlockInteraction(
      ServerPlayerEntity player,
      World world,
      ItemStack stack,
      Hand hand,
      BlockHitResult hitResult,
      CallbackInfoReturnable<ActionResult> cir) {
    if (RavagerPowers.NO_OFFHAND.isActive(player) && hand.equals(Hand.OFF_HAND)) {
      cir.setReturnValue(ActionResult.FAIL);
    }
  }
}
