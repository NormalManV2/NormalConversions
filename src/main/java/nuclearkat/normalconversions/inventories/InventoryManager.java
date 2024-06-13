package nuclearkat.normalconversions.inventories;

import nuclearkat.normalconversions.transactions.ConversionRates;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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


        ItemStack addSingleAppleButton = new ItemStack(Material.GREEN_WOOL);
        ItemMeta addSingleAppleButtonMeta = addSingleAppleButton.getItemMeta();
        addSingleAppleButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a [+] &f: &4Apple"));
        List<String> addAppleButtonLore = new ArrayList<>();
        addAppleButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to add one apple to your purchase!"));
        addSingleAppleButtonMeta.setLore(addAppleButtonLore);
        addSingleAppleButton.setItemMeta(addSingleAppleButtonMeta);

        ItemStack add16ApplesButton = new ItemStack(Material.GREEN_WOOL);
        ItemMeta add16ApplesButtonMeta = add16ApplesButton.getItemMeta();
        add16ApplesButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a [+] &7<&a16&7> &f: &4Apples"));
        List<String> add16ApplesButtonLore = new ArrayList<>();
        add16ApplesButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to add 16 apples to your purchase!"));
        add16ApplesButtonMeta.setLore(add16ApplesButtonLore);
        add16ApplesButton.setItemMeta(add16ApplesButtonMeta);

        ItemStack removeSingleAppleButton = new ItemStack(Material.RED_WOOL);
        ItemMeta removeSingleAppleButtonMeta = removeSingleAppleButton.getItemMeta();
        removeSingleAppleButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c [-] &f: &4Apple"));
        List<String> removeSingleAppleButtonLore = new ArrayList<>();
        removeSingleAppleButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to remove one apple from your purchase!"));
        removeSingleAppleButtonMeta.setLore(removeSingleAppleButtonLore);
        removeSingleAppleButton.setItemMeta(removeSingleAppleButtonMeta);

        ItemStack remove16AppleButton = new ItemStack(Material.RED_WOOL);
        ItemMeta remove16AppleButtonMeta = remove16AppleButton.getItemMeta();
        remove16AppleButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c [-] &7<&c16&7> &f: &4Apples"));
        List<String> remove16AppleButtonLore = new ArrayList<>();
        remove16AppleButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to remove 16 apples to your purchase!"));
        remove16AppleButtonMeta.setLore(remove16AppleButtonLore);
        remove16AppleButton.setItemMeta(remove16AppleButtonMeta);

        ItemStack appleAmountButton = new ItemStack(Material.APPLE);
        ItemMeta appleAmountButtonMeta = appleAmountButton.getItemMeta();
        appleAmountButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Apple : &f" + appleAmount));
        appleAmountButton.setItemMeta(appleAmountButtonMeta);

        ItemStack appleConversionRate = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta conversionRateMeta = appleConversionRate.getItemMeta();
        conversionRateMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cApple &fConversion Rate:"));
        List<String> conversionRateLore = new ArrayList<>();
        conversionRateLore.add(ChatColor.translateAlternateColorCodes('&', "&7[ &f1 : %appleRate% &7]").replace("%appleRate%", numberFormat.format(conversionRates.getPlayerAppleRate(player.getUniqueId()))));
        conversionRateMeta.setLore(conversionRateLore);
        appleConversionRate.setItemMeta(conversionRateMeta);

        ItemStack buyButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta buyButtonMeta = buyButton.getItemMeta();
        buyButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cClick To Buy!"));
        List<String> buyButtonLore = new ArrayList<>();
        buyButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7 [ &c%cost% &7] ").replace("%cost%", numberFormat.format(cost)));
        buyButtonMeta.setLore(buyButtonLore);
        buyButton.setItemMeta(buyButtonMeta);

        ItemStack  backButton = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta backButtonMeta = backButton.getItemMeta();
        backButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cClose"));
        List<String> backButtonLore = new ArrayList<>();
        backButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to close this menu!"));
        backButtonMeta.setLore(backButtonLore);
        backButton.setItemMeta(backButtonMeta);

        ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(" ");
        border.setItemMeta(borderMeta);

        appleConversionMenu.setItem(11, remove16AppleButton);
        appleConversionMenu.setItem(12, removeSingleAppleButton);
        appleConversionMenu.setItem(13, appleAmountButton);
        appleConversionMenu.setItem(14, addSingleAppleButton);
        appleConversionMenu.setItem(15, add16ApplesButton);


        for (int i = 0; i < appleConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                appleConversionMenu.setItem(i, border);
            }
        }
        appleConversionMenu.setItem(4, appleConversionRate);
        appleConversionMenu.setItem(22, buyButton);
        appleConversionMenu.setItem(26, backButton);
        return appleConversionMenu;
    }

    private Inventory expConversionMenu(int expAmount, double cost, Player player){
        this.expAmount = expAmount;

        Inventory expConversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cExp Conversion Menu"));
        NumberFormat numberFormat = NumberFormat.getInstance();


        ItemStack addSingleLevelButton = new ItemStack(Material.GREEN_WOOL);
        ItemMeta addSingleLevelButtonMeta = addSingleLevelButton.getItemMeta();
        addSingleLevelButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a [+] &f: &4Exp Level"));
        List<String> addLevelButtonLore = new ArrayList<>();
        addLevelButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to add one exp level to your purchase!"));
        addSingleLevelButtonMeta.setLore(addLevelButtonLore);
        addSingleLevelButton.setItemMeta(addSingleLevelButtonMeta);

        ItemStack add16LevelsButton = new ItemStack(Material.GREEN_WOOL);
        ItemMeta add16LevelsButtonMeta = add16LevelsButton.getItemMeta();
        add16LevelsButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a [+] &7<&a16&7> &f: &4Exp Levels"));
        List<String> add16LevelsButtonLore = new ArrayList<>();
        add16LevelsButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to add 16 exp levels to your purchase!"));
        add16LevelsButtonMeta.setLore(add16LevelsButtonLore);
        add16LevelsButton.setItemMeta(add16LevelsButtonMeta);

        ItemStack removeSingleLevelButton = new ItemStack(Material.RED_WOOL);
        ItemMeta removeSingleLevelButtonMeta = removeSingleLevelButton.getItemMeta();
        removeSingleLevelButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c [-] &f: &4Exp Level"));
        List<String> removeSingleLevelButtonLore = new ArrayList<>();
        removeSingleLevelButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to remove one exp level from your purchase!"));
        removeSingleLevelButtonMeta.setLore(removeSingleLevelButtonLore);
        removeSingleLevelButton.setItemMeta(removeSingleLevelButtonMeta);

        ItemStack remove16LevelsButton = new ItemStack(Material.RED_WOOL);
        ItemMeta remove16LevelsButtonMeta = remove16LevelsButton.getItemMeta();
        remove16LevelsButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c [-] &7<&c16&7> &f: &4Exp Levels"));
        List<String> remove16LevelsButtonLore = new ArrayList<>();
        remove16LevelsButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to remove 16 exp levels to your purchase!"));
        remove16LevelsButtonMeta.setLore(remove16LevelsButtonLore);
        remove16LevelsButton.setItemMeta(remove16LevelsButtonMeta);

        ItemStack levelAmountButton = new ItemStack(Material.APPLE);
        ItemMeta levelAmountButtonMeta = levelAmountButton.getItemMeta();
        levelAmountButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Exp Levels : &f" + expAmount));
        levelAmountButton.setItemMeta(levelAmountButtonMeta);

        ItemStack levelConversionRate = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta conversionRateMeta = levelConversionRate.getItemMeta();
        conversionRateMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cExp Level &fConversion Rate:"));
        List<String> conversionRateLore = new ArrayList<>();
        conversionRateLore.add(ChatColor.translateAlternateColorCodes('&', "&7[ &f1 : %levelRate% &7]").replace("%levelRate%", numberFormat.format(conversionRates.getPlayerLevelRate(player.getUniqueId()))));
        conversionRateMeta.setLore(conversionRateLore);
        levelConversionRate.setItemMeta(conversionRateMeta);

        ItemStack buyButton = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta buyButtonMeta = buyButton.getItemMeta();
        buyButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cClick To Buy!"));
        List<String> buyButtonLore = new ArrayList<>();
        buyButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7 [ &c%cost% &7] ").replace("%cost%", numberFormat.format(cost)));
        buyButtonMeta.setLore(buyButtonLore);
        buyButton.setItemMeta(buyButtonMeta);

        ItemStack  backButton = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta backButtonMeta = backButton.getItemMeta();
        backButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cBack"));
        List<String> backButtonLore = new ArrayList<>();
        backButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to go back to the previous menu!"));
        backButtonMeta.setLore(backButtonLore);
        backButton.setItemMeta(backButtonMeta);

        ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(" ");
        border.setItemMeta(borderMeta);

        expConversionMenu.setItem(11, remove16LevelsButton);
        expConversionMenu.setItem(12, removeSingleLevelButton);
        expConversionMenu.setItem(13, levelAmountButton);
        expConversionMenu.setItem(14, addSingleLevelButton);
        expConversionMenu.setItem(15, add16LevelsButton);


        for (int i = 0; i < expConversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                expConversionMenu.setItem(i, border);
            }
        }
        expConversionMenu.setItem(4, levelConversionRate);
        expConversionMenu.setItem(22, buyButton);
        expConversionMenu.setItem(26, backButton);


        return expConversionMenu;
    }

    private Inventory conversionMenu(){
        Inventory conversionMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&cConversion Menu"));

        ItemStack buyApplesButton = new ItemStack(Material.APPLE);
        ItemMeta buyApplesButtonMeta = buyApplesButton.getItemMeta();
        buyApplesButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Buy Apple Conversion"));
        List<String> buyAppleButtonLore = new ArrayList<>();
        buyAppleButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to convert your money to apples!"));
        buyApplesButtonMeta.setLore(buyAppleButtonLore);
        buyApplesButton.setItemMeta(buyApplesButtonMeta);

        ItemStack sellApplesButton = new ItemStack(Material.APPLE);
        ItemMeta sellApplesButtonMeta = sellApplesButton.getItemMeta();
        sellApplesButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&4Sell Apple Conversion"));
        List<String> sellApplesButtonLore = new ArrayList<>();
        sellApplesButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to convert your money to apples!"));
        sellApplesButtonMeta.setLore(sellApplesButtonLore);
        sellApplesButton.setItemMeta(sellApplesButtonMeta);

        ItemStack expButton = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta expButtonMeta = expButton.getItemMeta();
        expButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aExp Conversion"));
        List<String> expButtonLore = new ArrayList<>();
        expButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click here to convert your money to exp levels!"));
        expButtonMeta.setLore(expButtonLore);
        expButton.setItemMeta(expButtonMeta);

        ItemStack closeButton = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta closeButtonMeta = closeButton.getItemMeta();
        closeButtonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cClose"));
        List<String> closeButtonLore = new ArrayList<>();
        closeButtonLore.add(ChatColor.translateAlternateColorCodes('&', "&7Click to close this menu!"));
        closeButtonMeta.setLore(closeButtonLore);
        closeButton.setItemMeta(closeButtonMeta);

        ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.setDisplayName(" ");
        border.setItemMeta(borderMeta);

        conversionMenu.setItem(11, sellApplesButton);
        conversionMenu.setItem(12, buyApplesButton);
        conversionMenu.setItem(13, closeButton);
        conversionMenu.setItem(14, expButton);
        for (int i = 0; i < conversionMenu.getSize(); i++){
            if (i < 10 || i > 16){
                conversionMenu.setItem(i, border);
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
