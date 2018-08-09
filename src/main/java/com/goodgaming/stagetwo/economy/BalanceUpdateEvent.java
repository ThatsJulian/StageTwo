package com.goodgaming.stagetwo.economy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class BalanceUpdateEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private UUID uuid;
    private Integer balance;

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
