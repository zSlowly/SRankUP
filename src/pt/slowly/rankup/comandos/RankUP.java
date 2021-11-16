package pt.slowly.rankup.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import pt.slowly.rankup.Main;
import pt.slowly.rankup.utils.ItemBuilder;
import pt.slowly.rankup.utils.MySQL;

public class RankUP implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
		
		if (s instanceof Player) {
			Player p = (Player) s;
			
			if (c.getName().equalsIgnoreCase("rankup")) {
				
				Inventory inv = Bukkit.createInventory(null, 4 * 9, "RankUP");
				
				int rank = MySQL.getRank(p.getName());
				int nextRank = MySQL.getRank(p.getName()) + 1;
				
				ItemStack info = new ItemBuilder(Material.ANVIL)
						.setDisplayName("§aInformações")
						.setLore("§7Você está no Rank: " + Main.getInstance().getConfig().getString("Ranks." + rank + ".Nome").replace("&", "§"), "§7Próximo Rank: " + Main.getInstance().getConfig().getString("Ranks." + nextRank + ".Nome" ).replace("&", "§"), "", "§7Preço:", " §7- §f" + Main.getInstance().getConfig().getInt("Ranks." + rank + ".Coins") + " §7coins", " §7- §f" + Main.getInstance().getConfig().getInt("Ranks." + rank + ".Pontos") + " §7pontos")
						.build();
				
				inv.setItem(13, info);
				
				ItemStack confirm = new ItemBuilder(Material.WOOL, 1, (short) 5)
						.setDisplayName("§aConfirmar")
						.setLore("§7Clique aqui para confirmar e evoluir de rank.")
						.build();
				
				inv.setItem(20, confirm);
				
				ItemStack cancelar = new ItemBuilder(Material.WOOL, 1, (short) 14)
						.setDisplayName("§cCancelar")
						.setLore("§7Clique aqui para cancelar e fechar o menú.")
						.build();
				
				inv.setItem(24, cancelar);
				
				p.openInventory(inv);
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
				
			}
			
		}else {
			s.sendMessage("§cApenas jogadores podem executar este comando.");
		}
		
		return false;
	}

}
