package ravager.power;

import io.github.apace100.origins.power.Power;
import io.github.apace100.origins.power.PowerType;
import io.github.apace100.origins.power.PowerTypeReference;
import net.minecraft.util.Identifier;

public class RavagerPowers {
  public static final PowerType<Power> ENEMY_OF_THE_STATE =
      new PowerTypeReference<>(new Identifier("ravager", "enemy_of_the_state"));
  public static final PowerType<Power> HEAVYWEIGHT =
      new PowerTypeReference<>(new Identifier("ravager", "heavyweight"));
  public static final PowerType<Power> NO_OFFHAND =
      new PowerTypeReference<>(new Identifier("ravager", "no_offhand"));
}
