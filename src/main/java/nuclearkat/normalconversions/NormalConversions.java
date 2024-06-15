package nuclearkat.normalconversions;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.command.ConversionCommand;
import nuclearkat.normalconversions.command.UpdateRatesCommand;
import nuclearkat.normalconversions.inventories.InventoryManager;
import nuclearkat.normalconversions.listeners.AppleConversionListener;
import nuclearkat.normalconversions.listeners.ConversionMenuListener;
import nuclearkat.normalconversions.listeners.ExpConversionListener;
import nuclearkat.normalconversions.conversion.ConversionRates;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class NormalConversions extends JavaPlugin {

    private static Economy ECON;
    private ConversionRates conversionRates;
    private InventoryManager inventoryManager;

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
        this.conversionRates = new ConversionRates(this);
        this.inventoryManager = new InventoryManager(conversionRates);
        getConfig().addDefault("rates.apple.rate", 0.2);
        getConfig().addDefault("rates.apple.rate_threshold", 50000);
        getConfig().addDefault("rates.money.apple.rate", 0.08);
        getConfig().addDefault("rates.money.apple.rate_threshold", 5000);

        getConfig().addDefault("rates.exp.rate", 0.08);
        getConfig().addDefault("rates.exp.rate_threshold", 20000);
        getConfig().addDefault("rates.money.level.rate", 0.02);
        getConfig().addDefault("rates.money.level.rate_threshold", 5000);
        saveConfig();
    }

    private void registerCommands(){
        new ConversionCommand(inventoryManager);
        new UpdateRatesCommand(conversionRates);
    }

    private void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new ConversionMenuListener(inventoryManager), this);
        Bukkit.getPluginManager().registerEvents(new AppleConversionListener(inventoryManager, conversionRates), this);
        Bukkit.getPluginManager().registerEvents(new ExpConversionListener(inventoryManager, conversionRates), this);
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

}
