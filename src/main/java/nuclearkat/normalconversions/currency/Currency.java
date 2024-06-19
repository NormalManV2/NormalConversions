package nuclearkat.normalconversions.currency;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public abstract class Currency {

		Material material;
		String name;

		public Currency(Material material, String name) {
			this.material = material;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public String getNameLowercase() {
			return name.toLowerCase();
		}

		public String getNameLowercasePlural() {
			return name.toLowerCase() + "s";
		}

		public Material getMaterial() {
			return material;
		}

		public abstract double getPlayerAmount(Player player);

		public abstract void addPlayerAmount(Player player, double amount);

}
