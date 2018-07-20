package takahatashun;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public static File ConfigFile = new File("plugins/AntidoteVN","Config.yml");
    private static YamlConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);
    private static YamlConfiguration ConfigColor = YamlConfiguration.loadConfiguration(ConfigFile);

    public static YamlConfiguration getConfig() {
        try {
            ConfigColor.loadFromString(Config.saveToString().replace("&", "§"));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return ConfigColor;
    }

    public static void saveDefaultsConfig(){
        if(!ConfigFile.exists()){
            try {
                Config.loadFromString(
                    "Messages:\n" +
                        "  Prefix: '&9[&aAntidoteVN&9]'\n" +
                        "  Help:\n" +
                        "    - '/antidote help - View this page'\n" +
                        "    - '/antidote use <player> - Use for other'\n" +
                        "    - '/antidote reload - Reload config'\n" +
                        "  Used: '&aAntidote Successfully'\n" +
                        "  UseOther: '&aAntidote for {0} Successfully'\n" +
                        "  UsedByOther: '&aAntidote by {0}'\n" +
                        "  MustBePlayer: '&cYou must be a player to use this command'\n" +
                        "  PlayerNotExist: '&cPlayer not online or not exist!'\n" +
                        "  NoPerm: '&4You don''t have permission'\n" +
                        "  Reloaded: '&aReloaded Successfully'\n" +
                        "Item:\n" +
                        "  Name: '&aAntidote Potion'\n" +
                        "  Lore:\n" +
                        "    - '&f&m--------------'\n" +
                        "    - '&f&m--&9<&aAntidote&9>&f&m--'\n" +
                        "    - '&f&m--------------'"
                        );
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveConfig(){
        try{
            saveDefaultsConfig();
            Config.save(ConfigFile);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void reloadConfig(){
        saveDefaultsConfig();
        Config = YamlConfiguration.loadConfiguration(ConfigFile);
        ConfigColor = YamlConfiguration.loadConfiguration(ConfigFile);
    }

}

