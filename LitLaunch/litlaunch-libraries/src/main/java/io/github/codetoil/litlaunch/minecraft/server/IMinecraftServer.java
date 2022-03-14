/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.server;

import io.github.codetoil.litlaunch.minecraft.chat.ChatStyle;
import io.github.codetoil.litlaunch.minecraft.IMinecraftSoftware;
import io.github.codetoil.litlaunch.minecraft.dimensions.IDimensionType;

public interface IMinecraftServer extends IMinecraftSoftware
{
    IDimensionType getDimensionsRunning();

    IDimensionType[] getDimensionsExisting();

    String getVersion();

    void sendAsChatMessage(String message);

    void notifyUser(String message);

    void notifyUser(String message, ChatStyle pChatStyle);
}
