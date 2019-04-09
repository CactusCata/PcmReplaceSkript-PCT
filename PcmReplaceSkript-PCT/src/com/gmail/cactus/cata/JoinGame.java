package com.gmail.cactus.cata;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class JoinGame implements Listener {

	Main main;

	public JoinGame(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		player.sendMessage(PrefixMessage.PREFIX + "Bienvenue à toi " + player.getDisplayName() + ChatColor.YELLOW
				+ " sur le monde pct !");
		if (!(player.isOp())) {
			player.teleport(new Location(player.getWorld(), -0.5d, 65.2d, -0.5d, 0.0f, 10.0f));
		}
		player.setGameMode(GameMode.CREATIVE);

		File file = new File(main.getDataFolder(), "players/" + player.getUniqueId() + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		try {
			loadConfig(config, file, player);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}

	}

	public void loadConfig(FileConfiguration config, File file, Player player)
			throws IOException, InvalidConfigurationException {

		Calendar calendrier = Calendar.getInstance();

		if (!file.exists()) {
			config.set("general.firstlogin",
					calendrier.get(Calendar.HOUR_OF_DAY) + ":" + calendrier.get(Calendar.MINUTE) + ":"
							+ calendrier.get(Calendar.SECOND) + " " + calendrier.get(Calendar.DAY_OF_MONTH) + "/"
							+ calendrier.get(Calendar.MONTH) + 1 + "/" + calendrier.get(Calendar.YEAR));
			config.set("stats.uuid", player.getUniqueId().toString());
			config.save(file);
		}

		config.load(file);
		config.set("general.pseudo", player.getCustomName());
		config.set("general.lastip", player.getAddress().getHostName());
		config.set("general.lastlogin",
				calendrier.get(Calendar.HOUR_OF_DAY) + ":" + calendrier.get(Calendar.MINUTE) + ":"
						+ calendrier.get(Calendar.SECOND) + " " + calendrier.get(Calendar.DAY_OF_MONTH) + "/"
						+ calendrier.get(Calendar.MONTH) + 1 + "/" + calendrier.get(Calendar.YEAR));
		config.save(file);

	}

}
