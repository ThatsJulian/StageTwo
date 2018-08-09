package com.goodgaming.stagetwo;

import com.goodgaming.stagetwo.economy.BalanceCommand;
import com.goodgaming.stagetwo.economy.EcoCommand;
import com.goodgaming.stagetwo.economy.EcoDatabase;
import com.goodgaming.stagetwo.economy.PlayerManager;
import com.goodgaming.stagetwo.nms.TestEntity;
import com.goodgaming.stagetwo.world.WorldManager;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.EntityVillager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public final class StageTwo extends JavaPlugin {

    @Getter private static StageTwo instance;

    private HikariDataSource hikariDataSource;
    private EcoDatabase ecoDatabase;

    @Override
    public void onEnable() {
        instance = this;
        hikariDataSource = new HikariDataSource();

        ecoDatabase = new EcoDatabase();

        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
        getServer().getPluginManager().registerEvents(new WorldManager(), this);

        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("eco").setExecutor(new EcoCommand());

        registerEntity("CVillager", 120, EntityVillager.class, TestEntity.class);
    }

    @Override
    public void onDisable() {}

    public void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        try {

            List<Map<?, ?>> dataMap = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()){
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())){
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                }
            }

            if (dataMap.get(2).containsKey(id)){
                dataMap.get(0).remove(name);
                dataMap.get(2).remove(id);
            }

            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
