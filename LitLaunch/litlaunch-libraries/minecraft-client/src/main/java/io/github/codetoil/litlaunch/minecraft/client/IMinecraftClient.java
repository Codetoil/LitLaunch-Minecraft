/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.client;

import io.github.codetoil.litlaunch.api.IProjectObject;
import io.github.codetoil.litlaunch.minecraft.IMinecraftSoftware;
import io.github.codetoil.litlaunch.minecraft.world.IMinecraftWorld;

import java.util.Optional;

public interface IMinecraftClient extends IMinecraftSoftware, IProjectObject
{
	boolean isWorldLoaded();

	Optional<IMinecraftWorld> getWorldIfAvailable();
}
