/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1144;

import io.github.codetoil.litlaunch.core.LaunchCommon;
import io.github.codetoil.litlaunch.core.event.LitEvent;
import io.github.codetoil.litlaunch.core.event.LitEventHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkEvent;

@Mod.EventBusSubscriber(modid = LaunchCommon.MODID)
public class EventHandler {
    @SubscribeEvent
    public static void ServerConnect(NetworkEvent.ClientCustomPayloadLoginEvent event) {
        LitEventHandler.CLIENT.post(new LitEvent(EventHandler.class, LitEvent.TYPE.SERVERCONNECT, LaunchCommon.EMPTY));
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            LitEventHandler.CLIENT.post(new LitEvent(EventHandler.class, LitEvent.TYPE.CLIENTTICK, LaunchCommon.EMPTY), true);
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            LitEventHandler.SERVER.post(new LitEvent(EventHandler.class, LitEvent.TYPE.SERVERTICK, LaunchCommon.EMPTY), true);
        }
    }
}
