/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.config;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

@SuppressWarnings("unused")
public class LitLaunchConfig extends Config
{
	private boolean verbose = false;

	public LitLaunchConfig(File configFile)
	{
		super(configFile);
	}

	@Override
	public Config readConfig() throws IOException
	{
		if (this.configFile.exists()) {
			Properties properties = new Properties();
			InputStream configFileInput = Files.newInputStream(this.configFile.toPath());
			properties.load(configFileInput);
			String verboseStr = properties.getProperty("verbose");
			this.verbose = Boolean.parseBoolean(verboseStr);
		} else {
			throw new FileNotFoundException("Cannot find config file at " + this.configFile.toPath() + "!");
		}
		return this;
	}

	@Override
	public boolean createConfig() throws IOException
	{
		boolean result;
		Properties lProperties = new Properties();
		result = this.configFile.createNewFile();
		OutputStream ioOut = Files.newOutputStream(this.configFile.toPath());
		lProperties.setProperty("verbose", "false");
		lProperties.store(ioOut, " This is the config file for litlaunch.\n"
				+ " The verbose option makes LitLaunch print a lot of debug info to the console.");
		return result;
	}

	public boolean isVerbose()
	{
		return verbose;
	}
}
