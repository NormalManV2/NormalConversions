package nuclearkat.normalconversions.currency;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Apple extends Currency {

	private static Object Currency = null;
	private static Method getBalance = null;
	private static Method addBalance = null;
	private static Method removeBalance = null;

	static {
		coinsEngineLoadReflection();
	}

	Apple() {
		super(Material.APPLE, "Apple");
	}

	@Override
	public double getPlayerAmount(Player player) {
		return coinsEngineGetBalance(player);
	}

	@Override
	public void addPlayerAmount(Player player, double amount) {
		if (amount >= 0) {
			coinsEngineAddApples(player, amount);
		} else {
			coinsEngineRemoveApples(player, -amount);
		}
	}

	private static void coinsEngineLoadReflection() {
		try {
			Class<?> coinsEngineAPI = Class.forName("su.nightexpress.coinsengine.api.CoinsEngineAPI");
			Currency = coinsEngineAPI.getMethod("getCurrency").invoke(null, "ᴀᴘᴘʟᴇs");
			getBalance = coinsEngineAPI.getMethod("getBalance");
			addBalance = coinsEngineAPI.getMethod("addBalance");
			removeBalance = coinsEngineAPI.getMethod("removeBalance");
		} catch (ClassNotFoundException | InvocationTargetException |
				 IllegalAccessException | NoSuchMethodException ex) {
			ex.printStackTrace();
		}
	}

	private double coinsEngineGetBalance(Player player) {
		try {
			return (double) getBalance.invoke(null, player, Currency);
		} catch (InvocationTargetException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	private void coinsEngineAddApples(Player player, double amount) {
		try {
			addBalance.invoke(null, player, Currency, amount);
		} catch (InvocationTargetException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}

	private void coinsEngineRemoveApples(Player player, double amount) {
		try {
			removeBalance.invoke(null, player, Currency, amount);
		} catch (InvocationTargetException | IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}
}
