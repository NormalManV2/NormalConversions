package nuclearkat.normalconversions.transactions;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.NormalConversions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class ConversionRates {

    private final double baseAppleRate;
    private final double appleRateThreshold;

    private final double baseLevelRate;
    private final double levelRateThreshold;

    private BukkitTask dailyPlayerConversionRateTask;

    private final NormalConversions normalConversions;

    private final HashMap<UUID, Double> playerAppleRates = new HashMap<>();
    private final HashMap<UUID, Double> playerExpLevelRates = new HashMap<>();

    public ConversionRates(NormalConversions normalConversions) {
        this.normalConversions = normalConversions;
        this.baseAppleRate = normalConversions.getConfig().getDouble("rates.apple.rate", 0.2);
        this.appleRateThreshold = normalConversions.getConfig().getDouble("rates.apple.rate_threshold", 50000);
        this.baseLevelRate = normalConversions.getConfig().getDouble("rates.exp.rate", 0.08);
        this.levelRateThreshold = normalConversions.getConfig().getDouble("rates.exp.rate_threshold", 20000);
        startDailyConversionRate();
    }

    private void startDailyConversionRate(){
        scheduleDailyPlayerConversionRate(0L);
    }

    private void scheduleDailyPlayerConversionRate(long delay){
        this.dailyPlayerConversionRateTask = Bukkit.getScheduler().runTaskLater(normalConversions, () -> {
            updateConversionRates();
            scheduleDailyPlayerConversionRate(24L * 60L * 60L * 20L);
        }, delay);
    }

    private void updateConversionRates(){
        for (OfflinePlayer player : Bukkit.getOfflinePlayers()){
            playerAppleRates.put(player.getUniqueId(), calculatePlayerAppleRate((Player) player));
            playerExpLevelRates.put(player.getUniqueId(), calculatePlayerLevelRate((Player) player));
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

    private double calculatePlayerAppleRate(Player player){
        Economy economy = NormalConversions.getEconomy();
        double playerBalance = economy.getBalance(player);
        double rate = playerBalance * this.baseAppleRate;
        return Math.max(rate, this.appleRateThreshold);
    }

    private double calculatePlayerLevelRate(Player player){
        Economy economy = NormalConversions.getEconomy();
        double playerBalance = economy.getBalance(player);
        double rate = playerBalance * this.baseLevelRate;
        return Math.max(rate, this.levelRateThreshold);
    }

    public double getPlayerAppleRate(UUID playerId){
        return playerAppleRates.get(playerId);
    }

    public double getPlayerLevelRate(UUID playerId){
        return playerExpLevelRates.get(playerId);
    }

}
