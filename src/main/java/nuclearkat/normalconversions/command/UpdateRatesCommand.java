package nuclearkat.normalconversions.command;

import nuclearkat.normalconversions.conversion.ConversionRates;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class UpdateRatesCommand extends AbstractCommand {

    private final ConversionRates conversionRates;

    public UpdateRatesCommand(ConversionRates conversionRates) {
        super("nsave", new String[]{"ncs, ncsave, ratesave"}, "Used to save the rate settings in the config", "nc.command.savecommand.use");
        this.conversionRates = conversionRates;
    }

    private void handleLevelRateUpdate(double rateChage, double rateThresholdChange){
        conversionRates.updateLevelRates(rateChage, rateThresholdChange);
    }

    private void handleAppleRateUpdate(double rateChange, double rateThresholdChange){
        conversionRates.updateAppleRates(rateChange, rateThresholdChange);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1){
            conversionRates.saveRates();
            sender.sendMessage("Rates have been saved!");
            return;
        }
        if (!(sender instanceof Player)){
            return;
        }
        Player player = (Player) sender;
        if (args.length > 3){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c Incorrect usage! Please use <nsave || ncs || ncsave || ratesave> <appleRate || levelRate> <rate> <rateThreshold>"));
            return;
        }
        if (!args[0].equalsIgnoreCase("levelRate") || args[0].equalsIgnoreCase("appleRate")){
            double rateChange = Double.parseDouble(args[1]);
            double rateThresholdChange = Double.parseDouble(args[2]);
            handleAppleRateUpdate(rateChange, rateThresholdChange);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cApple rates have been updated!"));
            return;
        }
        double rateChange = Double.parseDouble(args[1]);
        double rateThresholdChange = Double.parseDouble(args[2]);
        handleLevelRateUpdate(rateChange, rateThresholdChange);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel rates have been updated!"));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
