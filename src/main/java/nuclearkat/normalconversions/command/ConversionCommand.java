package nuclearkat.normalconversions.command;

import net.md_5.bungee.api.ChatColor;
import nuclearkat.normalconversions.inventories.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.logging.Level;

public class ConversionCommand extends AbstractCommand {
    private final InventoryManager inventoryManager;

    public ConversionCommand(InventoryManager inventoryManager){
        super("nconversion",
                new String[]{"nc", "nconversion", "convert"},
                "Economy conversion system: Usage <nc , nconversion , convert>",
                "nc.command.convert.use");
        this.inventoryManager = inventoryManager;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)){
            Bukkit.getLogger().log(Level.WARNING, "Normal Conversions Command can only be used by players!");
            return;
        }
        Player player = (Player) sender;
        if (args.length > 0){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cIncorrect Usage! Please use : <nc || nconversion || convert>"));
            return;
        }
        player.openInventory(inventoryManager.getConversionMenu());
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

}
