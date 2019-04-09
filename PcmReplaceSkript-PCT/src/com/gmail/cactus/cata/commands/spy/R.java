package com.gmail.cactus.cata.commands.spy;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class R extends Msg implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (cmd.getName().equalsIgnoreCase("r")) {

			if (args.length < 0) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez préciser le message");
				return true;
			}

			String msg = "";
			for (int i = 0; i < args.length; i++) {
				msg += " " + args[i];
			}

			Player p = rep.get(sender);
			Player playersender = (Player) sender;

			try {

				playersender
						.sendMessage("§7[" + playersender.getDisplayName() + "§7->" + p.getDisplayName() + "§7]" + msg);
				p.sendMessage("§7[" + playersender.getDisplayName() + "§7->" + p.getDisplayName() + "§7]" + msg);

			} catch (java.lang.NullPointerException e) {
				sender.sendMessage(PrefixMessage.PREFIX + "Veillez d'abord parler à quelqu'un !");
				return true;
			}

			rep.put(p, playersender);

			return true;

		}

		return false;
	}

}
