package nuclearkat.normalconversions.inventories;

import nuclearkat.normalconversions.conversion.ConversionRates;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.text.NumberFormat;

public class InventoryManager {

    private final ConversionRates conversionRates;
    private int appleAmount;
    private int expAmount;

    // Money -> Apples conversion menu buttons

    private static final Button ADD_SINGLE_APPLE_BUTTON = new Button(Material.GREEN_WOOL, "&a [+] &f: &4Apple", "&7Click to add one apple to your purchase!");
    private static final Button ADD_16_APPLES_BUTTON = new Button(Material.GREEN_WOOL, "&a [+] &7<&a16&7> &f: &4Apples", "&7Click to add 16 apples to your purchase!");
    private static final Button REMOVE_SINGLE_APPLE_BUTTON = new Button(Material.RED_WOOL, "&c [-] &f: &4Apple", "&7Click to remove one apple from your purchase!");
    private static final Button REMOVE_16_APPLES_BUTTON = new Button(Material.RED_WOOL, "&c [-] &7<&c16&7> &f: &4Apples", "&7Click to remove 16 apples to your purchase!");

    // Money -> Exp conversion menu buttons

    private static final Button REMOVE_16_LEVELS_BUTTON = new Button(Material.RED_WOOL, "&c [-] &7<&c16&7> &f: &4Exp Levels", "&7Click to remove 16 exp levels to your purchase!");
    private static final Button REMOVE_SINGLE_LEVEL_BUTTON = new Button(Material.RED_WOOL, "&c [-] &f: &4Exp Level", "&7Click to remove one exp level from your purchase!");
    private static final Button ADD_SINGLE_LEVEL_BUTTON = new Button(Material.GREEN_WOOL, "&a [+] &f: &4Exp Level", "&7Click to add one exp level to your purchase!");
    private static final Button ADD_16_LEVELS_BUTTON = new Button(Material.GREEN_WOOL, "&a [+] &7<&a16&7> &f: &4Exp Levels", "&7Click to add 16 exp levels to your purchase!");

    // Conversion menu buttons

    private static final Button BUY_APPLES_BUTTON = new Button(Material.APPLE, "&4Buy Apple Conversion", "&7Click to convert your money to apples!");
    private static final Button SELL_APPLES_BUTTON = new Button(Material.APPLE, "&4Sell Apple Conversion", "&7Click to convert your apples to money!");
    private static final Button BUY_EXP_BUTTON = new Button(Material.EXPERIENCE_BOTTLE, "&aBuy exp Conversion", "&7Click here to convert your money to exp levels!");
    private static final Button SELL_EXP_BUTTON = new Button(Material.EXPERIENCE_BOTTLE, "&aSell Exp Conversion", "&7Click here to convert your exp levels into money!");
    private static final Button CLOSE_BUTTON = new Button(Material.RED_STAINED_GLASS_PANE, "&cClose", "&7Click to close this menu!");

    // Shared buttons

    private static final Button BACK_BUTTON = new Button(Material.RED_STAINED_GLASS_PANE, "&cBack", "&7Click to go back to the previous menu!");
    private static final Button BORDER = new Button(Material.GRAY_STAINED_GLASS_PANE, " ");

    public InventoryManager(ConversionRates conversionRates){
        this.conversionRates = conversionRates;
    }

    private Inventory appleConversionMenu(int appleAmount, double cost, Player player){
        this.appleAmount = appleAmount;

        Inventory appleConversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&4Apple Conversion Menu"));
        NumberFormat numberFormat = NumberFormat.getInstance();

        Button appleAmountButton = new Button(Material.APPLE, "&4Apple : &f" + appleAmount);
        Button appleConversionRateButton = new Button(Material.ENCHANTED_BOOK, "&cApple &fConversion Rate:", "&7[ &f1 : %appleRate% &7]".replace("%appleRate%", numberFormat.format(conversionRates.getPlayerMoneyToAppleRate(player.getUniqueId()))));
        Button buyButton = new Button(Material.GREEN_STAINED_GLASS_PANE, "&cClick To Buy!", "&7 [ &c%cost% &7] ".replace("%cost%", numberFormat.format(cost)));

        appleConversionMenu.setItem(11, REMOVE_16_APPLES_BUTTON.getItemStack());
        appleConversionMenu.setItem(12, REMOVE_SINGLE_APPLE_BUTTON.getItemStack());
        appleConversionMenu.setItem(13, appleAmountButton.getItemStack());
        appleConversionMenu.setItem(14, ADD_SINGLE_APPLE_BUTTON.getItemStack());
        appleConversionMenu.setItem(15, ADD_16_APPLES_BUTTON.getItemStack());


        for (int i = 0; i < appleConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                appleConversionMenu.setItem(i, BORDER.getItemStack());
            }
        }
        appleConversionMenu.setItem(4, appleConversionRateButton.getItemStack());
        appleConversionMenu.setItem(22, buyButton.getItemStack());
        appleConversionMenu.setItem(26, BACK_BUTTON.getItemStack());
        return appleConversionMenu;
    }

    private Inventory expConversionMenu(int expAmount, double cost, Player player){
        this.expAmount = expAmount;

        Inventory expConversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cExp Conversion Menu"));
        NumberFormat numberFormat = NumberFormat.getInstance();

        Button levelAmountButton = new Button(Material.EXPERIENCE_BOTTLE, "&4Exp Levels : &f" + expAmount);
        Button conversionRateButton = new Button(Material.ENCHANTED_BOOK, "&cExp Level &fConversion Rate:", "&7[ &f1 : %levelRate% &7]".replace("%levelRate%", numberFormat.format(conversionRates.getPlayerMoneyToLevelRate(player.getUniqueId()))));
        Button buyButton = new Button(Material.GREEN_STAINED_GLASS_PANE, "&cClick To Buy!", "&7 [ &c%cost% &7] ".replace("%cost%", numberFormat.format(cost)));

        expConversionMenu.setItem(11, REMOVE_16_LEVELS_BUTTON.getItemStack());
        expConversionMenu.setItem(12, REMOVE_SINGLE_LEVEL_BUTTON.getItemStack());
        expConversionMenu.setItem(13, levelAmountButton.getItemStack());
        expConversionMenu.setItem(14, ADD_SINGLE_LEVEL_BUTTON.getItemStack());
        expConversionMenu.setItem(15, ADD_16_LEVELS_BUTTON.getItemStack());


        for (int i = 0; i < expConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                expConversionMenu.setItem(i, BORDER.getItemStack());
            }
        }
        expConversionMenu.setItem(4, conversionRateButton.getItemStack());
        expConversionMenu.setItem(22, buyButton.getItemStack());
        expConversionMenu.setItem(26, BACK_BUTTON.getItemStack());


        return expConversionMenu;
    }

    private Inventory conversionMenu(){
        Inventory conversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cConversion Menu"));

        conversionMenu.setItem(11, SELL_APPLES_BUTTON.getItemStack());
        conversionMenu.setItem(12, BUY_APPLES_BUTTON.getItemStack());
        conversionMenu.setItem(13, CLOSE_BUTTON.getItemStack());
        conversionMenu.setItem(14, BUY_EXP_BUTTON.getItemStack());
        conversionMenu.setItem(15, SELL_EXP_BUTTON.getItemStack());

        for (int i = 0; i < conversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                conversionMenu.setItem(i, BORDER.getItemStack());
            }
        }
        return conversionMenu;
    }

    public Inventory getAppleConversionMenu(int appleAmount, double cost, Player player){
        return appleConversionMenu(appleAmount, cost, player);
    }

    public Inventory getExpConversionMenu(int expAmount, double cost, Player player){
        return expConversionMenu(expAmount, cost, player);
    }

    public Inventory getConversionMenu(){
        return conversionMenu();
    }

    public int getAppleAmount(){
        return appleAmount;
    }

    public int getExpAmount(){
        return expAmount;
    }

}
