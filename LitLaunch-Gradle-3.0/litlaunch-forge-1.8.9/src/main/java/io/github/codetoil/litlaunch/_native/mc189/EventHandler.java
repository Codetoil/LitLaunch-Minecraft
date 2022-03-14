/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc189;

import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.minecraft.event.LitEvent;
import io.github.codetoil.litlaunch.minecraft.event.LitEventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventHandler {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            LitEventHandler.CLIENT.post(new LitEvent(this, LitEvent.TYPE.CLIENTTICK, LitLaunch.emptyChainableMap()), true);
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            LitEventHandler.SERVER.post(new LitEvent(this, LitEvent.TYPE.SERVERTICK), true);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void serverConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        LitEventHandler.CLIENT.post(new LitEvent(EventHandler.class, LitEvent.TYPE.SERVERCONNECT));
    }
}
