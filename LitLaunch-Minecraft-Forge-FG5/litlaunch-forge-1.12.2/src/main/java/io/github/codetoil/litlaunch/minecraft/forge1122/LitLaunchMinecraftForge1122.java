/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.forge1122;

import io.github.codetoil.litlaunch.api.ISoftware;
import io.github.codetoil.litlaunch.minecraft.LitLaunch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = "litlaunch", version = LitLaunch.VERSION)
public class LitLaunchMinecraftForge1122 implements ISoftware
{

    public LitLaunchMinecraftForge1122() {

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {

    }

    @Override
    public String getVersion()
    {
        return LitLaunch.VERSION;
    }

    // public static final EventHandler handler = new EventHandler();

    /*
        if (FMLCommonHandler.instance().getSide().isClient()) {
            // LaunchCommon.setSide(Command.Side.CLIENT);
        } else if (FMLCommonHandler.instance().getSide().isServer()) {
            // LaunchCommon.setSide(Command.Side.SERVER);
        } else {
            throw new FailedBootstrapException("FML IS NOT SIDED!");
        }
        */
    /*
    public boolean setProxy() {
        boolean result = false;
        if (LaunchCommon.getCCproxy() != null) {
            LitLaunch.getLogger().error("Tried changing already-set proxy!");
            result = false;
        } else {
            Side side = FMLCommonHandler.instance().getSide();
            switch (side) {
                case CLIENT:
                    LaunchCommon.setCcproxy(new ClientProxy1122());
                    result = true;
                    break;
                case SERVER:
                    LaunchCommon.setCcproxy(new ServerProxy1122());
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
    */
    /*
        try {
            LitLaunch.getInstance().setPreformMinecraftAction(PreformMinecraftAction.INSTANCE);
            LitLaunch.getInstance().setMinecraftFields(GetMinecraftFields.INSTANCE);
            LaunchCommon.bootstrap(LogManager.getLogger(LaunchCommon.MODID),
                    getClass().getClassLoader(), this, Logger1122.getInstance());
        } catch (Throwable t) {
            FailedBootstrapException lFailedBootstrapException = new FailedBootstrapException();
            lFailedBootstrapException.initCause(t);
            throw lFailedBootstrapException;
        }
        LaunchCommon.preInit();
        */

    // LaunchCommon.init();
    // MinecraftForge.EVENT_BUS.register(handler);

    // LaunchCommon.postInit();

    //LaunchCommon.serverLoad();
        /*
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
                    LitLaunch.getLogger().error("Mod " + modClass + " does not have a method named \"getCommandList\". This is neccesary for the api to work! Skipping");
                }

            } catch (Throwable pThrowable) {
                pThrowable.printStackTrace();
            }

        });
        */
}
