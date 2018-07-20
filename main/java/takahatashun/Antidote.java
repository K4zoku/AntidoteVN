package takahatashun;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Antidote implements Listener {

    public Antidote(){}

    public static void use(Player p){
        p.removePotionEffect(PotionEffectType.BLINDNESS);
        p.removePotionEffect(PotionEffectType.CONFUSION);
        p.removePotionEffect(PotionEffectType.HUNGER);
        p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        p.removePotionEffect(PotionEffectType.POISON);
        p.removePotionEffect(PotionEffectType.SLOW);
        p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        p.removePotionEffect(PotionEffectType.WEAKNESS);
    }

    public static boolean checkLore(ItemStack item){
        if (item.getType() == Material.AIR) {
            return false;
        }
        if (!item.getItemMeta().hasLore()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        for (int i = 0; i < lore.size() ; i++) {
            if ((lore.get(i).contains(Config.getConfig().getStringList("Item.Lore").get(i)))) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onUseItem(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        ItemStack i = p.getInventory().getItemInMainHand();
        if(i == null){
            return;
        }
        if(i.getType() == Material.AIR){
            return;
        }
        if(i.getType() != Material.POTION){
            return;
        }
        if(i.getType() == Material.POTION){
            if(i.getItemMeta().getDisplayName() == Config.getConfig().getString("Item.Name") && checkLore(i)){
                Antidote.use(p);
            }
        }
    }

}
