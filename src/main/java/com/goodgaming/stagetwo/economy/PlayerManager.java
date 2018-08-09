package com.goodgaming.stagetwo.economy;

import com.goodgaming.stagetwo.StageTwo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerManager implements Listener {

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event) {
        StageTwo.getInstance().getEcoDatabase().addPlayer(event.getUniqueId());
    }

}
