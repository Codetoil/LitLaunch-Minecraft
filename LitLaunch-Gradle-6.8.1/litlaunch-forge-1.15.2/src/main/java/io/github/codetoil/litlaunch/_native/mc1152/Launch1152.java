package io.github.codetoil.litlaunch._native.mc1152;

import io.github.codetoil.litlaunch._native.mc1152.proxy.ClientProxy1152;
import io.github.codetoil.litlaunch._native.mc1152.proxy.ServerProxy1152;
import io.github.codetoil.litlaunch.api.Command;
import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.exceptions.FailedBootstrapException;
import io.github.codetoil.litlaunch.minecraft.INativeLaunch;
import io.github.codetoil.litlaunch.minecraft.LaunchCommon;
import io.github.codetoil.litlaunch.modloader.ModFinder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;

import java.util.List;

@Mod(LaunchCommon.MODID)
public class Launch1152 implements INativeLaunch {
    public static final String VERSION = GetMinecraftFields.INSTANCE.minecraftVersion() + "-" + LaunchCommon.VERSIONF;

    public Launch1152() {
        if (FMLEnvironment.dist.isClient()) {
            LaunchCommon.setSide(Command.Side.CLIENT);
        } else if (FMLEnvironment.dist.isDedicatedServer()) {
            LaunchCommon.setSide(Command.Side.SERVER);
        } else {
            throw new FailedBootstrapException("FML IS NOT SIDED!");
        }
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        MinecraftForge.EVENT_BUS.register(this);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverLoad);
    }

    public void setup(final FMLCommonSetupEvent event) {
        LaunchCommon.setGamePath(FMLLoader.getGamePath());
        try {
            LitLaunch.getInstance().setPreformMinecraftAction(PreformMinecraftAction.INSTANCE);
            LitLaunch.getInstance().setMinecraftFields(GetMinecraftFields.INSTANCE);
            LaunchCommon.bootstrap(LogManager.getLogger(LaunchCommon.MODID), getClass().getClassLoader(), this, Logger1152.getInstance());
        } catch (Throwable t) {
            FailedBootstrapException lFailedBootstrapException = new FailedBootstrapException();
            lFailedBootstrapException.initCause(t);
            throw lFailedBootstrapException;
        }
        LaunchCommon.preInit();
        LaunchCommon.init();
        LaunchCommon.postInit();
    }

    public void setupClient(final FMLClientSetupEvent event) {

    }

    public boolean setProxy() {
        boolean result;
        if (LaunchCommon.getCCproxy() != null) {
            LitLaunch.getLogger().error("Tried changing already-set proxy!");
            result = false;
        } else {
            Dist side = FMLEnvironment.dist;
            switch (side) {
                case CLIENT:
                    LaunchCommon.setCcproxy(new ClientProxy1152());
                    result = true;
                    break;
                case DEDICATED_SERVER:
                    LaunchCommon.setCcproxy(new ServerProxy1152());
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

    @SubscribeEvent
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
    }

}
