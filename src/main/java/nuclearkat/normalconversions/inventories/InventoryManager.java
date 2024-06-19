package nuclearkat.normalconversions.inventories;

import nuclearkat.normalconversions.conversion.PlayerConversion;
import nuclearkat.normalconversions.currency.Currency;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import static nuclearkat.normalconversions.util.Util.f;

public class InventoryManager {

    private static final Button BUY_APPLES_BUTTON = new Button(Material.APPLE, "&4Buy Apple Conversion",
        "&7Click to convert your money to apples!");
    private static final Button SELL_APPLES_BUTTON = new Button(Material.APPLE, "&4Sell Apple Conversion",
        "&7Click to convert your apples to money!");
    private static final Button CLOSE_BUTTON = new Button(Material.RED_STAINED_GLASS_PANE, "&cClose",
        "&7Click to close this menu!");
    private static final Button BACK_BUTTON = new Button(Material.RED_STAINED_GLASS_PANE, "&cBack",
        "&7Click to go back to the previous menu!");
    private static final Button BORDER_BUTTON = new Button(Material.GRAY_STAINED_GLASS_PANE, " ");

    /**
     * Conversion Menu
     *
     * @param p We get the player here to display their balance.
     * @param conversion The conversion object containing the from and to currencies, and conversion amount.
     * @return Returns the created inventory.
     */
    private Inventory getConversionMenu(Player p, PlayerConversion conversion) {
        Currency from = conversion.getFrom();
        Currency to = conversion.getTo();
        int amount = conversion.getAmount();

        Inventory conversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', f("&4Convert %s to %s Menu", from.getName(), to.getName())));

        conversionMenu.setItem(11, ConversionButton.REMOVE_16.getButton(from, to, amount, p).getItemStack());
        conversionMenu.setItem(12, ConversionButton.REMOVE_SINGLE.getButton(from, to, amount, p).getItemStack());
        conversionMenu.setItem(13, ConversionButton.TO_AMOUNT.getButton(from, to, amount, p).getItemStack());
        conversionMenu.setItem(14, ConversionButton.ADD_SINGLE.getButton(from, to, amount, p).getItemStack());
        conversionMenu.setItem(15, ConversionButton.ADD_16.getButton(from, to, amount, p).getItemStack());

        // Gives us a border like appearance surrounding the other buttons.
        for (int i = 0; i < conversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                conversionMenu.setItem(i, BORDER_BUTTON.getItemStack());
            }
        }

        // Set the buttons after the border buttons are placed, as these buttons are placed in spots of borders.
        conversionMenu.setItem(4, ConversionButton.CONVERSION_RATE.getButton(from, to, amount, p).getItemStack());
        conversionMenu.setItem(22, ConversionButton.BUY.getButton(from, to, amount, p).getItemStack());
        conversionMenu.setItem(26, BACK_BUTTON.getItemStack());
        conversionMenu.setItem(8, ConversionButton.BALANCE.getButton(from, to, amount, p).getItemStack());
        return conversionMenu;
    }

    public void openConversionMenu(Player p, PlayerConversion conversion) {
        p.openInventory(getConversionMenu(p, conversion));
    }

    /**
     * Conversion Menu
     * Utility inventory to hold the conversion options.
     * @return Returns the created inventory.
     */
    private Inventory chooseConversionMenu(){
        Inventory conversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cConversion Menu"));

        conversionMenu.setItem(12, SELL_APPLES_BUTTON.getItemStack());
        conversionMenu.setItem(13, CLOSE_BUTTON.getItemStack());
        conversionMenu.setItem(14, BUY_APPLES_BUTTON.getItemStack());
        // Gives us a border like appearance surrounding the other buttons.
        for (int i = 0; i < conversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                conversionMenu.setItem(i, BORDER_BUTTON.getItemStack());
            }
        }
        return conversionMenu;
    }

    public void openChooseConversionMenu(Player p){
        p.openInventory(chooseConversionMenu());
    }
}
