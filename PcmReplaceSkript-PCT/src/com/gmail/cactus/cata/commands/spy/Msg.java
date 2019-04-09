package com.gmail.cactus.cata.commands.spy;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class Msg implements CommandExecutor {

	public static HashMap<Player, Player> rep = new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("w")
				|| cmd.getName().equalsIgnoreCase("m")) {

			if (args.length < 1) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le joueur !");
				return true;
			}

			if (args.length < 2) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le message !");
				return true;
			}

			Player target = Bukkit.getPlayerExact(args[0]);
			if (target == null || !target.isOnline()) {
				sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " n'a pas été trouvé !");
				return true;
			}

			if (target.getName().equals(sender.getName())) {
				sender.sendMessage(PrefixMessage.PREFIX + "Vous ne pouvez pas vous envoyer des messages à vous même !");
				return true;
			}

			String msg = "";
			for (int i = 1; i < args.length; i++) {
				msg += " " + args[i];
			}

			Player playersender = (Player) sender;

			playersender.sendMessage(
					"§7[" + playersender.getDisplayName() + "§7->" + target.getDisplayName() + "§7]" + msg);
			target.sendMessage("§7[" + playersender.getDisplayName() + "§7->" + target.getDisplayName() + "§7]" + msg);

			rep.put(target, (Player) sender);

			return true;

		}
		return false;
	}

}
