package nuclearkat.normalconversions.conversion;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.NormalConversions;
import nuclearkat.normalconversions.inventories.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;

public class AppleToMoneyConversion implements ConversionInterface {

    private final InventoryManager inventoryManager;
    private final ConversionRates conversionRates;
    private int moneyAmount;

    public AppleToMoneyConversion(InventoryManager inventoryManager, ConversionRates conversionRates){
        this.inventoryManager = inventoryManager;
        this.conversionRates = conversionRates;
    }

    @Override
    public void handleInteraction(int clickedSlot, Player player) {
        int moneyAmount = this.moneyAmount;

        switch (clickedSlot){
            case 11 -> {
                if (moneyAmount - 16 < 0){
                    return;
                }
                moneyAmount -= 16;
                player.openInventory(inventoryManager.getMoneyToAppleConversionMenu(moneyAmount, calculateCost(moneyAmount), player));
            }
            case 12 -> {
                if (moneyAmount == 0){
                    return;
                }
                moneyAmount -= 1;
                player.openInventory(inventoryManager.getMoneyToAppleConversionMenu(moneyAmount, calculateCost(moneyAmount), player));
            }
            case 14 ->{
                moneyAmount += 1;
                player.openInventory(inventoryManager.getMoneyToAppleConversionMenu(moneyAmount, calculateCost(moneyAmount), player));
            }
            case 15 -> {
                moneyAmount += 16;
                player.openInventory(inventoryManager.getMoneyToAppleConversionMenu(moneyAmount, calculateCost(moneyAmount), player));
            }
            case 22 -> {
                if (moneyAmount == 0){
                    return;
                }
                handleConversion(player, moneyAmount);
            }
            case 26 -> player.openInventory(inventoryManager.getConversionMenu());
        }
    }

    @Override
    public double calculateCost(int amount) {
        double rate = conversionRates.getMoneyToAppleConversionRate();
        return rate * amount;
    }

    @Override
    public void handleConversion(Player player, int amount) {
        Economy economy = NormalConversions.getEconomy();
        double cost = calculateCost(amount);
        double currentBalance = economy.getBalance(player);
        NumberFormat numberFormat = NumberFormat.getInstance();

        if (currentBalance < cost){
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou do not have enough money for this conversion!"));
            return;
        }

        economy.depositPlayer(player, cost);

        CommandSender sender = Bukkit.getConsoleSender();
        String command = "eco give %player% %amount%".replace("%player%", player.getDisplayName()).replace("%amount%", String.valueOf(amount));
        Bukkit.dispatchCommand(sender, command);
        player.closeInventory();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConverted &f" + numberFormat.format(cost) + " money to " + amount + "&c apples!"));

    }
}
