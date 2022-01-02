package com.markie129.basicstaffchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SC] Starting");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SC] Loading config...");

        getConfig().addDefault("Version", "1.0");
        getConfig().addDefault("Prefix", "&7[&4SC7] ");
        getConfig().addDefault("No-Permission", "&4ERROR: You do not have permission to run this command!");
        getConfig().addDefault("Enabled", true);

        getConfig().options().copyDefaults(true);
        saveConfig();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SC] Successfully loaded config.");

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SC] Thank you for using Basic Staff Chat by Markie129!");

    }

    @Override
    public void onDisable() {

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SC] Stopped!");

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SC] Thank you for using Basic Staff Chat by Markie129!");

    }

    public boolean onCommand(CommandSender sender, Command cmd, String list, String args[]) {

        // Prefix
        String nonColoredPrefix = getConfig().getString("Prefix");
        String coloredPrefix = ChatColor.translateAlternateColorCodes('&', nonColoredPrefix);

        if(cmd.getName().equalsIgnoreCase("sc") && getConfig().getBoolean("enabled")) {

            if(sender.hasPermission("bsc.use")) {

                if(args.length == 0) {

                    sender.sendMessage(coloredPrefix + ChatColor.RED + "ERROR: Not enough arguments!");
                    return true;

                }

                StringBuilder x = new StringBuilder();
                for(int i = 0; i < args.length; i++) {

                    x.append(args[i] + " ");

                }

                for(Player all : Bukkit.getOnlinePlayers()){
                    if (all.hasPermission("bsc.see")) {

                        all.sendMessage(coloredPrefix + ChatColor.RED + sender.getName() + ": " + x.toString());

                    }
                }
                return true;

            }
            else {

                String nonColoredPermError = getConfig().getString("No-Permission");
                String coloredPermError  = ChatColor.translateAlternateColorCodes('&', nonColoredPermError);

                sender.sendMessage(coloredPrefix + " " + coloredPermError);
                return true;

            }

        }

        if(cmd.getName().equalsIgnoreCase("screload")) {

            if(sender.hasPermission("bsc.reload")) {

                sender.sendMessage(coloredPrefix + ChatColor.RED + "Reloading!");
                reloadConfig();
                sender.sendMessage(coloredPrefix + ChatColor.RED + "Reloaded!");

                return true;

            }
            else {

                String nonColoredPermError = getConfig().getString("No-Permission");
                String coloredPermError  = ChatColor.translateAlternateColorCodes('&', nonColoredPermError);

                sender.sendMessage(coloredPrefix + " " + coloredPermError);
                return true;

            }

        }

        return true;

    }

}
