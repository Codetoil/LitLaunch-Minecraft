/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.forge1165;

import io.github.codetoil.litlaunch.api.ISoftware;
import io.github.codetoil.litlaunch.minecraft.LitLaunch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

import java.util.List;

@Mod("litlaunch")
public class LitLaunchMinecraftForge1165 implements ISoftware
{
    public LitLaunchMinecraftForge1165() {
        if (FMLEnvironment.dist.isClient()) {

        } else if (FMLEnvironment.dist.isDedicatedServer()) {

        } else {

        }
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLCommonSetupEvent event) {
    }

    public void setupClient(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void serverLoad(FMLServerStartingEvent event) {

    }

    @Override
    public String getVersion()
    {
        return LitLaunch.VERSION;
    }

    //LaunchCommon.setSide(Command.Side.CLIENT);
    //LaunchCommon.setSide(Command.Side.SERVER);
    //throw new FailedBootstrapException("FML IS NOT SIDED!");
    //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverLoad);
        /*
        LaunchCommon.setGamePath(FMLLoader.getGamePath());
        try {
            LitLaunch.getInstance().setPreformMinecraftAction(PreformMinecraftAction.INSTANCE);
            LitLaunch.getInstance().setMinecraftFields(GetMinecraftFields.INSTANCE);
            LaunchCommon.bootstrap(LogManager.getLogger(LaunchCommon.MODID), getClass().getClassLoader(), this, Logger1165.getInstance());
        } catch (Throwable t) {
            FailedBootstrapException lFailedBootstrapException = new FailedBootstrapException();
            lFailedBootstrapException.initCause(t);
            throw lFailedBootstrapException;
        }
        LaunchCommon.preInit();
        LaunchCommon.init();
        LaunchCommon.postInit();
        */
    /*
    public boolean setProxy() {
        boolean result;
        if (LaunchCommon.getCCproxy() != null) {
            LitLaunch.getLogger().error("Tried changing already-set proxy!");
            result = false;
        } else {
            Dist side = FMLEnvironment.dist;
            switch (side) {
                case CLIENT:
                    LaunchCommon.setCcproxy(new ClientProxy1165());
                    result = true;
                    break;
                case DEDICATED_SERVER:
                    LaunchCommon.setCcproxy(new ServerProxy1165());
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
                                new CommandNew((Command) command, event.getCommandDispatcher());
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
        */

}
