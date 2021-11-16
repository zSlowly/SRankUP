package pt.slowly.rankup.eventos;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import pt.slowly.rankup.Main;
import pt.slowly.rankup.utils.MySQL;

public class InventoryClick implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().equals("RankUP")) return;
		e.setCancelled(true);
		
		Player p = (Player) e.getWhoClicked();
		
		if (e.getSlot() == 24) {
			p.closeInventory();
			p.sendMessage("§cVocê cancelou esta ação.");
		}
		
		if (e.getSlot() == 20) {
			
			int nextRank = MySQL.getRank(p.getName()) + 1;
			
			if (Main.econ.getBalance(p) < Main.getInstance().getConfig().getInt("Ranks." + nextRank + ".Coins")) {
				p.sendMessage("§cVocê não possui coins o suficiente para evoluir de rank.");
				return;
			}
			
			if (MySQL.getPontos(p.getName()) < Main.getInstance().getConfig().getInt("Ranks." + nextRank + ".Pontos")) {
				p.sendMessage("§cVocê não possui pontos o suficiente para evoluir de rank.");
				return;
			}
			
			Main.econ.withdrawPlayer(p, Main.getInstance().getConfig().getInt("Ranks." + nextRank + ".Coins"));
			MySQL.removePontos(p.getName(), Main.getInstance().getConfig().getInt("Ranks." + nextRank + ".Pontos"));
			MySQL.setRank(p.getName(), nextRank);
			p.sendMessage("§aVocê evoluir para o rank " + Main.getInstance().getConfig().getString("Ranks." + nextRank + ".Nome").replace("&", "§"));
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
			p.closeInventory();
			
		}
		
	}

}
