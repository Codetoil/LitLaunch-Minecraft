/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.world;

import io.github.codetoil.litlaunch.api.IProjectObject;
import io.github.codetoil.litlaunch.minecraft.chat.ChatStyle;
import io.github.codetoil.litlaunch.minecraft.dimensions.IDimensionType;

public interface IMinecraftWorld extends IProjectObject
{
	IDimensionType getDimensionsRunning();

	IDimensionType[] getDimensionsThatExist();

	void sendChatMessage(String message);

	void sendGeneralMessage(String message);

	void sendGeneralMessage(String message, ChatStyle pChatStyle);
}
