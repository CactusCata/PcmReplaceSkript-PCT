package com.gmail.cactus.cata;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.gmail.cactus.cata.maths.Maths;

public class LeaveGame implements Listener {

	public LeaveGame(Main main) {
		this.main = main;
	}

	private Main main;

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {

		Player p = event.getPlayer();
		double x = Maths.arrondidouble(p.getLocation().getX(), 2);
		double y = Maths.arrondidouble(p.getLocation().getY(), 2);
		double z = Maths.arrondidouble(p.getLocation().getZ(), 2);
		double pitch = Maths.arrondifloat(p.getLocation().getPitch(), 2);
		double yaw = Maths.arrondifloat(p.getLocation().getYaw(), 2);
		UUID uuid = p.getUniqueId();

		try {
			loadConfig(x, y, z, pitch, yaw, uuid);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		event.setQuitMessage("");

	}

	public void loadConfig(double x, double y, double z, double pitch, double yaw, UUID uuid)
			throws IOException, InvalidConfigurationException {

		File file = new File(main.getDataFolder(), "players/" + uuid + ".yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		Calendar calendrier = Calendar.getInstance();

		config.load(file);
		config.set("general.lastlogin",
				calendrier.get(Calendar.HOUR_OF_DAY) + ":" + calendrier.get(Calendar.MINUTE) + ":"
						+ calendrier.get(Calendar.SECOND) + " " + calendrier.get(Calendar.DAY_OF_MONTH) + "/"
						+ calendrier.get(Calendar.MONTH) + 1 + "/" + calendrier.get(Calendar.YEAR));
		config.set("stats.lastposition.x", x);
		config.set("stats.lastposition.y", y);
		config.set("stats.lastposition.z", z);
		config.set("stats.lastposition.pitch", pitch);
		config.set("stats.lastposition.yaw", yaw);
		config.save(file);

	}

}
