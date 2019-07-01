/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1_13;

import io.github.codetoil.litlaunch.core.event.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.network.NetworkEvent;

public class EventHandler
{
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		if (event.phase.equals(TickEvent.Phase.END)) {
			LitEventHandler.COMMON.post(new LitEvent(this, "clientTick"), true);
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.DEDICATED_SERVER)
	public void onServerTick(TickEvent.ServerTickEvent event)
	{
		if (event.phase.equals(TickEvent.Phase.END)) {
			LitEventHandler.COMMON.post(new LitEvent(this, "serverTick"), true);
		}
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void ServerConnect(NetworkEvent.ClientCustomPayloadLoginEvent event)
	{
		LitEventHandler.COMMON.post(new LitEvent(EventHandler.class, "ServerConnect"));
	}
}
