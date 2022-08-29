/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.logger.config;

import io.github.codetoil.litlaunch.config.Config;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

@SuppressWarnings("unused")
public class LoggerConfig extends Config
{
	private boolean verbose = false;

	public LoggerConfig(File configFile)
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
