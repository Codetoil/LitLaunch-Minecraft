/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.commands;

import com.google.common.collect.BiMap;
import io.github.codetoil.litlaunch.minecraft.commands.arguments.Argument;

import java.lang.reflect.Method;

/**
 * @author Codetoil
 * @since 0.0.5+build.0
 */
public interface ICommand
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
