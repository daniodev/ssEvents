package net;

import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import events.*;
import commands.*;
import org.bukkit.command.*;
import org.bukkit.*;
import net.luckperms.api.*;
import org.bukkit.plugin.*;

public final class danio extends JavaPlugin
{
    public void onEnable() {
        System.out.println("Plugin Abilitato");
        this.getServer().getPluginManager().registerEvents((Listener)new join(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new quit(), (Plugin)this);
        this.getCommand("ssevents").setExecutor((CommandExecutor)new reload());
        final RegisteredServiceProvider<LuckPerms> provider = (RegisteredServiceProvider<LuckPerms>)Bukkit.getServicesManager().getRegistration((Class)LuckPerms.class);
        if (provider != null) {
            final LuckPerms luckPerms = (LuckPerms)provider.getProvider();
        }
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.reloadConfig();
    }
    
    public void onDisable() {
        System.out.println("Plugin Disabilitato");
    }
}
