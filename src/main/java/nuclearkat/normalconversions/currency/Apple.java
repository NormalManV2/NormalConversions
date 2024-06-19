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
			Class<?> currencyClass = Class.forName("su.nightexpress.coinsengine.api.currency.Currency");
			Currency = coinsEngineAPI.getMethod("getCurrency", String.class).invoke(null, "APPLE");
			getBalance = coinsEngineAPI.getMethod("getBalance", Player.class, currencyClass);
			addBalance = coinsEngineAPI.getMethod("addBalance", Player.class, currencyClass, double.class);
			removeBalance = coinsEngineAPI.getMethod("removeBalance", Player.class, currencyClass, double.class);
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
