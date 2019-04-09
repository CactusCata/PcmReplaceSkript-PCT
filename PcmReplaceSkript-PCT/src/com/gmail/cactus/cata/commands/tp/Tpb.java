package com.gmail.cactus.cata.commands.tp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class Tpb implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpb")) {

			if (args.length < 2) {
				sender.sendMessage(
						PrefixMessage.PREFIX + "Veillez pr�ciser quel joueur doit �tre t�l�port� � quel joueur !");
				return true;
			}

			Player player1 = Bukkit.getPlayerExact(args[0]);
			Player player2 = Bukkit.getPlayerExact(args[1]);
			if (player1 == null || !player1.isOnline()) {
				sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " n'a pas �t� trouv�");
				return true;
			}
			if (player2 == null || !player2.isOnline()) {
				sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[1] + " n'a pas �t� trouv�");
				return true;
			}

			player1.teleport(player2.getLocation());
			Player playersender = (Player) sender;

			player1.sendMessage(PrefixMessage.PREFIX + "Vous avez �t� t�l�port� sur " + player2.getDisplayName()
					+ " par " + playersender.getDisplayName() + " !");
			player2.sendMessage(PrefixMessage.PREFIX + player1.getDisplayName() + " a �t� t�l�port� sur vous par "
					+ playersender.getDisplayName() + " !");
			sender.sendMessage(PrefixMessage.PREFIX + player1.getDisplayName() + " a �t� t�l�port� sur "
					+ player2.getDisplayName() + " !");
			return true;

		}
		return false;
	}

}
