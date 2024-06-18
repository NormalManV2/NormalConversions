package nuclearkat.normalconversions.conversion;

import net.milkbowl.vault.economy.Economy;
import nuclearkat.normalconversions.NormalConversions;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class ConversionRates {

    private final NormalConversions normalConversions;
    private double moneyToAppleConversionRate;
    private double appleToMoneyConversionRate;
    private double sellRate;

    public ConversionRates(NormalConversions normalConversions) {
        this.normalConversions = normalConversions;
        this.sellRate = normalConversions.getConfig().getDouble("rates.apple.sell_rate", 0.2);
        updateConversionRates();
        startDailyConversionRate();
    }

    private void startDailyConversionRate() {
        scheduleDailyPlayerConversionRate(0L);
    }

    private void scheduleDailyPlayerConversionRate(long delay) {
        Bukkit.getScheduler().runTaskLater(normalConversions, () -> {
            updateConversionRates();
            scheduleDailyPlayerConversionRate(24L * 60L * 60L * 20L);
        }, delay);
    }

    private void updateConversionRates() {
        moneyToAppleConversionRate = calculateMoneyToAppleRate();
        appleToMoneyConversionRate = calculateAppleToMoneyRate();
    }

    public void updateAppleRates(double sellRate) {
        FileConfiguration config = normalConversions.getConfig();
        config.set("rates.apple.sell_rate", sellRate);
        normalConversions.saveConfig();
        this.sellRate = config.getDouble("rates.apple.sell_rate");
    }

    public void saveRates() {
        normalConversions.saveConfig();
    }

    private double calculateMoneyToAppleRate() {
        Economy economy = NormalConversions.getEconomy();
        List<OfflinePlayer> allPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        allPlayers.addAll(Arrays.asList(Bukkit.getOfflinePlayers()));

        Iterator<OfflinePlayer> iterator = allPlayers.iterator();
        while (iterator.hasNext()) {
            OfflinePlayer offlinePlayer = iterator.next();
            if (!offlinePlayer.hasPlayedBefore()) {
                iterator.remove();
                continue;
            }
            Player player = offlinePlayer.getPlayer();
            if (player != null && player.hasPermission("nc.isAdmin")) {
                iterator.remove();
            }
        }
        double allBalances = allPlayers.stream().mapToDouble(economy::getBalance).sum();

        return allBalances / allPlayers.size();
    }

    private double calculateAppleToMoneyRate(){
        Economy economy = NormalConversions.getEconomy();
        List<OfflinePlayer> allPlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        allPlayers.addAll(Arrays.asList(Bukkit.getOfflinePlayers()));

        Iterator<OfflinePlayer> iterator = allPlayers.iterator();
        while (iterator.hasNext()){
            OfflinePlayer offlinePlayer = iterator.next();
            if (!offlinePlayer.hasPlayedBefore()){
                iterator.remove();
                continue;
            }
            Player player = offlinePlayer.getPlayer();
            if (player != null && player.hasPermission("nc.isAdmin")){
                iterator.remove();
            }
        }
        double allBalances = allPlayers.stream().mapToDouble(economy::getBalance).sum();

        return (allBalances / allPlayers.size()) * sellRate;
    }

    public double getMoneyToAppleConversionRate() {return moneyToAppleConversionRate;}
    public double getAppleToMoneyConversionRate(){return appleToMoneyConversionRate;}
}
