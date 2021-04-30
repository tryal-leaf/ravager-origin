package tryalleaf.ravager.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {

  private static final String TEAM_RAVAGER = "ravager";

  @Shadow public abstract ServerScoreboard getScoreboard();

  @Inject(
      method = "addEntity",
      at = @At(
          value = "TAIL",
          target = "Lnet/minecraft/world/chunk/Chunk;addEntity(Lnet/minecraft/entity/Entity;)V"))
  public void addIllagerToTeamRavager(Entity entity, CallbackInfoReturnable<Boolean> cir) {
    if (isIllager(entity.getType()) && getScoreboard().getPlayerTeam(entity.getEntityName()) == null) {
      getScoreboard().addPlayerToTeam(entity.getEntityName(), getScoreboard().getTeam(TEAM_RAVAGER));
    }
  }

  private boolean isIllager(EntityType<?> entityType) {
    return entityType.equals(EntityType.PILLAGER)
        || entityType.equals(EntityType.EVOKER)
        || entityType.equals(EntityType.VINDICATOR)
        || entityType.equals(EntityType.RAVAGER)
        || entityType.equals(EntityType.VEX)
        || entityType.equals(EntityType.WITCH);
  }
}
