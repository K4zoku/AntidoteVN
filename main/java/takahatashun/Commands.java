package takahatashun;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class Commands implements CommandExecutor {
    private static final String PREFIX = Config.getConfig().getString("Messages.Prefix");

    public Commands(){}

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0){
            if(sender instanceof Player && sender.hasPermission("antidote.use")){
                Player p = (Player) sender;
                Antidote.use(p);
                p.sendMessage(PREFIX+" "+Config.getConfig().getString("Messages.Used"));
            } else {
                sender.sendMessage(Config.getConfig().getString("Messages.MustBePlayer"));
            }
        } else
        if(args[0].equalsIgnoreCase("help") && sender.hasPermission("antidote.*")){
            int cfgsize = Config.getConfig().getStringList("Messages.Help").size();
            for(int i = 0; i<cfgsize; i++){
                sender.sendMessage(String.valueOf(Config.getConfig().getStringList("Messages.Help").get(i)));
            }
            return false;
        } else
        if(args[0].equalsIgnoreCase("reload") && sender.hasPermission("antidote.reload")){
            Config.reloadConfig();
            sender.sendMessage(PREFIX+" "+Config.getConfig().getString("Messages.Reloaded"));
            return false;
        } else
        if(args[0].equalsIgnoreCase("use") && sender.hasPermission("antidote.use")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if (args.length == 1) {
                    Antidote.use(p);
                    p.sendMessage(PREFIX+" "+Config.getConfig().getString("Messages.Used"));
                }
                if (args.length == 2){
                    Player dp = Bukkit.getPlayer(args[1]);
                    if(dp == p){
                        Antidote.use(p);
                        p.sendMessage(PREFIX+" "+Config.getConfig().getString("Message.Used"));
                    } else
                    if(dp == null || !(dp.isOnline())){
                        p.sendMessage(PREFIX+" "+(Config.getConfig().getString("Messages.PlayerNotOnline")));
                        return false;
                    } else {
                        Antidote.use(dp);
                        String UseOther = Config.getConfig().getString("Messages.UseOther")
                                .replace("{0}",dp.getName());
                        String UsedByOther = Config.getConfig().getString("Messages.UsedByOther")
                                .replace("{0}",p.getName());
                        p.sendMessage(PREFIX+" "+UseOther);
                        dp.sendMessage(PREFIX+" "+UsedByOther);
                    }

                }
            } else {
                if(args.length == 1){
                    sender.sendMessage("/antidote use <player>");
                } else if(args.length == 2){
                    Player dp = Bukkit.getPlayer(args[1]);
                    Antidote.use(dp);
                    String UseOther = Config.getConfig().getString("Messages.UseOther").replace("{0}",dp.getName());
                    sender.sendMessage(PREFIX+" "+UseOther);
                    String UsedByOther = Config.getConfig().getString("Messages.UsedByOther").replace("{0}","Console");
                    dp.sendMessage(PREFIX+" "+UsedByOther);
                } else {
                    sender.sendMessage("§4Too many Arguments!");
                }
            }
            return false;
        } else
        if(args[0].equalsIgnoreCase("getpotion") && sender.hasPermission("antidote.get.potion")){
            if(sender instanceof Player){
                ItemStack potion = new ItemStack(Material.POTION);
                ItemMeta meta = potion.getItemMeta();
                meta.setDisplayName(Config.getConfig().getString("Item.Name"));
                meta.setLore(Config.getConfig().getStringList("Item.Lore"));
                potion.setItemMeta(meta);
                Player p = (Player) sender;
                p.getInventory().addItem(potion);
            } else {
                sender.sendMessage(Config.getConfig().getString("Messages.MustBePlayer"));
            }
        }
        if(!(sender.hasPermission("antidote.*"))){
            sender.sendMessage(Config.getConfig().getString("Messages.NoPerm"));
        }

        return false;
    }
}
