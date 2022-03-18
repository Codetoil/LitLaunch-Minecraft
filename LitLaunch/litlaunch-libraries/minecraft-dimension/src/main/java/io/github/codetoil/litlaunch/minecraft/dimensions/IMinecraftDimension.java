/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.dimensions;

import io.github.codetoil.litlaunch.api.IProjectObject;

public interface IMinecraftDimension extends IProjectObject
{
	IDimensionType getDimensionType();

	long getTime();

	long getTimeOfDay();
}
