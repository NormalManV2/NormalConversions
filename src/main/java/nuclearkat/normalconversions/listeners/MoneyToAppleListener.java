package nuclearkat.normalconversions.listeners;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.NormalConversions;
import nuclearkat.normalconversions.conversion.MoneyToAppleConversion;
import nuclearkat.normalconversions.inventories.InventoryManager;
import nuclearkat.normalconversions.conversion.ConversionRates;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MoneyToAppleListener implements Listener {

    private final Map<UUID, Double> playerBalance = new HashMap<>();
    private final MoneyToAppleConversion moneyToAppleConversion;

    public MoneyToAppleListener(InventoryManager inventoryManager, ConversionRates conversionRates){
        this.moneyToAppleConversion = new MoneyToAppleConversion(inventoryManager, conversionRates);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event){
        if (!event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&4Buy Apple Menu"))){
            return;
        }
        Player player = (Player) event.getPlayer();
        Economy economy = NormalConversions.getEconomy();
        this.playerBalance.put(player.getUniqueId(), economy.getBalance(player));
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&4Buy Apple Menu"))){
            return;
        }
        event.setCancelled(true);

        Economy economy = NormalConversions.getEconomy();
        Player player = (Player) event.getWhoClicked();

        playerBalance.putIfAbsent(player.getUniqueId(), economy.getBalance(player));
        double cachedBalance = this.playerBalance.get(player.getUniqueId());
        double currentBalance = economy.getBalance(player);

        if (cachedBalance != currentBalance){
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYour balance has changed during this conversion, please try again!"));
            return;
        }

        moneyToAppleConversion.handleInteraction(event.getRawSlot(), player);
    }
}
