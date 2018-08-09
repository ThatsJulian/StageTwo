package com.goodgaming.stagetwo.nms;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList;

import java.lang.reflect.Field;

/*
TO BE HONEST WITH GOOD-GAMING, I HAVE NEVER LIKE LOOKED HEAVILY INTO NMS.
WHICH IS A BIG MISTAKE ON MY PART AND I KNOW YOU WANT TO SEE HOW I WORK WITH THIS
BUT IF I DO GET PAST THIS STAGE, I WOULD LOVE IF ONE OF YOU MORE MATURE DEVELOPERS CAN SHOW ME
AROUND THIS AREA THAT I NEVER SUNK MY TEETH INTO.
 */
public class TestEntity extends EntityVillager {

    public TestEntity(World world) {
        super (world);


        try {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);

            bField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            bField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(goalSelector, new UnsafeList<PathfinderGoalSelector>());
            cField.set(targetSelector, new UnsafeList<PathfinderGoalSelector>());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(1, new PathfinderGoalLookAtTradingPlayer(this));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.6D));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityHuman.class, 3.0F, 1.0F));
        this.goalSelector.a(9, new PathfinderGoalInteract(this, EntityVillager.class, 5.0F, 0.02F));
        this.goalSelector.a(9, new PathfinderGoalRandomStroll(this, 0.6D));
        this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
    }

    @Override
    public void move(double d0, double d1, double d2) {
        return;
    }

    @Override
    public void collide(Entity entity) {
        return;
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return false;
    }

}
