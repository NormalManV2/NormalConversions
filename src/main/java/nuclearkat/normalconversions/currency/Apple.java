package nuclearkat.normalconversions.currency;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Apple extends Currency {

	Apple() {
		super(Material.APPLE, "Apple");
	}

	@Override
	public double getPlayerAmount(Player player) {
		return 0; // TODO Implement this!
	}

	@Override
	public void addPlayerAmount(Player player, double amount) {
		CommandSender sender = Bukkit.getConsoleSender();
		if (amount < 0) {
			String command = String.format("apple take %s %.2f", player.getDisplayName(), amount);
			Bukkit.dispatchCommand(sender, command);
		} else {
			// TODO Implement this properly!
		}

	}
}
