package com.gmail.cactus.cata.commands.other;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class Broadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("broadcast") || cmd.getName().equalsIgnoreCase("bcast")) {

			if (args.length == 0) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez pr�ciser un message !");
				return true;
			}

			String broadcast = "";

			for (int i = 0; i < args.length; i++) {
				broadcast += " " + args[i];
			}

			Bukkit.broadcastMessage(broadcast.replace('&', '�'));

			return true;

		}
		return false;
	}
}
