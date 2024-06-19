package nuclearkat.normalconversions.listeners;

import nuclearkat.normalconversions.NormalConversions;
import nuclearkat.normalconversions.conversion.ConversionManager;
import nuclearkat.normalconversions.currency.Currencies;
import nuclearkat.normalconversions.inventories.InventoryManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ChooseConversionMenuListener implements Listener {
    private final InventoryManager inventoryManager;

    public ChooseConversionMenuListener(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&cConversion Menu"))){
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        ConversionManager conversionManager = NormalConversions.getConversionManager();

        int clickedSlot = event.getRawSlot();
        switch (clickedSlot){
            case 12 -> {
                conversionManager.setState(player.getUniqueId(), Currencies.APPLE, Currencies.MONEY, 0);
                inventoryManager.openConversionMenu(player, conversionManager.getPlayerConversion(player.getUniqueId()));
            }
            case 14 -> {
                conversionManager.setState(player.getUniqueId(), Currencies.MONEY, Currencies.APPLE, 0);
                inventoryManager.openConversionMenu(player, conversionManager.getPlayerConversion(player.getUniqueId()));
            }
            default -> player.closeInventory();
        }
    }

}
