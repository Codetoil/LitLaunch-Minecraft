/*
 * Copyright (c) 2022.
 */

package io.github.codetoil.litlaunch.api.mods;

import io.github.codetoil.litlaunch.api.commands.CommandSide;
import io.github.codetoil.litlaunch.api.mods.event.ILitEventHandler;
import io.github.codetoil.litlaunch.api.litlaunch.ICommonProxy;
import io.github.codetoil.litlaunch.api.litlaunch.ILitClassLoader;

import java.util.List;

public interface IGetLitLaunchFields {
    String litlaunchVersion();

    String litlaunchMod();

    ICommonProxy activeProxy();

    boolean isVerbose();

    double timeFrame();

    CommandSide getSide();

    List<Class<?>> modsLoaded();

    ILitClassLoader classLoader();

    ILitEventHandler commonEventHandler();

    ILitEventHandler clientEventHandler();

    ILitEventHandler serverEventHandler();

    double timeSinceBootstrap();
}
