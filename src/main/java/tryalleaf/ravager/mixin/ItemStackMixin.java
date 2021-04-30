package tryalleaf.ravager.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tryalleaf.ravager.power.RavagerPowers;

@Mixin(ItemStack.class)
public class ItemStackMixin {

  @Inject(method = "use", at = @At("HEAD"), cancellable = true)
  public void preventOffHandUse(
      World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> info) {
    if (user != null && RavagerPowers.NO_OFFHAND.isActive(user) && hand.equals(Hand.OFF_HAND)) {
      info.setReturnValue(TypedActionResult.fail(user.getStackInHand(hand)));
    }
  }
}
