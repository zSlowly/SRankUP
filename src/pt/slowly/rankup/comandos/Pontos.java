package pt.slowly.rankup.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pt.slowly.rankup.utils.MySQL;

public class Pontos implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
		
		if (c.getName().equalsIgnoreCase("pontos")) {
			
				if (s instanceof Player) {
					Player p = (Player) s;
					
					if (args.length == 0) {
					
						p.sendMessage("�aVoc� possui �f" + MySQL.getPontos(p.getName()) + " �apontos.");
						return true;
					}else {
						
						p.sendMessage("�aO jogador �f" + Bukkit.getPlayer(args[0]).getName() + " �apossui �f" + MySQL.getPontos(Bukkit.getPlayer(args[0]).getName()) + " �apontos.");
						return true;
					}
					
				}
				
				if (args.length < 3) {
					s.sendMessage("�cDigite '/pontos <dar/remover/setar> <jogador> <quantia>");
					return true;
				}
				
				Player target = Bukkit.getPlayer(args[1]);
				
				if (args[0].equalsIgnoreCase("dar")) {
					
					try {
						
						int quantia = Integer.parseInt(args[2]);
						
						MySQL.addPontos(target.getName(), quantia);
						s.sendMessage("�aVoc� deu �f" + quantia + " �apontos para o jogador �f" + target.getName() + "�a.");
						
					} catch (Exception e) {
						s.sendMessage("�cDigite uma quantia v�lida!");
					}
					
				}
				
				if (args[0].equalsIgnoreCase("remover")) {
					
					try {
						
						int quantia = Integer.parseInt(args[2]);
						
						MySQL.removePontos(target.getName(), quantia);
						s.sendMessage("�aVoc� removeu �f" + quantia + " �apontos para o jogador �f" + target.getName() + "�a.");
						
					} catch (Exception e) {
						s.sendMessage("�cDigite uma quantia v�lida!");
					}
					
				}
				
				if (args[0].equalsIgnoreCase("setar")) {
					
					try {
						
						int quantia = Integer.parseInt(args[2]);
						
						MySQL.setPontos(target.getName(), quantia);
						s.sendMessage("�aVoc� setou �f" + quantia + " �apontos para o jogador �f" + target.getName() + "�a.");
						
					} catch (Exception e) {
						s.sendMessage("�cDigite uma quantia v�lida!");
					}
					
				}
			
		}
		
		return false;
	}

}
