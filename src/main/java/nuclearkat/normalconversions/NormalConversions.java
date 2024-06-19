package nuclearkat.normalconversions;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.command.ConversionCommand;
import nuclearkat.normalconversions.command.UpdateRatesCommand;
import nuclearkat.normalconversions.conversion.ConversionManager;
import nuclearkat.normalconversions.inventories.InventoryManager;
import nuclearkat.normalconversions.listeners.ChooseConversionMenuListener;
import nuclearkat.normalconversions.conversion.ConversionRates;
import nuclearkat.normalconversions.listeners.ConversionListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class NormalConversions extends JavaPlugin {

    private static Economy ECON;
    private static ConversionRates CONVERSION_RATES;
    private static ConversionManager CONVERSION_MANAGER;
    private static InventoryManager INVENTORY_MANAGER;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.initializeRates();
        this.registerCommands();
        this.registerListeners();
    }

    @Override
    public void onDisable() {
    }

    private void initializeRates(){
        CONVERSION_RATES = new ConversionRates(this);
        INVENTORY_MANAGER = new InventoryManager();
        CONVERSION_MANAGER = new ConversionManager();
        getConfig().addDefault("rates.apple.sell_rate", 0.2);
        saveConfig();
    }

    private void registerCommands(){
        new ConversionCommand(INVENTORY_MANAGER);
        new UpdateRatesCommand(CONVERSION_RATES);
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new ChooseConversionMenuListener(INVENTORY_MANAGER), this);
        Bukkit.getPluginManager().registerEvents(new ConversionListener(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("Vault not found!");
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            System.out.println("Rsp not found! (econ)");
            return false;
        }
        ECON = rsp.getProvider();
        return true;
    }

    public static Economy getEconomy() {
        return ECON;
    }

    public static ConversionRates getConversionRates() {
        return CONVERSION_RATES;
    }

    public static ConversionManager getConversionManager() {
        return CONVERSION_MANAGER;
    }

    public static InventoryManager getInventoryManager() {
        return INVENTORY_MANAGER;
    }
}
