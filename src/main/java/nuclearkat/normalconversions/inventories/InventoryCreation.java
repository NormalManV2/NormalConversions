package nuclearkat.normalconversions.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryCreation {

    private final InventoryHolder holder;
    private final int size;
    private final String title;

    public InventoryCreation(InventoryHolder holder, int size, String title){
        this.holder = holder;
        this.size = size;
        this.title = title;
    }

    public Inventory getInventory(){
        Inventory createdInventory = Bukkit.createInventory(holder, size, ChatColor.translateAlternateColorCodes('&', title));


        return createdInventory;
    }

}
