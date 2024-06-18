package nuclearkat.normalconversions.inventories;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.NormalConversions;
import nuclearkat.normalconversions.conversion.ConversionRates;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.text.NumberFormat;

public class InventoryManager {

    private final ConversionRates conversionRates;

    // Conversion menu buttons

    private static final Button BUY_APPLES_BUTTON = new Button(Material.APPLE, "&4Buy Apple Conversion", "&7Click to convert your money to apples!");
    private static final Button SELL_APPLES_BUTTON = new Button(Material.APPLE, "&4Sell Apple Conversion", "&7Click to convert your apples to money!");
    private static final Button CLOSE_BUTTON = new Button(Material.RED_STAINED_GLASS_PANE, "&cClose", "&7Click to close this menu!");

    // Shared buttons

    private static final Button BACK_BUTTON = new Button(Material.RED_STAINED_GLASS_PANE, "&cBack", "&7Click to go back to the previous menu!");
    private static final Button BORDER = new Button(Material.GRAY_STAINED_GLASS_PANE, " ");
    private static final Button REMOVE_SINGLE_APPLE_BUTTON = new Button(Material.RED_WOOL, "&c [-] &f: &4Apple", "&7Click to remove one apple from your purchase!");
    private static final Button REMOVE_16_APPLES_BUTTON = new Button(Material.RED_WOOL, "&c [-] &7<&c16&7> &f: &4Apples", "&7Click to remove 16 apples from your purchase!");
    private static final Button ADD_SINGLE_APPLE_BUTTON = new Button(Material.GREEN_WOOL, "&a [+] &f: &4Apple", "&7Click to add one apple to your purchase!");
    private static final Button ADD_16_APPLES_BUTTON = new Button(Material.GREEN_WOOL, "&a [+] &7<&a16&7> &f: &4Apples", "&7Click to add 16 apples to your purchase!");

    public InventoryManager(ConversionRates conversionRates){
        this.conversionRates = conversionRates;
    }

    /**
     * Money To Apple Conversion Menu
     *
     * @param appleAmount The amount of apples to display.
     * @param cost The cost of how many apples.
     * @param player We get the player here to display their balance.
     * @return Returns the created inventory.
     */
    private Inventory moneyToAppleConversionMenu(int appleAmount, double cost, Player player){

        Inventory appleConversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&4Buy Apple Menu"));
        NumberFormat numberFormat = NumberFormat.getInstance();
        Economy economy = NormalConversions.getEconomy();

        Button appleAmountButton = new Button(Material.APPLE, "&4Apple : &f" + appleAmount);
        Button appleConversionRateButton = new Button(Material.ENCHANTED_BOOK, "&cApple &fConversion Rate:", "&7[ &f1 : %appleRate% &7]".replace("%appleRate%", numberFormat.format(conversionRates.getMoneyToAppleConversionRate())));
        Button buyButton = new Button(Material.GREEN_STAINED_GLASS_PANE, "&cClick To Buy!", "&7 [ &c%cost% &7] ".replace("%cost%", numberFormat.format(cost)));
        String playerBalance = "&cYour balance &f: &7<&f %playerBalance% &7>".replace("%playerBalance", String.valueOf(economy.getBalance(player)));
        Button playerBalanceButton = new Button(Material.PAPER, ChatColor.translateAlternateColorCodes('&', playerBalance));

        appleConversionMenu.setItem(11, REMOVE_16_APPLES_BUTTON.getItemStack());
        appleConversionMenu.setItem(12, REMOVE_SINGLE_APPLE_BUTTON.getItemStack());
        appleConversionMenu.setItem(13, appleAmountButton.getItemStack());
        appleConversionMenu.setItem(14, ADD_SINGLE_APPLE_BUTTON.getItemStack());
        appleConversionMenu.setItem(15, ADD_16_APPLES_BUTTON.getItemStack());

        // Gives us a border like appearance surrounding the other buttons.
        for (int i = 0; i < appleConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                appleConversionMenu.setItem(i, BORDER.getItemStack());
            }
        }

        // Set the buttons after the border buttons are placed, as these buttons are placed in spots of borders.
        appleConversionMenu.setItem(4, appleConversionRateButton.getItemStack());
        appleConversionMenu.setItem(22, buyButton.getItemStack());
        appleConversionMenu.setItem(26, BACK_BUTTON.getItemStack());
        appleConversionMenu.setItem(8, playerBalanceButton.getItemStack());
        return appleConversionMenu;
    }

    /**
     * Apple To Money Conversion Menu
     *
     * @param moneyAmount The amount of money to display.
     * @param cost The cost in apples of this money.
     * @param player We get the player here to display their balance.
     * @return Returns the created inventory.
     */
    private Inventory appleToMoneyConversionMenu(int moneyAmount, double cost, Player player){

        Inventory appleConversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&4Sell Apples Menu"));
        NumberFormat numberFormat = NumberFormat.getInstance();
        Economy economy = NormalConversions.getEconomy();

        Button moneyAmountButton = new Button(Material.IRON_INGOT, "&4Money : &f" + moneyAmount);
        Button moneyConversionRateButton = new Button(Material.ENCHANTED_BOOK, "&cApple &fConversion Rate:", "&7[ &f1 : %appleRate% &7]".replace("%appleRate%", numberFormat.format(conversionRates.getAppleToMoneyConversionRate())));
        Button buyButton = new Button(Material.GREEN_STAINED_GLASS_PANE, "&cClick To Buy!", "&7 [ &c%cost% &7] ".replace("%cost%", numberFormat.format(cost)));
        String playerBalance = "&cYour balance &f: &7<&f %playerBalance% &7>".replace("%playerBalance", String.valueOf(economy.getBalance(player)));
        Button playerBalanceButton = new Button(Material.PAPER, ChatColor.translateAlternateColorCodes('&', playerBalance));

        appleConversionMenu.setItem(11, REMOVE_16_APPLES_BUTTON.getItemStack());
        appleConversionMenu.setItem(12, REMOVE_SINGLE_APPLE_BUTTON.getItemStack());
        appleConversionMenu.setItem(13, moneyAmountButton.getItemStack());
        appleConversionMenu.setItem(14, ADD_SINGLE_APPLE_BUTTON.getItemStack());
        appleConversionMenu.setItem(15, ADD_16_APPLES_BUTTON.getItemStack());

        // Gives us a border like appearance surrounding the other buttons.
        for (int i = 0; i < appleConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                appleConversionMenu.setItem(i, BORDER.getItemStack());
            }
        }

        // Set the buttons after the border buttons are placed, as these buttons are placed in spots of borders.
        appleConversionMenu.setItem(4, moneyConversionRateButton.getItemStack());
        appleConversionMenu.setItem(22, buyButton.getItemStack());
        appleConversionMenu.setItem(26, BACK_BUTTON.getItemStack());
        appleConversionMenu.setItem(8, playerBalanceButton.getItemStack());

        return appleConversionMenu;
    }

    /**
     * Conversion Menu
     * Utility inventory to hold the conversion options.
     * @return Returns the created inventory.
     */
    private Inventory conversionMenu(){
        Inventory conversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cConversion Menu"));

        conversionMenu.setItem(12, SELL_APPLES_BUTTON.getItemStack());
        conversionMenu.setItem(13, CLOSE_BUTTON.getItemStack());
        conversionMenu.setItem(14, BUY_APPLES_BUTTON.getItemStack());
        // Gives us a border like appearance surrounding the other buttons.
        for (int i = 0; i < conversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                conversionMenu.setItem(i, BORDER.getItemStack());
            }
        }
        return conversionMenu;
    }

    public Inventory getMoneyToAppleConversionMenu(int appleAmount, double cost, Player player){
        return moneyToAppleConversionMenu(appleAmount, cost, player);
    }
    public Inventory getAppleToMoneyConversionMenu(int moneyAmount, double cost, Player player){
        return appleToMoneyConversionMenu(moneyAmount, cost, player);
    }

    public Inventory getConversionMenu(){
        return conversionMenu();
    }

}
