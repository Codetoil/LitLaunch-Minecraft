package io.github.codetoil.litlaunch.api;

import io.github.codetoil.litlaunch.api.event.ILitEventHandler;
import io.github.codetoil.litlaunch.api.internal.ICommonProxy;
import io.github.codetoil.litlaunch.api.internal.ILitClassLoader;

import java.util.List;

public interface IGetLitLaunchFields {
    String litlaunchVersion();

    String litlaunchMod();

    ChainableMap<String, Object> emptyChainableMap();

    ICommonProxy activeProxy();

    boolean isVerbose();

    double timeFrame();

    Command.Side getSide();

    List<Class<?>> modsLoaded();

    ILitClassLoader classLoader();

    ILitEventHandler commonEventHandler();

    ILitEventHandler clientEventHandler();

    ILitEventHandler serverEventHandler();

    double timeSinceBootstrap();
}
