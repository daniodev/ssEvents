package events;

import org.bukkit.plugin.*;
import net.*;
import org.bukkit.event.player.*;
import net.luckperms.api.*;
import org.bukkit.entity.*;
import net.luckperms.api.node.*;
import net.luckperms.api.node.types.*;
import org.bukkit.*;
import org.bukkit.command.*;
import net.luckperms.api.model.user.*;
import java.util.*;
import org.bukkit.event.*;

public class join implements Listener
{
    private Plugin plugin;
    
    public join() {
        this.plugin = (Plugin)danio.getPlugin((Class)danio.class);
    }
    
    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final String perms = this.plugin.getConfig().getString("staff-perms");
        assert perms != null;
        if (this.plugin.getConfig().getBoolean("teleport-suspect-to-xyz") && !event.getPlayer().hasPermission(perms)) {
            final Player p = event.getPlayer();
            final String location_sospetto_x = this.plugin.getConfig().getString("SuspectJoinLocation.x");
            final double x = Double.parseDouble(location_sospetto_x);
            final String location_sospetto_y = this.plugin.getConfig().getString("SuspectJoinLocation.y");
            final double y = Double.parseDouble(location_sospetto_y);
            final String location_sospetto_z = this.plugin.getConfig().getString("SuspectJoinLocation.z");
            final double z = Double.parseDouble(location_sospetto_z);
            final Location location = new Location(p.getWorld(), x, y, z);
            p.teleport(location);
        }
        if (this.plugin.getConfig().getBoolean("teleport-staff-to-xyz") && event.getPlayer().hasPermission(perms)) {
            final String location_staff_x = this.plugin.getConfig().getString("StaffJoinLocation.x");
            final double x2 = Double.parseDouble(location_staff_x);
            final String location_staff_y = this.plugin.getConfig().getString("StaffJoinLocation.y");
            final double y2 = Double.parseDouble(location_staff_y);
            final String location_staff_z = this.plugin.getConfig().getString("StaffJoinLocation.z");
            final double z2 = Double.parseDouble(location_staff_z);
            final Location location2 = new Location(event.getPlayer().getWorld(), x2, y2, z2);
            event.getPlayer().teleport(location2);
        }
        if (this.plugin.getConfig().getBoolean("LUCKPERMS-IMPLEMENTATION")) {
            if (this.plugin.getConfig().getBoolean("lp-change-prefix")) {
                if (event.getPlayer().hasPermission(perms)) {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newprefix = this.plugin.getConfig().getString("Prefix.staff-prefix").replaceAll("&", "§");
                    final int StafftPriority = this.plugin.getConfig().getInt("Prefix.StaffPriority");
                    final PrefixNode node = (PrefixNode)PrefixNode.builder(newprefix, StafftPriority).build();
                    user.data().add((Node)node);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
                else {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newprefix = this.plugin.getConfig().getString("Prefix.suspect-prefix").replaceAll("&", "§");
                    final int SuspectPriority = this.plugin.getConfig().getInt("Prefix.SuspectPriority");
                    final PrefixNode node = (PrefixNode)PrefixNode.builder(newprefix, SuspectPriority).build();
                    user.data().add((Node)node);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
            }
            if (this.plugin.getConfig().getBoolean("lp-add-group")) {
                if (event.getPlayer().hasPermission(perms)) {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newgroup = this.plugin.getConfig().getString("Group.staff-group").replaceAll("&", "§");
                    final InheritanceNode node2 = (InheritanceNode)InheritanceNode.builder(newgroup).build();
                    user.data().add((Node)node2);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
                else {
                    final Player p = event.getPlayer();
                    final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                    final String newgroup = this.plugin.getConfig().getString("Group.suspect-group").replaceAll("&", "§");
                    final InheritanceNode node2 = (InheritanceNode)InheritanceNode.builder(newgroup).build();
                    user.data().add((Node)node2);
                    LuckPermsProvider.get().getUserManager().saveUser(user);
                }
            }
            if (this.plugin.getConfig().getBoolean("lp-add-permissions")) {
                final Player p = event.getPlayer();
                final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                if (p.hasPermission(perms)) {
                    final List<String> permissions = (List<String>)this.plugin.getConfig().getStringList("Permission.Add.Staff");
                    for (final String cmdperms : permissions) {
                        final PermissionNode node3 = (PermissionNode)PermissionNode.builder(cmdperms).build();
                        user.data().add((Node)node3);
                        LuckPermsProvider.get().getUserManager().saveUser(user);
                    }
                }
                else {
                    final List<String> permissions = (List<String>)this.plugin.getConfig().getStringList("Permission.Add.Suspect");
                    for (final String cmdperms : permissions) {
                        final PermissionNode node3 = (PermissionNode)PermissionNode.builder(cmdperms).build();
                        user.data().add((Node)node3);
                        LuckPermsProvider.get().getUserManager().saveUser(user);
                    }
                }
            }
            if (this.plugin.getConfig().getBoolean("lp-remove-permssions")) {
                final Player p = event.getPlayer();
                final User user = LuckPermsProvider.get().getPlayerAdapter((Class)Player.class).getUser((Object)p);
                if (p.hasPermission(perms)) {
                    final List<String> permissions = (List<String>)this.plugin.getConfig().getStringList("Permission.Rem.Staff");
                    for (final String cmdperms : permissions) {
                        final RegexPermissionNode node4 = (RegexPermissionNode)RegexPermissionNode.builder(cmdperms).build();
                        user.data().add((Node)node4);
                        LuckPermsProvider.get().getUserManager().saveUser(user);
                    }
                }
                else {
                    final List<String> permissions = (List<String>)this.plugin.getConfig().getStringList("Permission.Remove.Suspect");
                    for (final String cmdperms : permissions) {
                        final RegexPermissionNode node4 = (RegexPermissionNode)RegexPermissionNode.builder(cmdperms).build();
                        user.data().add((Node)node4);
                        LuckPermsProvider.get().getUserManager().saveUser(user);
                    }
                }
            }
        }
        if (!event.getPlayer().hasPermission(perms)) {
            final List<String> commandsuspect = (List<String>)this.plugin.getConfig().getStringList("Commands.Suspect.JoinEvent");
            for (final String cmd : commandsuspect) {
                final String command = cmd.replaceAll("\\&", "§").replaceAll("\\[player\\]", event.getPlayer().getName());
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
            }
        }
        else {
            final List<String> commandsuspect = (List<String>)this.plugin.getConfig().getStringList("Commands.Staffer.JoinEvent");
            for (final String cmd : commandsuspect) {
                final String command = cmd.replaceAll("\\&", "§").replaceAll("\\[player\\]", event.getPlayer().getName());
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
            }
        }
        if (this.plugin.getConfig().getBoolean("join-messages-on")) {
            event.setJoinMessage((String)null);
            if (event.getPlayer().hasPermission(perms)) {
                final String StaffJoinMSG = this.plugin.getConfig().getString("JoinMessages.StaffJoin").replaceAll("\\[player\\]", event.getPlayer().getName()).replaceAll("\\&", "§");
                event.setJoinMessage(StaffJoinMSG);
            }
            else {
                final String SuspectJoinMSG = this.plugin.getConfig().getString("JoinMessages.SuspectJoin").replaceAll("\\[player\\]", event.getPlayer().getName()).replaceAll("\\&", "§");
                event.setJoinMessage(SuspectJoinMSG);
            }
        }
    }
}
