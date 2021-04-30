package tryalleaf.ravager.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tryalleaf.ravager.power.RavagerPowers;

@Mixin(BlockItem.class)
public class BlockItemMixin {

  @Inject(
      method = "useOnBlock",
      at = @At(
          value = "INVOKE",
          target = "Lnet/minecraft/item/BlockItem;use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;"),
      cancellable = true)
  public void preventOffHandUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
    PlayerEntity user = context.getPlayer();
    Hand hand = context.getHand();
    if (user != null && RavagerPowers.NO_OFFHAND.isActive(user) && hand.equals(Hand.OFF_HAND)) {
      cir.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)).getResult());
    }
  }
}
