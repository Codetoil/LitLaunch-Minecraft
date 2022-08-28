/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.server;

import io.github.codetoil.litlaunch.api.IProjectObject;
import io.github.codetoil.litlaunch.minecraft.IMinecraftSoftware;
import io.github.codetoil.litlaunch.minecraft.world.IMinecraftWorld;

import java.util.Optional;

public interface IMinecraftServer extends IMinecraftSoftware, IProjectObject
{
    boolean isWorldLoaded();

    Optional<IMinecraftWorld> getWorldIfAvailable();
}
