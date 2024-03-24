package commands;

import org.bukkit.plugin.*;
import net.*;
import org.bukkit.command.*;

public class reload implements CommandExecutor
{
    private Plugin plugin;
    
    public reload() {
        this.plugin = (Plugin)danio.getPlugin((Class)danio.class);
    }
    
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender.hasPermission("ssEvents.admin")) {
            if (args.length == 1 && (args[0].equalsIgnoreCase("rl") || args[0].equalsIgnoreCase("reload"))) {
                this.plugin.reloadConfig();
                final String rl = this.plugin.getConfig().getString("Messages.ReloadMSG").replaceAll("&", "§");
                sender.sendMessage(rl);
            }
            if (args.length == 0) {
                final String args2 = this.plugin.getConfig().getString("Messages.args0").replaceAll("&", "§");
                sender.sendMessage(args2);
            }
            if (args.length > 1) {
                final String tooMuchArgs = this.plugin.getConfig().getString("Messages.TooMuchArgs").replaceAll("&", "§");
                sender.sendMessage(tooMuchArgs);
            }
        }
        else {
            final String no_perms = this.plugin.getConfig().getString("Messages.no-perms").replaceAll("&", "§");
            sender.sendMessage(no_perms);
        }
        return false;
    }
}
