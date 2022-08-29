/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.minecraft.event;

import io.github.codetoil.litlaunch.api.event.EventType;

import java.util.HashMap;
import java.util.Map;

public class LitMinecraftEventTypes
{
	private static Map<String, EventType> types = new HashMap<>();

	public static final EventType DISCONNECT = getEnumFromString("Disconnect");
	public static final EventType CONSTRUCTION = getEnumFromString("Contruction");
	public static final EventType PREINIT = getEnumFromString("PreInit");
	public static final EventType INIT = getEnumFromString("Init");
	public static final EventType POSTINIT = getEnumFromString("PostInit");
	public static final EventType TESTSPAM = getEnumFromString("TestSpam");
	public static final EventType SERVERLOAD = getEnumFromString("ServerLoad");
	public static final EventType SERVERCONNECT = getEnumFromString("ServerConnect");
	public static final EventType CLIENTTICK = getEnumFromString("clientTick");
	public static final EventType SERVERTICK = getEnumFromString("serverTick");
	public static final EventType ONPACKET = getEnumFromString("onPacket");

	public static EventType getEnumFromString(String name) {
		if (types == null) {
			types = new HashMap<>();
		}
		EventType typeFromName = types.get(name);
		if (typeFromName == null) {
			typeFromName = new EventType(name);
			types.put(name, typeFromName);
		}
		return typeFromName;
	}
}