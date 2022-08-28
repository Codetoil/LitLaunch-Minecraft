/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft;

import io.github.codetoil.litlaunch.api.ILibrary;
import io.github.codetoil.litlaunch.api.event.LitEventHandler;

public class LitLaunch implements ILibrary
{
	public final static LitEventHandler COMMON = new LitEventHandler("Common");
	public final static String VERSION = "0.0.5+build.1";
}
