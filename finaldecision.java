import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class finaldecision implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Non sei un giocatore :(");
            return false;
        }
        final Player player = (Player)sender;
        if (args.length == 1) {
            player.sendMessage(ChatColor.RED + "Uso Corretto: df [player]");
        }
        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED + "Player offline");
            return false;
        }
        final Inventory gui = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.RED + "Decisione finale per: " + target.getName());
        final ItemStack admissionCheating = new ItemStack(Material.PAPER);
        final ItemMeta admissionCheatingMeta = admissionCheating.getItemMeta();
        admissionCheatingMeta.setDisplayName(ChatColor.RED + "Ammissione Cheating");
        admissionCheating.setItemMeta(admissionCheatingMeta);
        gui.setItem(10, admissionCheating);
        final ItemStack cheatsFoundInSS = new ItemStack(Material.PAPER);
        final ItemMeta cheatsFoundInSSMeta = cheatsFoundInSS.getItemMeta();
        cheatsFoundInSSMeta.setDisplayName(ChatColor.RED + "Cheat Trovati in ss");
        cheatsFoundInSS.setItemMeta(cheatsFoundInSSMeta);
        gui.setItem(11, cheatsFoundInSS);
        final ItemStack slog = new ItemStack(Material.PAPER);
        final ItemMeta slogmeta = admissionCheating.getItemMeta();
        slogmeta.setDisplayName(ChatColor.RED + "Slog Durante Controllo SS");
        slog.setItemMeta(slogmeta);
        gui.setItem(9, slog);
        final ItemStack rifiutocontrollo = new ItemStack(Material.PAPER);
        final ItemMeta rifiutocontrollometa = cheatsFoundInSS.getItemMeta();
        rifiutocontrollometa.setDisplayName(ChatColor.RED + "Rifiuto Controllo SS");
        rifiutocontrollo.setItemMeta(rifiutocontrollometa);
        gui.setItem(8, rifiutocontrollo);
        final ItemStack tentativobypass = new ItemStack(Material.PAPER);
        final ItemMeta tentativobypassmeta = cheatsFoundInSS.getItemMeta();
        tentativobypassmeta.setDisplayName(ChatColor.RED + "Tentativo di Bypass");
        tentativobypass.setItemMeta(tentativobypassmeta);
        gui.setItem(7, tentativobypass);
        player.openInventory(gui);
        return true;
    }
}
