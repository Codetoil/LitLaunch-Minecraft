/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1710;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import io.github.codetoil.litlaunch._native.mc1710.proxy.ClientProxy1710;
import io.github.codetoil.litlaunch._native.mc1710.proxy.ServerProxy1710;
import io.github.codetoil.litlaunch.api.Command;
import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.exceptions.FailedBootstrapException;
import io.github.codetoil.litlaunch.minecraft.INativeLaunch;
import io.github.codetoil.litlaunch.minecraft.LaunchCommon;
import io.github.codetoil.litlaunch.modloader.ModFinder;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;

import java.util.List;

@Mod(modid = LaunchCommon.MODID, version = Launch1710.VERSION)
public class Launch1710 implements INativeLaunch {
    public static final String VERSION = MinecraftForge.MC_VERSION + "-" + LaunchCommon.VERSIONF;
    public static final EventHandler handler = new EventHandler();


    public Launch1710() {
        if (FMLCommonHandler.instance().getSide().isClient()) {
            LaunchCommon.setSide(Command.Side.CLIENT);
        } else if (FMLCommonHandler.instance().getSide().isServer()) {
            LaunchCommon.setSide(Command.Side.SERVER);
        } else {
            throw new FailedBootstrapException("FML IS NOT SIDED!");
        }
    }

    public boolean setProxy() {
        boolean result;
        if (LaunchCommon.getCCproxy() != null) {
            LitLaunch.getLogger().error("Tried changing already-set proxy!");
            result = false;
        } else {
            Side side = FMLCommonHandler.instance().getSide();
            switch (side) {
                case CLIENT:
                    LaunchCommon.setCcproxy(new ClientProxy1710());
                    result = true;
                    break;
                case SERVER:
                    LaunchCommon.setCcproxy(new ServerProxy1710());
                    result = true;
                    break;
                default:
                    LitLaunch.getLogger().error("FML is not sided(client vs server). This should not happen!");
                    result = false;
                    break;
            }
        }
        return result;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        try {
            LitLaunch.getInstance().setPreformMinecraftAction(PreformMinecraftAction.INSTANCE);
            LitLaunch.getInstance().setMinecraftFields(GetMinecraftFields.INSTANCE);
            LaunchCommon.bootstrap(LogManager.getLogger(LaunchCommon.MODID),
                    getClass().getClassLoader(), this, Logger1710.getInstance());
        } catch (Throwable t) {
            FailedBootstrapException lFailedBootstrapException = new FailedBootstrapException();
            lFailedBootstrapException.initCause(t);
            throw lFailedBootstrapException;
        }
        LaunchCommon.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LaunchCommon.init();
        MinecraftForge.EVENT_BUS.register(handler);
        FMLCommonHandler.instance().bus().register(handler);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        LaunchCommon.postInit();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        LaunchCommon.serverLoad();
        ModFinder.validMods.forEach((modClass) -> {
            try {
                Object oCommands = modClass.getMethod("getCommandList").invoke(modClass.newInstance());
                List lCommands;
                if (oCommands instanceof List) {
                    lCommands = (List) oCommands;
                    lCommands.forEach((command) -> {
                        if (command instanceof Command) {
                            if (Command.Side.SERVER.equals(((Command) command).side) || Command.Side.BOTH.equals(((Command) command).side)) {
                                event.registerServerCommand(new CommandNew((Command) command));
                            }
                        }
                    });
                } else {
                    LitLaunch.getLogger().error("Mod " + modClass + " does not have a method named \"commandList\". This is neccesary for the api to work though. Skipping!");
                }

            } catch (Throwable pThrowable) {
                pThrowable.printStackTrace();
            }

        });
    }
}
