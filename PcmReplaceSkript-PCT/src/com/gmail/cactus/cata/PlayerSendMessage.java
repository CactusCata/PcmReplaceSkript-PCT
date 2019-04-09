package com.gmail.cactus.cata;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerSendMessage implements Listener {

	private Main main;

	public PlayerSendMessage(Main main) {
		this.main = main;
	}

	@EventHandler
	public void SendMessage(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();
		event.setCancelled(true);

		File file = new File(main.getDataFolder(), "players/" + player.getUniqueId() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		if (player.hasPermission("pcm.pct.msg.color")) {
			Bukkit.broadcastMessage(config.get("general.prefix") + player.getName() + config.get("general.suffix")
					+ ": " + event.getMessage().replace('&', '§'));

		} else {
			Bukkit.broadcastMessage(config.get("general.prefix") + player.getName() + config.get("general.suffix")
					+ ": " + event.getMessage());
		}
	}

}
