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

    public InventoryManager(ConversionRates conversionRates){
        this.conversionRates = conversionRates;
    }

    private Inventory appleConversionMenu(int appleAmount, double cost, Player player){
        this.appleAmount = appleAmount;

        Inventory appleConversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&4Apple Conversion Menu"));
        NumberFormat numberFormat = NumberFormat.getInstance();

        Button addSingleAppleButton = new Button(Material.GREEN_WOOL, "&a [+] &f: &4Apple", "&7Click to add one apple to your purchase!");
        Button add16ApplesButton = new Button(Material.GREEN_WOOL, "&a [+] &7<&a16&7> &f: &4Apples", "&7Click to add 16 apples to your purchase!");
        Button removeSingleAppleButton = new Button(Material.RED_WOOL, "&c [-] &f: &4Apple", "&7Click to remove one apple from your purchase!");
        Button remove16ApplesButton = new Button(Material.RED_WOOL, "&c [-] &7<&c16&7> &f: &4Apples", "&7Click to remove 16 apples to your purchase!");
        Button appleAmountButton = new Button(Material.APPLE, "&4Apple : &f" + appleAmount);
        Button appleConversionRateButton = new Button(Material.ENCHANTED_BOOK, "&cApple &fConversion Rate:", "&7[ &f1 : %appleRate% &7]".replace("%appleRate%", numberFormat.format(conversionRates.getPlayerAppleRate(player.getUniqueId()))));
        Button buyButton = new Button(Material.GREEN_STAINED_GLASS_PANE, "&cClick To Buy!", "&7 [ &c%cost% &7] ".replace("%cost%", numberFormat.format(cost)));
        Button backButton = new Button(Material.RED_STAINED_GLASS_PANE, "&cBack", "&7Click to go back to the previous menu!");
        Button border = new Button(Material.GRAY_STAINED_GLASS_PANE, " ");

        appleConversionMenu.setItem(11, remove16ApplesButton.getItemStack());
        appleConversionMenu.setItem(12, removeSingleAppleButton.getItemStack());
        appleConversionMenu.setItem(13, appleAmountButton.getItemStack());
        appleConversionMenu.setItem(14, addSingleAppleButton.getItemStack());
        appleConversionMenu.setItem(15, add16ApplesButton.getItemStack());


        for (int i = 0; i < appleConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                appleConversionMenu.setItem(i, border.getItemStack());
            }
        }
        appleConversionMenu.setItem(4, appleConversionRateButton.getItemStack());
        appleConversionMenu.setItem(22, buyButton.getItemStack());
        appleConversionMenu.setItem(26, backButton.getItemStack());
        return appleConversionMenu;
    }

    private Inventory expConversionMenu(int expAmount, double cost, Player player){
        this.expAmount = expAmount;

        Inventory expConversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cExp Conversion Menu"));
        NumberFormat numberFormat = NumberFormat.getInstance();

        Button levelAmountButton = new Button(Material.EXPERIENCE_BOTTLE, "&4Exp Levels : &f" + expAmount);
        Button remove16LevelsButton = new Button(Material.RED_WOOL, "&c [-] &7<&c16&7> &f: &4Exp Levels", "&7Click to remove 16 exp levels to your purchase!");
        Button removeSingleLevelButton = new Button(Material.RED_WOOL, "&c [-] &f: &4Exp Level", "&7Click to remove one exp level from your purchase!");
        Button addSingleLevelButton = new Button(Material.GREEN_WOOL, "&a [+] &f: &4Exp Level", "&7Click to add one exp level to your purchase!");
        Button add16LevelsButton = new Button(Material.GREEN_WOOL, "&a [+] &7<&a16&7> &f: &4Exp Levels", "&7Click to add 16 exp levels to your purchase!");
        Button conversionRateButton = new Button(Material.ENCHANTED_BOOK, "&cExp Level &fConversion Rate:", "&7[ &f1 : %levelRate% &7]".replace("%levelRate%", numberFormat.format(conversionRates.getPlayerLevelRate(player.getUniqueId()))));
        Button buyButton = new Button(Material.GREEN_STAINED_GLASS_PANE, "&cClick To Buy!", "&7 [ &c%cost% &7] ".replace("%cost%", numberFormat.format(cost)));
        Button backButton = new Button(Material.RED_STAINED_GLASS_PANE, "&cBack", "&7Click to go back to the previous menu!");
        Button border = new Button(Material.GRAY_STAINED_GLASS_PANE, " ");

        expConversionMenu.setItem(11, remove16LevelsButton.getItemStack());
        expConversionMenu.setItem(12, removeSingleLevelButton.getItemStack());
        expConversionMenu.setItem(13, levelAmountButton.getItemStack());
        expConversionMenu.setItem(14, addSingleLevelButton.getItemStack());
        expConversionMenu.setItem(15, add16LevelsButton.getItemStack());


        for (int i = 0; i < expConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                expConversionMenu.setItem(i, border.getItemStack());
            }
        }
        expConversionMenu.setItem(4, conversionRateButton.getItemStack());
        expConversionMenu.setItem(22, buyButton.getItemStack());
        expConversionMenu.setItem(26, backButton.getItemStack());


        return expConversionMenu;
    }

    private Inventory conversionMenu(){
        Inventory conversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cConversion Menu"));

        Button buyApplesButton = new Button(Material.APPLE, "&4Buy Apple Conversion", "&7Click to convert your money to apples!");
        Button sellApplesButton = new Button(Material.APPLE, "&4Sell Apple Conversion", "&7Click to convert your apples to money!");
        Button buyExpButton = new Button(Material.EXPERIENCE_BOTTLE, "&aBuy exp Conversion", "&7Click here to convert your money to exp levels!");
        Button sellExpButton = new Button(Material.EXPERIENCE_BOTTLE, "&aSell Exp Conversion", "&7Click here to convert your exp levels into money!");
        Button closeButton = new Button(Material.RED_STAINED_GLASS_PANE, "&cClose", "&7Click to close this menu!");
        Button border = new Button(Material.GRAY_STAINED_GLASS_PANE, " ");

        conversionMenu.setItem(11, sellApplesButton.getItemStack());
        conversionMenu.setItem(12, buyApplesButton.getItemStack());
        conversionMenu.setItem(13, closeButton.getItemStack());
        conversionMenu.setItem(14, buyExpButton.getItemStack());
        conversionMenu.setItem(15, sellExpButton.getItemStack());

        for (int i = 0; i < conversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                conversionMenu.setItem(i, border.getItemStack());
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
