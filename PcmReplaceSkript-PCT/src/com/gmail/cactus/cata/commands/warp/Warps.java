package com.gmail.cactus.cata.commands.warp;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.cactus.cata.Main;
import com.gmail.cactus.cata.enums.PrefixMessage;

public class Warps implements CommandExecutor {

	private Main main;

	public Warps(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("warps")) {

			File file = new File(main.getDataFolder(), "warps.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);

			try {
				config.load(file);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}

			String warps = PrefixMessage.PREFIX + "Liste de tous les warps :";
			int i = 0;

			for (String key : config.getConfigurationSection("warps.").getKeys(false)) {
				warps += (i < 1) ? "" : ',';
				i++;
				warps += " " + key;
			}

			warps += " !";
			
			if(i == 0){
				sender.sendMessage(PrefixMessage.PREFIX + "Il n'y a aucun warp existant !");
				return true;
			}

			sender.sendMessage(warps);
			return true;

		}

		return false;
	}

}
