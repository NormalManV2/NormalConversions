package nuclearkat.normalconversions.currency;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.NormalConversions;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Money extends Currency {

	Money() {
		super(Material.IRON_INGOT, "Money");
	}

	@Override
	public double getPlayerAmount(Player player) {
		Economy economy = NormalConversions.getEconomy();
		return economy.getBalance(player);
	}

	@Override
	public void addPlayerAmount(Player player, double amount) {
		Economy economy = NormalConversions.getEconomy();
		if (amount >= 0) {
			economy.depositPlayer(player, amount);
			return;
		}
		economy.withdrawPlayer(player, -amount);
	}
}
