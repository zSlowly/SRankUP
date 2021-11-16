package pt.slowly.rankup;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import pt.slowly.rankup.comandos.Pontos;
import pt.slowly.rankup.comandos.RankUP;
import pt.slowly.rankup.eventos.BlockBreak;
import pt.slowly.rankup.eventos.EntityDeath;
import pt.slowly.rankup.eventos.InventoryClick;
import pt.slowly.rankup.eventos.LegendChatEvent;
import pt.slowly.rankup.eventos.PlayerJoin;
import pt.slowly.rankup.utils.MySQL;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public static Economy econ = null;
	
	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		setupEconomy();
		registro();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				
				MySQL.abrirConexao();
				
			}
		}, 0, 20 * 3600);
		
	}
	
	@Override
	public void onDisable() {
	}
	
	private void registro() {
		getCommand("rankup").setExecutor(new RankUP());
		getCommand("pontos").setExecutor(new Pontos());
		Bukkit.getPluginManager().registerEvents(new BlockBreak(), instance);
		Bukkit.getPluginManager().registerEvents(new EntityDeath(), instance);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), instance);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), instance);
		Bukkit.getPluginManager().registerEvents(new LegendChatEvent(), instance);
	}
	
	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		
		econ = (Economy)rsp.getProvider();
		return (econ != null);
	}
	
	public static Main getInstance() {
		return instance;
	}

}
