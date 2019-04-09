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

public class WarpInfo implements CommandExecutor {

	private Main main;

	public WarpInfo(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("warpinfo")) {

			if (args.length == 0) {

				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le warp !");
				return true;

			}

			File file = new File(main.getDataFolder(), "warps.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(file);

			String warp = args[0];

			try {
				config.load(file);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			String x = config.get("warps." + warp + ".x").toString();
			String y = config.get("warps." + warp + ".y").toString();
			String z = config.get("warps." + warp + ".z").toString();
			String pitch = config.get("warps." + warp + ".pitch").toString();
			String yaw = config.get("warps." + warp + ".yaw").toString();
			String monde = config.get("warps." + warp + ".monde").toString();
			if (config.get("warps." + warp + ".password") != null) {
				String password = config.get("warps." + warp + ".password").toString();
				sender.sendMessage(PrefixMessage.PREFIX + "Le warp " + warp + " a comme info :\n" + "x: " + x + "\ny: "
						+ y + "\nz: " + z + "\npitch: " + pitch + "\nyaw: " + yaw + "\npassword: " + password
						+ "\nmonde: " + monde);
			} else {

				sender.sendMessage(PrefixMessage.PREFIX + "Le warp " + warp + " a comme info :\n" + "x: " + x + "\ny: "
						+ y + "\nz: " + z + "\npitch: " + pitch + "\nyaw: " + yaw + "\nmonde: " + monde);

			}

			return true;

		}

		return false;
	}

}
