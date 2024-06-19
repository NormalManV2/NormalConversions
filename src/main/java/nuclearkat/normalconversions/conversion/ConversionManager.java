package nuclearkat.normalconversions.conversion;

import nuclearkat.normalconversions.currency.Currency;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConversionManager {

	private final Map<UUID, PlayerConversion> playerStates = new HashMap<>();

	public PlayerConversion getPlayerConversion(UUID player) {
		if (!playerStates.containsKey(player)) {
			playerStates.put(player, new PlayerConversion(player));
		}
		return playerStates.get(player);
	}

	public void setState(UUID player, Currency from, Currency to, int amount) {
		getPlayerConversion(player).update(from, to, amount);
	}
}
