package nuclearkat.normalconversions.conversion;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.NormalConversions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConversionRates {

    private final double baseMoneyToAppleRate;
    private final double moneyToAppleRateThreshold;

    private final double baseAppleToMoneyRate;
    private final double appleToMoneyRateThreshold;

    private final double baseMoneyToLevelRate;
    private final double levelToMoneyRateThreshold;

    private final double baseLevelToMoneyRate;
    private final double moneyToLevelRateThreshold;

    private final NormalConversions normalConversions;

    private final Map<UUID, Double> playerMoneyToAppleRates = new HashMap<>();
    private final Map<UUID, Double> playerAppleToMoneyRates = new HashMap<>();

    private final Map<UUID, Double> playerMoneyToLevelRates = new HashMap<>();
    private final Map<UUID, Double> playerLevelToMoneyRates = new HashMap<>();

    public ConversionRates(NormalConversions normalConversions) {
        this.normalConversions = normalConversions;
        this.baseMoneyToAppleRate = normalConversions.getConfig().getDouble("rates.apple.rate", 0.2);
        this.moneyToAppleRateThreshold = normalConversions.getConfig().getDouble("rates.apple.rate_threshold", 50000);

        this.baseAppleToMoneyRate = normalConversions.getConfig().getDouble("rates.money.apple.rate", 0.08);
        this.appleToMoneyRateThreshold = normalConversions.getConfig().getDouble("rates.money.apple.rate_threshold", 5000);

        this.baseMoneyToLevelRate = normalConversions.getConfig().getDouble("rates.exp.rate", 0.08);
        this.moneyToLevelRateThreshold = normalConversions.getConfig().getDouble("rates.exp.rate_threshold", 20000);

        this.baseLevelToMoneyRate = normalConversions.getConfig().getDouble("rates.money.level.rate", 0.02);
        this.levelToMoneyRateThreshold = normalConversions.getConfig().getDouble("rates.money.level.rate_threshold", 5000);

        updateConversionRates();
        startDailyConversionRate();
    }

    private void startDailyConversionRate(){
        scheduleDailyPlayerConversionRate(0L);
    }

    private void scheduleDailyPlayerConversionRate(long delay){
        Bukkit.getScheduler().runTaskLater(normalConversions, () -> {
            updateConversionRates();
            scheduleDailyPlayerConversionRate(24L * 60L * 60L * 20L);
        }, delay);
    }

    private void updateConversionRates(){
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()){
            playerMoneyToAppleRates.put(player.getUniqueId(), calculatePlayerMoneyToAppleRate((Player) player));
            playerAppleToMoneyRates.put(player.getUniqueId(), calculatePlayerAppleToMoneyRate((Player) player));
            playerMoneyToLevelRates.put(player.getUniqueId(), calculatePlayerMoneyToLevelRate((Player) player));
            playerLevelToMoneyRates.put(player.getUniqueId(), calculatePlayerLevelToMoneyRate((Player) player));
        }
    }

    public void updateAppleRates(double playerRate, double rateThreshold){
        FileConfiguration config = normalConversions.getConfig();
        config.set("rates.apple.rate", playerRate);
        config.set("rates.apple.rate_threshold", rateThreshold);
        normalConversions.saveConfig();
    }

    public void updateLevelRates(double playerRate, double rateThreshold){
        FileConfiguration config = normalConversions.getConfig();
        config.set("rates.exp.rate", playerRate);
        config.set("rates.exp.rate_threshold", rateThreshold);
    }

    public void saveRates(){
        normalConversions.saveConfig();
    }



    private double calculatePlayerMoneyToAppleRate(Player player){
        Economy economy = NormalConversions.getEconomy();
        double playerBalance = economy.getBalance(player);
        double rate = playerBalance * this.baseMoneyToAppleRate;
        return Math.max(rate, this.moneyToAppleRateThreshold);
    }

    private double calculatePlayerAppleToMoneyRate(Player player){
        Economy economy = NormalConversions.getEconomy();
        double playerBalance = economy.getBalance(player);
        double rate = playerBalance * this.baseAppleToMoneyRate;
        return Math.max(rate, this.appleToMoneyRateThreshold);
    }

    private double calculatePlayerMoneyToLevelRate(Player player){
        Economy economy = NormalConversions.getEconomy();
        double playerBalance = economy.getBalance(player);
        double rate = playerBalance * this.baseMoneyToLevelRate;
        return Math.max(rate, this.moneyToLevelRateThreshold);
    }

    private double calculatePlayerLevelToMoneyRate(Player player){
        Economy economy = NormalConversions.getEconomy();
        double playerBalance = economy.getBalance(player);
        double rate = playerBalance * this.baseLevelToMoneyRate;
        return Math.max(rate, this.levelToMoneyRateThreshold);
    }

    public double getPlayerMoneyToAppleRate(UUID playerId){
        return playerMoneyToAppleRates.get(playerId);
    }

    public double getPlayerAppleToMoneyRate(UUID playerId){
        return playerAppleToMoneyRates.get(playerId);
    }

    public double getPlayerMoneyToLevelRate(UUID playerId){
        return playerMoneyToLevelRates.get(playerId);
    }

    public double getPlayerLevelToMoneyRate(UUID playerId){
        return playerLevelToMoneyRates.get(playerId);
    }
}
