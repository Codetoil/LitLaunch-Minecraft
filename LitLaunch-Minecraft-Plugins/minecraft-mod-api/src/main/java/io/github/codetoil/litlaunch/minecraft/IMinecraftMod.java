/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft;

import io.github.codetoil.litlaunch.api.EnumSoftwareModRequirementStatus;
import io.github.codetoil.litlaunch.api.ILitPlugin;
import io.github.codetoil.litlaunch.api.event.LitEventHandler;

public interface IMinecraftMod extends LitEventHandler.ILitEventListener, ILitPlugin
{
    EnumSoftwareModRequirementStatus onClient();

    EnumSoftwareModRequirementStatus onServer();

    String getModID();

    String getVersion();
}
