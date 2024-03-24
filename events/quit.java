package events;

import org.bukkit.plugin.*;
import net.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.command.*;
import net.luckperms.api.*;
import org.bukkit.entity.*;
import net.luckperms.api.node.*;
import net.luckperms.api.node.types.*;
import java.util.*;
import net.luckperms.api.model.user.*;
import org.bukkit.event.*;

public class quit implements Listener
{
    private Plugin plugin;
    
    public quit() {
        this.plugin = (Plugin)danio.getPlugin((Class)danio.class);
    }
    
    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        final String perms = this.plugin.getConfig().getString("staff-perms");
        if (!event.getPlayer().hasPermission(perms)) {
            final Player p = event.getPlayer();
            final List<String> commands = (List<String>)this.plugin.getConfig().getStringList("Commands.Suspect.QuitEvent");
            for (final String cmd : commands) {
                final String command = cmd.replaceAll("\\&", "§").replaceAll("\\[player\\]", event.getPlayer().getName());
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
            }
        }
        else {
            final Player cheater = event.getPlayer();
            final List<String> commands = (List<String>)this.plugin.getConfig().getStringList("Commands.Staffer.QuitEvent");
            for (final String cmd : commands) {
                final String command = cmd.replaceAll("\\&", "§").replaceAll("\\[player\\]", event.getPlayer().getName());
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
            }
        }
        if (this.plugin.getConfig().getBoolean("LUCKPERMS-IMPLEMENTATION")) {
            if (this.plugin.getConfig().getBoolean("lp-change-prefix")) {
                if (event.getPlayer().hasPermission(perms)) {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newprefix = this.plugin.getConfig().getString("Prefix.staff-prefix").replaceAll("&", "§");
                    final int StafftPriority = this.plugin.getConfig().getInt("Prefix.StaffPriority");
                    final PrefixNode node = (PrefixNode)PrefixNode.builder(newprefix, StafftPriority).build();
                    user.data().remove((Node)node);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
                else {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newprefix = this.plugin.getConfig().getString("Prefix.suspect-prefix").replaceAll("&", "§");
                    final int SuspectPriority = this.plugin.getConfig().getInt("Prefix.SuspectPriority");
                    final PrefixNode node = (PrefixNode)PrefixNode.builder(newprefix, SuspectPriority).build();
                    user.data().remove((Node)node);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
            }
            if (this.plugin.getConfig().getBoolean("lp-add-group")) {
                if (event.getPlayer().hasPermission(perms)) {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newgroup = this.plugin.getConfig().getString("Group.staff-group").replaceAll("&", "§");
                    final InheritanceNode node2 = (InheritanceNode)InheritanceNode.builder(newgroup).build();
                    user.data().remove((Node)node2);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
                else {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newgroup = this.plugin.getConfig().getString("Group.suspect-group").replaceAll("&", "§");
                    final InheritanceNode node2 = (InheritanceNode)InheritanceNode.builder(newgroup).build();
                    user.data().remove((Node)node2);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
            }
        }
        final Player p = event.getPlayer();
        if (this.plugin.getConfig().getBoolean("leave-messages-on")) {
            event.setQuitMessage((String)null);
            if (event.getPlayer().hasPermission(perms)) {
                final String StaffLeave = this.plugin.getConfig().getString("LeaveMessages.StaffLeave").replaceAll("\\[player\\]", event.getPlayer().getName()).replaceAll("\\&", "§");
                event.setQuitMessage(StaffLeave);
            }
            else {
                final String SuspectLeave = this.plugin.getConfig().getString("LeaveMessages.SuspectLeave").replaceAll("\\[player\\]", event.getPlayer().getName()).replaceAll("\\&", "§");
                event.setQuitMessage(SuspectLeave);
            }
        }
    }
}
