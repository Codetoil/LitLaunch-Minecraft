/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft;

import io.github.codetoil.litlaunch.api.litlaunch.ISoftware;
import io.github.codetoil.litlaunch.minecraft.chat.ChatStyle;

public interface IMinecraftSoftware extends ISoftware
{
	void sendAsChatMessage(String message);

	void notifyUser(String message);

	void notifyUser(String message, ChatStyle pChatStyle);
}
