/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.dependency_loader;

import io.github.codetoil.litlaunch.api.IPlugin;

public interface ILitDependencyInjector
{
	void injectLitPlugins(IPlugin... mods);
}
