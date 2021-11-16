package pt.slowly.rankup.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.Bukkit;

import pt.slowly.rankup.Main;

public class MySQL {

	public static Connection con = null;
	
	public static void abrirConexao() {
		String str1 = Main.getInstance().getConfig().getString("MySQL.IP");
		int i = Main.getInstance().getConfig().getInt("MySQL.Porta");
		String str2 = Main.getInstance().getConfig().getString("MySQL.Usuario");
		String str3 = Main.getInstance().getConfig().getString("MySQL.Senha");
		String str4 = Main.getInstance().getConfig().getString("MySQL.DataBase");
		String str5 = "jdbc:mysql://";
		String str6 = String.valueOf(String.valueOf(str5)) + str1 + ":" + i + "/" + str4;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(str6, str2, str3);
			criarTabela();
			criarTabelaPontos();
			Bukkit.getConsoleSender().sendMessage("§aConexão com o MySQL foi bem sucedida!");

		} catch (Exception e) {
			
			Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao tentar conectar ao MySQL!");
			
		}
	}
	
	public static void criarTabela() {
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = con.prepareStatement("CREATE TABLE IF NOT EXISTS RANKUP(JOGADOR VARCHAR(45), RANK INTEGER);");
			preparedStatement.executeUpdate();
			Bukkit.getConsoleSender().sendMessage("§aTabela carregada com sucesso!");
			
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao tentar carregar a tabela!");
		}
	}
	
	public static void addJogador(String jogador) {
		PreparedStatement preparedStatement = null;
		
		try {
		
			preparedStatement = con.prepareStatement("INSERT INTO RANKUP (JOGADOR,RANK) VALUES (?,0)");
			preparedStatement.setString(1, jogador);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static boolean hasJogador(String jogador) {
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = con.prepareStatement("SELECT RANK FROM RANKUP WHERE JOGADOR = ?");
			preparedStatement.setString(1, jogador);
			ResultSet rs = preparedStatement.executeQuery();
			boolean result = rs.next();
			return result;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void setRank(String jogador, int rank) {
		PreparedStatement preparedStatement = null;
		
		if (hasJogador(jogador)) {
			try {
				
				preparedStatement = con.prepareStatement("UPDATE RANKUP SET RANK = ? WHERE JOGADOR = ?");
				preparedStatement.setString(2, jogador);
				preparedStatement.setInt(1, rank);
				preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			addJogador(jogador);
		}
	}
	
	public static int getRank(String jogador) {
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = con.prepareStatement("SELECT RANK FROM RANKUP WHERE JOGADOR = ?");
			preparedStatement.setString(1, jogador);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				return rs.getInt("RANK"); 
			return 0;
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static void removeJogador(String jogador) {
		PreparedStatement preparedStatement = null;
		
		if (hasJogador(jogador)) {
			try {
				
				preparedStatement = con.prepareStatement("DELETE FROM RANKUP WHERE JOGADOR = ?");
				preparedStatement.setString(1, jogador);
				preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void criarTabelaPontos() {
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = con.prepareStatement("CREATE TABLE IF NOT EXISTS PONTOS(JOGADOR VARCHAR(45), QUANTIA INTEGER);");
			preparedStatement.executeUpdate();
			Bukkit.getConsoleSender().sendMessage("§aTabela carregada com sucesso!");
			
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao tentar carregar a tabela!");
		}
	}
	
	public static void addJogadorPontos(String jogador) {
		PreparedStatement preparedStatement = null;
		
		try {
		
			preparedStatement = con.prepareStatement("INSERT INTO PONTOS (JOGADOR,QUANTIA) VALUES (?,0)");
			preparedStatement.setString(1, jogador);
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static boolean hasJogadorPontos(String jogador) {
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = con.prepareStatement("SELECT QUANTIA FROM PONTOS WHERE JOGADOR = ?");
			preparedStatement.setString(1, jogador);
			ResultSet rs = preparedStatement.executeQuery();
			boolean result = rs.next();
			return result;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void setPontos(String jogador, int pontos) {
		PreparedStatement preparedStatement = null;
		
		if (hasJogadorPontos(jogador)) {
			try {
				
				preparedStatement = con.prepareStatement("UPDATE PONTOS SET QUANTIA = ? WHERE JOGADOR = ?");
				preparedStatement.setString(2, jogador);
				preparedStatement.setInt(1, pontos);
				preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			addJogador(jogador);
		}
	}
	
	public static int getPontos(String jogador) {
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = con.prepareStatement("SELECT QUANTIA FROM PONTOS WHERE JOGADOR = ?");
			preparedStatement.setString(1, jogador);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				return rs.getInt("QUANTIA"); 
			return 0;
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static void addPontos(String jogador, int pontos) {
		
		if (hasJogador(jogador)) {
			setPontos(jogador, getPontos(jogador) + pontos);
		}
		
	}
	
	public static void removePontos(String jogador, int pontos) {
		
		if (hasJogador(jogador)) {
			setPontos(jogador, getPontos(jogador) - pontos);
		}
		
	}
	
	public static void removeJogadorPontos(String jogador) {
		PreparedStatement preparedStatement = null;
		
		if (hasJogador(jogador)) {
			try {
				
				preparedStatement = con.prepareStatement("DELETE FROM PONTOS WHERE JOGADOR = ?");
				preparedStatement.setString(1, jogador);
				preparedStatement.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
