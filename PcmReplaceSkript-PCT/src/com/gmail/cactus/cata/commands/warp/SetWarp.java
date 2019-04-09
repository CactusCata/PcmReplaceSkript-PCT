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
import com.gmail.cactus.cata.maths.Maths;

public class SetWarp implements CommandExecutor {

	private Main main;

	public SetWarp(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setwarp")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
				return true;
			}

			if (args.length == 0 || args[0] == null) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le nom du warp !");
				return true;
			}

			Player p = (Player) sender;
			String world = p.getWorld().getName();
			String warp = args[0];
			double x = Maths.arrondidouble(p.getLocation().getX(), 2);
			double y = Maths.arrondidouble(p.getLocation().getY(), 2);
			double z = Maths.arrondidouble(p.getLocation().getZ(), 2);
			float pitch = Maths.arrondifloat(p.getLocation().getPitch(), 2);
			float yaw = Maths.arrondifloat(p.getLocation().getYaw(), 2);
			String password = null;

			if (args.length == 2) {

				password = args[1];
				p.sendMessage(PrefixMessage.PREFIX + "Le warp " + args[0] + " a été créer en x: " + x + ", y: " + y
						+ ", z: " + z + ", pitch: " + pitch + ", yaw: " + yaw + ", monde: " + world.toString()
						+ " avec le mot de passe : " + password + "!");

			} else {

				p.sendMessage(PrefixMessage.PREFIX + "Le warp " + args[0] + " a été créer en x: " + x + ", y: " + y
						+ ", z: " + z + ", pitch: " + pitch + ", yaw: " + yaw + ", monde: " + world.toString() + " !");

			}

			try {
				loadConfig(x, y, z, pitch, yaw, world, password, warp);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}

			return true;
		}
		return false;
	}

	public void loadConfig(double x, double y, double z, float pitch, float yaw, String world, String password,
			String warp) throws IOException, InvalidConfigurationException {

		File file = new File(main.getDataFolder(), "warps.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if (!file.exists())
			config.save(file);

		config.load(file);
		config.set("warps." + warp + ".x", x);
		config.set("warps." + warp + ".y", y);
		config.set("warps." + warp + ".z", z);
		config.set("warps." + warp + ".pitch", pitch);
		config.set("warps." + warp + ".yaw", yaw);
		config.set("warps." + warp + ".password", password);
		config.set("warps." + warp + ".monde", world);
		config.save(file);

	}

}
