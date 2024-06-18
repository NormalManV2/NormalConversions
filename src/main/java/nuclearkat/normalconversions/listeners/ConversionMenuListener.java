package nuclearkat.normalconversions.listeners;

import nuclearkat.normalconversions.inventories.InventoryManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ConversionMenuListener implements Listener {
    private final InventoryManager inventoryManager;

    public ConversionMenuListener(InventoryManager inventoryManager){
        this.inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (!event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', "&cConversion Menu"))){
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        int clickedSlot = event.getRawSlot();
        switch (clickedSlot){
            case 14 -> player.openInventory(inventoryManager.getMoneyToAppleConversionMenu(0, 0, player));
            case 12 -> player.openInventory(inventoryManager.getAppleToMoneyConversionMenu(0, 0, player));
            default -> player.closeInventory();
        }
    }

}
