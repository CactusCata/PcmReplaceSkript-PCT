package com.gmail.cactus.cata.commands.warp;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.gmail.cactus.cata.Main;
import com.gmail.cactus.cata.enums.PrefixMessage;

public class DelWarp implements CommandExecutor {

	private Main main;

	public DelWarp(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("delwarp")) {

			if (args.length == 0 || args[0] == null) {

				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le nom du warp !");
				return true;

			}

			Player player = (Player) sender;
			String warp = args[0];
			File file = new File(main.getDataFolder(), "warps.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);

			if (config.get("warps." + warp + ".x") == null) {
				player.sendMessage(PrefixMessage.PREFIX + "Le warp " + warp + " n'existe pas !");
				return true;
			}

			try {
				loadConfig(file, config, warp, player);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
				return true;
			}

			return true;

		}
		return false;
	}

	public void loadConfig(File file, FileConfiguration config, String warp, Player player)
			throws IOException, InvalidConfigurationException {

		config.load(file);
		config.set("warps." + warp, null); // null --> reset de la partie
		config.save(file);

		player.sendMessage(PrefixMessage.PREFIX + "Le warp " + warp + " a bien été supprimé !");

	}

}
