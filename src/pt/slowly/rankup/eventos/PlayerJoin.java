package pt.slowly.rankup.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import pt.slowly.rankup.utils.MySQL;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		if (!MySQL.hasJogador(p.getName())) {
			MySQL.addJogador(p.getName());
		}
		
		if (!MySQL.hasJogadorPontos(p.getName())) {
			MySQL.addJogadorPontos(p.getName());
		}
		
	}

}
