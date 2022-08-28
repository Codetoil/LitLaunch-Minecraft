/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.fabric1165;

import io.github.codetoil.litlaunch.api.ISoftware;
import io.github.codetoil.litlaunch.minecraft.LitLaunch;
import net.fabricmc.api.ModInitializer;

public class LitLaunchFabric1165 implements ModInitializer, ISoftware
{
	@Override
	public void onInitialize() {

	}

	@Override
	public String getVersion()
	{
		return LitLaunch.VERSION;
	}
}
