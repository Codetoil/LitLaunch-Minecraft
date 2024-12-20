/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1_8;


import io.github.codetoil.litlaunch.core.ILogger;
import io.github.codetoil.litlaunch.core.LaunchCommon;
import org.apache.logging.log4j.Logger;

public class Logger1_8 implements ILogger
{

	private final static ILogger Instance = new Logger1_8();
	private Logger LOGGER;

	private Logger1_8()
	{
	}

	public static ILogger getInstance()
	{
		return Instance;
	}

	@Override
	public void debug(Object obj)
	{
		this.LOGGER.debug(obj);
	}

	@Override
	public void info(Object obj)
	{
		this.LOGGER.info(obj);
	}

	@Override
	public void warn(Object obj)
	{
		this.LOGGER.warn(obj);
	}

	@Override
	public void error(Object obj)
	{
		this.LOGGER.error(obj);
	}

	@Override
	public void fatal(Object obj)
	{
		this.LOGGER.fatal(obj);
	}

	@Override
	public void trace(Object obj)
	{
		this.LOGGER.trace(obj);
	}

	@Override
	public void verbose(Object obj)
	{
		if (LaunchCommon.isVerbose())
		{
			this.LOGGER.trace(obj);
		}
	}

	public Object getInternalLogger()
	{
		return LOGGER;
	}

	public void setInternalLogger(Object logger)
	{
		if (this.LOGGER == null)
		{
			this.LOGGER = (Logger) logger;
		}
	}


}
