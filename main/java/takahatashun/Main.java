package takahatashun;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Main plugin;

    public void onEnable() {
        plugin = this;
    getLogger().info("Enabling AntidoteVN...");

    getLogger().info("Loading Configuration...");
    Config.getConfig().options().copyDefaults(true);
    if(!(Config.ConfigFile.exists())){
        Config.saveDefaultsConfig();
        getLogger().info("Config.yml not found, create new!");
    }
    getLogger().info("Loaded Configuration!");

    getLogger().info("Registering Events...");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Antidote(),this);
    getLogger().info("Registered Event!");

    getCommand("antidote").setExecutor(new Commands());

    getLogger().info("AntidoteVN has been enabled");

    }

    public void onDisable() {
        Config.saveConfig();
        getLogger().info("AntidoteVN has been disabled");
        // Plugin shutdown logic
    }
}
