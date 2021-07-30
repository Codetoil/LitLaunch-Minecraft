/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.bukkit;

import io.github.codetoil.litlaunch.api.IGetMinecraftFields;
import io.github.codetoil.litlaunch.api.LitLaunch;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class GetMinecraftFields implements IGetMinecraftFields {
    public static final IGetMinecraftFields INSTANCE = new GetMinecraftFields();

    private GetMinecraftFields() {
    }

    @Override
    public synchronized long getTotalWorldTime(int dimension) {
        long result = -1;
        try {
            World world = Bukkit.getWorld(getDimensionStringFromID(dimension));
            return world.getFullTime();
        } catch (Throwable e) {
            LitLaunch.getLogger().error("Something went wrong!");
            e.printStackTrace();
        }

        return result;
    }

    private String getDimensionStringFromID(int id) {
        String type;
        switch (id) {
            case -1:
                type = "minecraft:the_nether";
                break;
            case 0:
                type = "minecraft:overworld";
                break;
            case 1:
                type = "minecraft:the_ends";
                break;
            default:
                return "";
        }
        return type;
    }

    //Client Thing
    @Override
    public int getDimRunning() {
        throw new IllegalStateException("This should not be running on a client!");
    }

    @Override
    public int[] getDimsAvailable() {
        return new int[]{-1, 0, 1};
    }

    @Override
    public String minecraftVersion() {
        return "unknown";
    }
}
