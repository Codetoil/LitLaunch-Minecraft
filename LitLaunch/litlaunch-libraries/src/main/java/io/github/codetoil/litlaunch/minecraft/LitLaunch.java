/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft;

import io.github.codetoil.litlaunch.api.litlaunch.ILibrary;
import io.github.codetoil.litlaunch.event.LitEventHandler;

public class LitLaunch implements ILibrary
{
	public final static LitEventHandler COMMON = new LitEventHandler("Common");
	public final static LitEventHandler CLIENT = new LitEventHandler("Client");
	public final static LitEventHandler SERVER = new LitEventHandler("Server");
}
