package io.github.codetoil.litlaunch._native.mc1144.proxy;


import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.internal.ICommonProxy;

public class ServerProxy1144 implements ICommonProxy {
    @Override
    public void setGamePath() {
        LitLaunch.getLogger().info("LitLaunch Server Proxy Setting Game Path On Lit Launch!");
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
