package nuclearkat.normalconversions.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Button {

    private final Material materialType;
    private final String displayName;
    private final List<String> lore;

    public Button(Material materialType, String displayName, String... lore) {
        this.materialType = materialType;
        this.displayName = displayName;
        this.lore = new ArrayList<>();
        for (String string : lore){
            this.lore.add(ChatColor.translateAlternateColorCodes('&', string));
        }
    }

    public ItemStack getItemStack(){
        ItemStack stack = new ItemStack(materialType);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getLore() {
        return lore;
    }

    public Material getMaterialType() {
        return materialType;
    }

}
