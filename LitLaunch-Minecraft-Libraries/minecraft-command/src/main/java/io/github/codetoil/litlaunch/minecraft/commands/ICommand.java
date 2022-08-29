/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.minecraft.commands;

import com.google.common.collect.BiMap;
import io.github.codetoil.litlaunch.api.IComponent;
import io.github.codetoil.litlaunch.minecraft.commands.arguments.Argument;

import java.lang.reflect.Method;

/**
 * @author Codetoil
 * @since LitLaunch 0.0.5+build.0
 */
public interface ICommand extends IComponent
{
	/**
	 * @return Name of the command.
	 */
	String getName();

	/**
	 * @return The arguments applied
	 */
	BiMap<String, Argument<?>> getArgumentTypes();

	/**
	 *
	 * @return The method that is called when the command is run.
	 */
	Method getCallingMethod();

	/**
	 *
	 * @return The help information that is returned upon /help &lt;command&gt;
	 */
	String getHelp();

	/**
	 * @return Side the command should be registered to. e.g. CLIENT gives a client side command.
	 */
	CommandSide getCommandSide();
}
