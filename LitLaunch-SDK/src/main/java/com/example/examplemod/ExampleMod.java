package com.example.examplemod;

import com.google.common.collect.Lists;
import io.github.codetoil.litlaunch.api.*;
import io.github.codetoil.litlaunch.api.event.ILitEvent;
import io.github.codetoil.litlaunch.api.event.ILitEventHandler;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ExampleMod implements IMod, ILitEventHandler.IEventListener {
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";

    public final static Object INSTANCE = new ExampleMod(); // LitLaunch creates a random instance to access this instance. Be sure to only have one main instance.
    public final static ILitEventHandler EVENTS; // Event handler for this mod!

    static {
        try {
            EVENTS = (ILitEventHandler) LitLaunch.getConstructorMap().apply(ILitEventHandler.class).newInstance(MODID);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to load " + MODID);
        }
        if (LitLaunch.getLitLaunchFields().getSide() == null) {
            LitLaunch.getLogger().error("LitLaunch is not sided... the game will most likely crash");
        }

    }

    @Override
    public EnumRequireOrNot onClient() {
        return EnumRequireOrNot.COMPATIBLE;
    }

    @Override
    public EnumRequireOrNot onServer() {
        return EnumRequireOrNot.COMPATIBLE;
    }

    @Override
    public String getModID() {
        return MODID;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public List<Command> getCommandList() {
        return Lists.newArrayList();
    }

    @Override
    public void construction() {
        LitLaunch.getLogger().info("On construction");
    }

    @Override
    public void preInit() {
        LitLaunch.getLogger().info("On preinitialization");
    }

    @Override
    public void init() {
        LitLaunch.getLogger().info("On initialization");
    }

    @Override
    public void postInit() {
        LitLaunch.getLogger().info("On postinitialization");
    }

    @Override
    public void serverLoad() {
        LitLaunch.getLogger().info("On server load");

    }

    public void serverConnect() {
        LitLaunch.getLogger().info("On server connect");
    }

    @Override
    public IMod getModINSTANCE() {
        return (IMod) INSTANCE;
    }

    @Override
    public void receivedEvent(ILitEvent event, ILitEventHandler handler) {
        switch (event.getType().getName()) {
            case "Construction":
                construction();
                break;
            case "PreInit":
                preInit();
                break;
            case "Init":
                init();
                break;
            case "PostInit":
                postInit();
                break;
            case "ServerLoad":
                serverLoad();
                break;
            case "ServerConnect":
                serverConnect();
                break;
            default:
                break;
        }
    }

    @Override
    public ILitEventHandler.IEventListener getListener() {
        return (ILitEventHandler.IEventListener) INSTANCE;
    }
}
