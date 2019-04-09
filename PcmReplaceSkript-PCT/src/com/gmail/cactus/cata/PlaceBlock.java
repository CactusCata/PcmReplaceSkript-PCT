package com.gmail.cactus.cata;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gmail.cactus.cata.enums.PrefixMessage;

public class PlaceBlock implements Listener {

	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event) {

		Player player = event.getPlayer();
		
		if(!(player.hasPermission("pcm.pct.placeblock"))){
			player.sendMessage(PrefixMessage.PREFIX + "Vous n'avez pas la permission de build !");
			event.setCancelled(true);
		}
		
	}

}
