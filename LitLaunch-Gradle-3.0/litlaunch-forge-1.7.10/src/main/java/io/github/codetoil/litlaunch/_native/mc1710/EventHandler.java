/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1710;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.minecraft.event.LitEvent;
import io.github.codetoil.litlaunch.minecraft.event.LitEventHandler;

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
        //FMLClientHandler.instance().setPlayClient(new EventThrowingClientPlayNetHandler((NetHandlerPlayClient) event.handler));
    }
}
