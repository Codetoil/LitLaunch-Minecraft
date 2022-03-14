/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc189.proxy;


import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.litlaunch.ICommonProxy;
import io.github.codetoil.litlaunch.minecraft.LaunchCommon;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ServerProxy189 implements ICommonProxy {
    @Override
    public void setGamePath() {
        LitLaunch.getLogger().info("LitLaunch Server Proxy Setting Game Path On LitLaunch!");
        LaunchCommon.setGamePath(FMLCommonHandler.instance().getMinecraftServerInstance().getFile(".").toPath());
    }

    @Override
    public void construction() {
        LitLaunch.getLogger().info("LitLaunch Server Proxy Constructing!");
    }

    @Override
    public void preInit() {
        LitLaunch.getLogger().info("LitLaunch Server Proxy PreInitializing!");
    }

    @Override
    public void init() {
        LitLaunch.getLogger().info("LitLaunch Server Proxy Initializing!");
    }

    @Override
    public void postInit() {
        LitLaunch.getLogger().info("LitLaunch Server Proxy PostInitializing!");
    }

    @Override
    public void serverLoad() {
        LitLaunch.getLogger().info("LitLaunch Server Proxy Loading Server!");
    }
}
