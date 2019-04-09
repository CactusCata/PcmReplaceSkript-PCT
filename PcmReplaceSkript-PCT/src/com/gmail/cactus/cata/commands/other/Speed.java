package com.gmail.cactus.cata.commands.other;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class Speed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("speed")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER + "");
				return true;
			}

			if (args.length < 1) {
				sender.sendMessage(PrefixMessage.PREFIX + "Vous devez indiquer la puissance su speed !");
				return true;
			}

			Player p = (Player) sender;

			try {
				if (Float.parseFloat(args[0]) > 10) {
					p.sendMessage(PrefixMessage.PREFIX + "La valeur de vitesse " + args[0] + " est trop grande !");
					return true;
				} else if (Float.parseFloat(args[0]) < 0) {
					p.sendMessage(PrefixMessage.PREFIX + "La valeur de vitesse " + args[0] + " est trop petite !");
					return true;
				}
			} catch (java.lang.NumberFormatException e) {
				p.sendMessage(PrefixMessage.PREFIX
						+ "La commande doit s'effectuer sous la forme: �a/speed �6<�cnombre�6> �b!");
				return true;
			}

			p.setWalkSpeed(Float.parseFloat(args[0]) / 10);
			p.setFlySpeed(Float.parseFloat(args[0]) / 20);
			p.sendMessage(PrefixMessage.PREFIX + "Votre vitesse � �t� mise � " + args[0] + " ! (par d�faut : 2)");
			return true;

		}
		return false;
	}

}
