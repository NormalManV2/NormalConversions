package nuclearkat.normalconversions.inventories;

import nuclearkat.normalconversions.NormalConversions;
import nuclearkat.normalconversions.currency.Currency;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static nuclearkat.normalconversions.util.Util.f;

public enum ConversionButton {

	REMOVE_SINGLE((from, to, amount, p) ->
		new Button(Material.RED_WOOL, f("&c [-] &f: &4%s", to.getName()), f("&7Click to remove one %s from your purchase!", to.getNameLowercase()))),

	REMOVE_16((from, to, amount, p) ->
		new Button(Material.RED_WOOL, f("&c [-] &7<&c16&7> &f: &4%s", to.getName()), f("&7Click to remove 16 %s from your purchase!", to.getNameLowercasePlural()))),

	ADD_SINGLE((from, to, amount, p) ->
		new Button(Material.GREEN_WOOL, f("&a [+] &f: &4%s", to.getName()), f("&7Click to add one %s to your purchase!", to.getNameLowercase()))),

	ADD_16((from, to, amount, p) ->
		new Button(Material.GREEN_WOOL, f("&a [+] &7<&a16&7> &f: &4%s", to.getName()), f("&7Click to add 16 %s to your purchase!", to.getNameLowercasePlural()))),

	TO_AMOUNT((from, to, amount, p) ->
		new Button(to.getMaterial(), f("&4%s : &f%s", to.getName(), amount))),

	CONVERSION_RATE((from, to, amount, p) ->
		new Button(Material.ENCHANTED_BOOK, f("&c%s to %s &fConversion Rate:", from.getName(), to.getName()), f("&7[ &f1 : %.2f &7]", NormalConversions.getConversionRates().getConversionRate(from, to)))),

	BUY((from, to, amount, p) ->
		new Button(Material.GREEN_STAINED_GLASS_PANE, "&cClick To Buy!", f("&7 [ &c%s &7] ", NormalConversions.getConversionRates().getCost(from, to, amount)))),

	BALANCE((from, to, amount, p) ->
		new Button(from.getMaterial(), f("&cYour %s balance &f: &7<&f %.2f &7>", from.getName(), from.getPlayerAmount(p))));

	final ButtonSupplier buttonSupplier;

	ConversionButton(ButtonSupplier buttonSupplier) {
		this.buttonSupplier = buttonSupplier;
	}

	public Button getButton(Currency from, Currency to, double amount, Player p) {
		return buttonSupplier.get(from, to, amount, p);
	}

	@FunctionalInterface
	public interface ButtonSupplier {

		Button get(Currency from, Currency to, double amount, Player p);
	}
}
