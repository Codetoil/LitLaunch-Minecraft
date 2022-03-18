/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.event;

import io.github.codetoil.litlaunch.api.event.LitEventType;

import java.util.HashMap;
import java.util.Map;

public class LitMinecraftEventTypes
{
	private static Map<String, LitEventType> types = new HashMap<>();

	public static final LitEventType DISCONNECT = getEnumFromString("Disconnect");
	public static final LitEventType CONSTRUCTION = getEnumFromString("Contruction");
	public static final LitEventType PREINIT = getEnumFromString("PreInit");
	public static final LitEventType INIT = getEnumFromString("Init");
	public static final LitEventType POSTINIT = getEnumFromString("PostInit");
	public static final LitEventType TESTSPAM = getEnumFromString("TestSpam");
	public static final LitEventType SERVERLOAD = getEnumFromString("ServerLoad");
	public static final LitEventType SERVERCONNECT = getEnumFromString("ServerConnect");
	public static final LitEventType CLIENTTICK = getEnumFromString("clientTick");
	public static final LitEventType SERVERTICK = getEnumFromString("serverTick");
	public static final LitEventType ONPACKET = getEnumFromString("onPacket");

	public static LitEventType getEnumFromString(String name) {
		if (types == null) {
			types = new HashMap<>();
		}
		LitEventType typeFromName = types.get(name);
		if (typeFromName == null) {
			typeFromName = new LitEventType(name);
			types.put(name, typeFromName);
		}
		return typeFromName;
	}
}