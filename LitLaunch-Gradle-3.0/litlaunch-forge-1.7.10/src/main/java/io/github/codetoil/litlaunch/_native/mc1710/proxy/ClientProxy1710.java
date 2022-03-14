/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1710.proxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.codetoil.litlaunch._native.mc1710.CommandNew;
import io.github.codetoil.litlaunch.api.Command;
import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.litlaunch.ICommonProxy;
import io.github.codetoil.litlaunch.minecraft.LaunchCommon;
import io.github.codetoil.litlaunch.modloader.ModFinder;
import net.minecraft.client.Minecraft;

import java.util.List;

@SideOnly(Side.CLIENT)
public class ClientProxy1710 implements ICommonProxy {
    @Override
    public void setGamePath() {
        LitLaunch.getLogger().info("LitLaunch Client Proxy Setting Game Path On Lit Launch!");
        LaunchCommon.setGamePath(Minecraft.getMinecraft().mcDataDir.toPath());
    }

    @Override
    public void construction() {
        LitLaunch.getLogger().info("LitLaunch Client Proxy Constructing!");
    }

    @Override
    public void preInit() {
        LitLaunch.getLogger().info("LitLaunch Client Proxy PreInitializing!");
        ModFinder.validMods.forEach((modClass) -> {
            try {
                Object oCommands = modClass.getMethod("getCommandList").invoke(modClass.newInstance());
                List lCommands;
                if (oCommands instanceof List) {
                    lCommands = (List) oCommands;
                    lCommands.forEach((command) -> {
                        if (command instanceof Command) {
                            if (Command.Side.CLIENT.equals(((Command) command).side) || Command.Side.BOTH.equals(((Command) command).side)) {
                                net.minecraftforge.client.ClientCommandHandler.instance.registerCommand(new CommandNew((Command) command));
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

    @Override
    public void init() {
        LitLaunch.getLogger().info("LitLaunch Client Proxy Initializing!");

    }

    @Override
    public void postInit() {
        LitLaunch.getLogger().info("LitLaunch Client Proxy PostInitializing!");

    }

    @Override
    public void serverLoad() {
        LitLaunch.getLogger().info("LitLaunch Client Proxy Loading Server!");

    }
}
