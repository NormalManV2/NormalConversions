package nuclearkat.normalconversions.conversion;

import org.bukkit.entity.Player;

public interface ConversionInterface {

    void handleInteraction(int clickedSlot, Player player);

    double calculateCost(int amount);

    void handleConversion(Player player, int amount);

}
