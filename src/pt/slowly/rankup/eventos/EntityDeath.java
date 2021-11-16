package pt.slowly.rankup.eventos;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import pt.slowly.rankup.Main;
import pt.slowly.rankup.utils.MySQL;

public class EntityDeath implements Listener {
	
	@EventHandler
	public void death(EntityDeathEvent e) {
		
		if (e.getEntity().getKiller() instanceof Player) {
			
			Player p = e.getEntity().getKiller();
			
			if (p.getWorld().getName().equals(Main.getInstance().getConfig().getString("Mundo.Mobs"))) {
				
				Random random = new Random();
				int rdm = random.nextInt(100) + 1;
				
				if (Main.getInstance().getConfig().getDouble("Pontos.Porcentagem") <= rdm) {
					
					MySQL.addPontos(p.getName(), Main.getInstance().getConfig().getInt("Pontos.Ganhar"));
					
				}
				
			}
			
		}
		
	}

}
