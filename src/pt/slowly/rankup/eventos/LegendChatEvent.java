package pt.slowly.rankup.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import pt.slowly.rankup.Main;
import pt.slowly.rankup.utils.MySQL;

public class LegendChatEvent implements Listener {
	
	@EventHandler
	public void tag(ChatMessageEvent e) {
		
		Player p = e.getSender();
		
		if (e.getTags().contains("rank")) {
			
			e.setTagValue("rank", Main.getInstance().getConfig().getString("Ranks." + MySQL.getRank(p.getName()) + ".Nome").replace("&", "§"));
			
		}
		
	}

}
