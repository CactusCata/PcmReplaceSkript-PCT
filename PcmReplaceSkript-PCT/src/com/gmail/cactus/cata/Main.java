package com.gmail.cactus.cata;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.cactus.cata.commands.other.Broadcast;
import com.gmail.cactus.cata.commands.other.Gamemode;
import com.gmail.cactus.cata.commands.other.SetStaff;
import com.gmail.cactus.cata.commands.other.Spawn;
import com.gmail.cactus.cata.commands.other.Speed;
import com.gmail.cactus.cata.commands.spy.Msg;
import com.gmail.cactus.cata.commands.spy.R;
import com.gmail.cactus.cata.commands.tp.Tpb;
import com.gmail.cactus.cata.commands.tp.Tppos;
import com.gmail.cactus.cata.commands.warp.DelWarp;
import com.gmail.cactus.cata.commands.warp.SetWarp;
import com.gmail.cactus.cata.commands.warp.Warp;
import com.gmail.cactus.cata.commands.warp.WarpInfo;
import com.gmail.cactus.cata.commands.warp.Warps;

public class Main extends JavaPlugin {

	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public void onEnable() {

		instance = this;
		PluginManager pm = getServer().getPluginManager();
		getConfig().options().copyDefaults(false);
		this.saveConfig();

		pm.registerEvents(new PlaceBlock(), (this));
		pm.registerEvents(new BreakBlock(), (this));
		pm.registerEvents(new JoinGame(this), (this));
		pm.registerEvents(new LeaveGame(this), (this));
		pm.registerEvents(new PlayerSendMessage(this), (this));
		pm.registerEvents(new Sign(), (this));
		getCommand("setstaff").setExecutor(new SetStaff(this));
		getCommand("broadcast").setExecutor(new Broadcast());
		getCommand("gamemode").setExecutor(new Gamemode());
		getCommand("setwarp").setExecutor(new SetWarp(this));
		getCommand("delwarp").setExecutor(new DelWarp(this));
		getCommand("warp").setExecutor(new Warp(this));
		getCommand("warps").setExecutor(new Warps(this));
		getCommand("warpinfo").setExecutor(new WarpInfo(this));
		getCommand("msg").setExecutor(new Msg());
		getCommand("r").setExecutor(new R());
		getCommand("speed").setExecutor(new Speed());
		getCommand("tppos").setExecutor(new Tppos());
		getCommand("tpb").setExecutor(new Tpb());
		getCommand("spawn").setExecutor(new Spawn());
	}

	public void onDisable() {

	}

}