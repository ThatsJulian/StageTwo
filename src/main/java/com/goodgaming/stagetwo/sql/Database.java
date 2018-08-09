package com.goodgaming.stagetwo.sql;

import com.goodgaming.stagetwo.StageTwo;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public abstract class Database {

    public Database() {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(StageTwo.getInstance(), () -> {
            initialize();
            update();
        });
    }

    protected abstract void initialize();

    protected abstract void update();
}
