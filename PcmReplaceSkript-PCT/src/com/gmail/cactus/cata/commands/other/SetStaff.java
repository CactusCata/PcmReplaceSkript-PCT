package com.gmail.cactus.cata.commands.other;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.gmail.cactus.cata.Main;
import com.gmail.cactus.cata.enums.PrefixMessage;

public class SetStaff implements CommandExecutor {

	private Main main;

	public SetStaff(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("setstaff")) {

			if (args.length < 1) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le joueur !");
				return true;
			}
			if (args.length < 2) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le grade !");
				return true;
			}

			if (args.length < 3) {
				sender.sendMessage(PrefixMessage.PREFIX + "Il manque le mot de passe !");
				return true;
			}
			if (!(args[2].equalsIgnoreCase("blabla"))) {
				sender.sendMessage(PrefixMessage.PREFIX + "Mot de passe incorrect !");
				return true;
			}

			boolean value = false;

			String[] grades = { "Fondateur", "Administrateur", "Responsable", "Animateur", "Organisateur", "Moderateur",
					"PCT", "Guardian", "Guide", "Ami", "Aucun" };
			for (int i = 0; i < grades.length; i++) {
				if (args[1].equals(grades[i])) {
					value = true;
				}
			}

			if (value == false) {
				sender.sendMessage(PrefixMessage.PREFIX + "Le grade " + args[1] + " n'éxiste pas !");
				return true;
			}

			String prefix = null;
			String suffix = null;
			Player playersender = (Player) sender;
			
			Player target = Bukkit.getPlayerExact(args[0]);
			if (target == null || !target.isOnline()) {
				sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " n'a pas été trouvé");
				return true;
			}

			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard scoremain = manager.getMainScoreboard();
			Team team = scoremain.getTeam("PCT");

			if (team == null) {

				team = scoremain.registerNewTeam("PCT");
				team.setPrefix("");

			}
			
			

			team.addPlayer(target);

			File file = new File(main.getDataFolder(), "players/" + target.getUniqueId() + ".yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);

			String staff = args[1];

			switch (staff) {

			case "Fondateur":
				if (target.getName().equalsIgnoreCase("tazmaik") || target.getName().equalsIgnoreCase("Soul_Scythe")) {
					prefix = "§c§l[Fondateur]";
					suffix = "§b";
				} else {
					playersender.sendMessage(
							PrefixMessage.PREFIX + "Il n'y a que Tazmaik qui peut utiliser cette commande !");
					return true;
				}
				break;

			case "Administrateur":
				prefix = "§c[Admin]";
				suffix = "§b";
				break;

			case "Responsable":
				prefix = "§5[Responsable]";
				suffix = "§3";
				break;

			case "Animateur":
				prefix = "§9[Animateur]";
				suffix = "§d";
				break;

			case "Organisateur":
				prefix = "§9[Organisateur]";
				suffix = "§d";
				break;

			case "Moderateur":
				prefix = "§d[Modo]";
				suffix = "§3";
				break;

			case "PCT":
				prefix = "§2[PCT]";
				suffix = "§3";
				break;

			case "Guardian":
				prefix = "§a[Guardian]";
				suffix = "§3";
				break;

			case "Guide":
				prefix = "§7[Guide]";
				suffix = "§3";
				break;

			case "Ami":
				prefix = "§6[Ami]";
				suffix = "§3";
				break;

			default:
				prefix = "§e";
				suffix = "§f";
				break;
			}

			try {
				loadConfig(playersender, target, prefix, suffix, config, file, staff);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}

			return true;
		}
		return false;
	}

	public void loadConfig(Player playersender, Player target, String prefix, String suffix, FileConfiguration config,
			File file, String staff) throws IOException, InvalidConfigurationException {

		if (!file.exists()) {
			config.save(file);
		}

		config.load(file);
		config.set("general.staff", staff);
		config.set("general.prefix", prefix);
		config.set("general.suffix", suffix);
		config.save(file);

		target.setDisplayName(config.get("general.prefix") + target.getName() + config.get("general.suffix"));
		target.setPlayerListName(config.get("general.prefix") + target.getName() + config.get("general.suffix"));

		playersender.sendMessage(PrefixMessage.PREFIX + "Vous avez mis le grade " + config.get("general.staff")
				+ " au joueur " + target.getDisplayName() + ChatColor.YELLOW + " !");

		target.sendMessage(PrefixMessage.PREFIX + playersender.getDisplayName() + ChatColor.YELLOW
				+ " vous a mis le grade " + config.get("general.staff") + ChatColor.YELLOW + " !");

	}
}
