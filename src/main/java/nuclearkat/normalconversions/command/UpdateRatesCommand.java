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

    private void handleAppleRateUpdate(double rateChange){
        conversionRates.updateAppleRates(rateChange);
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
        if (args.length > 2){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c Incorrect usage! Please use <nsave || ncs || ncsave || ratesave> <sell_rate> "));
            return;
        }

        double rateChange = Double.parseDouble(args[0]);
        handleAppleRateUpdate(rateChange);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLevel rates have been updated!"));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
