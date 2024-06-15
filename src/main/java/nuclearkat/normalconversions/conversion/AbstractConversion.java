package nuclearkat.normalconversions.conversion;

import org.bukkit.entity.Player;

public abstract class AbstractConversion {

    public abstract void handleInteraction(int clickedSlot, Player player);

    public abstract void calculateCost(Player player, int amount);

    public abstract void handleConversion(Player player, int amount);

}
