package com.gmail.cactus.cata.commands.other;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("spawn")) {

			if (!(sender instanceof Player)) {

				sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
				return true;

			}

			Player player = (Player) sender;
			World world = Bukkit.getWorld("world");
			try {
				player.teleport(new Location(world, -95, 13, -138));
			} catch (java.lang.NullPointerException e) {
				player.sendMessage(PrefixMessage.PREFIX + "Le monde \"world\" n'existe pas !");
				return true;
			}

		}

		return false;
	}

}
