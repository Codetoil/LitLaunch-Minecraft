/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.client;

import io.github.codetoil.litlaunch.minecraft.chat.ChatStyle;
import io.github.codetoil.litlaunch.minecraft.IMinecraftSoftware;

public interface IMinecraftClient extends IMinecraftSoftware
{
	String getVersion();

	void sendAsChatMessage(String message);

	void notifyUser(String message);

	void notifyUser(String message, ChatStyle pChatStyle);
}
