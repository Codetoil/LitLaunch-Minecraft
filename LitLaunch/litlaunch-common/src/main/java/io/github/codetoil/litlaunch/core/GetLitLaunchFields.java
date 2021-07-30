/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch.core;

import io.github.codetoil.litlaunch.api.*;
import io.github.codetoil.litlaunch.api.internal.ICommonProxy;
import io.github.codetoil.litlaunch.core.event.LitEventHandler;
import io.github.codetoil.litlaunch.modloader.LitClassLoader;
import io.github.codetoil.litlaunch.modloader.ModFinder;

import java.util.List;

/**
 * The front end of LitLaunch, allows to access to the api from a single class
 */
public class GetLitLaunchFields implements IGetLitLaunchFields {
    public String litlaunchVersion() {
        return LaunchCommon.VERSION;
    }

    public String litlaunchMod() {
        return LaunchCommon.MODID;
    }

    public ChainableMap<String, Object> emptyChainableMap() {
        return LaunchCommon.EMPTY;
    }

    public String minecraftVersion() {
        return LaunchCommon.getGetFields().minecraftVersion();
    }

    public ICommonProxy activeProxy() {
        return LaunchCommon.getCCproxy();
    }

    public IGetMinecraftFields getFields() {
        return LaunchCommon.getGetFields();
    }

    public IPreformMinecraftAction doThingObject() {
        return LaunchCommon.getDoThing();
    }

    public boolean isVerbose() {
        return LaunchCommon.isVerbose();
    }

    public double timeFrame() {
        return LaunchCommon.getTimeInit();
    }

    public Command.Side getSide() {
        return LaunchCommon.getSide();
    }

    public List<Class<?>> modsLoaded() {
        return ModFinder.validMods;
    }

    public LitClassLoader classLoader() {
        return ModFinder.litClassLoader;
    }

    public LitEventHandler commonEventHandler() {
        return LitEventHandler.COMMON;
    }

    public LitEventHandler clientEventHandler() {
        return LitEventHandler.CLIENT;
    }

    public LitEventHandler serverEventHandler() {
        return LitEventHandler.SERVER;
    }

    public double timeSinceBootstrap() {
        return LaunchCommon.getTimeInSeconds();
    }
}
