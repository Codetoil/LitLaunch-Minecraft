/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.config;

import java.io.*;
import java.util.Properties;

public class MainConfig
{
    private boolean verbose = false;
    private final File configFile;

    public MainConfig(File configFile)
    {
        this.configFile = configFile;
    }

    public MainConfig readConfig() throws IOException {
        // io.github.codetoil.litlaunch.api.LitLaunch.getLogger().trace("Reading config file!");
        if (this.configFile.exists()) {
            Properties properties = new Properties();
            InputStream configFileInput = new FileInputStream(this.configFile);
            properties.load(configFileInput);
            String verboseStr = properties.getProperty("verbose");
            // io.github.codetoil.litlaunch.api.LitLaunch.getLogger().trace("Verbose option: " + verbose);
            this.verbose = Boolean.parseBoolean(verboseStr);
            // io.github.codetoil.litlaunch.api.LitLaunch.getLogger().trace("Verbose boolean: " + lV);
        } else {
            // io.github.codetoil.litlaunch.api.LitLaunch.getLogger().error("No config file! Creating new config file!");
            throw new FileNotFoundException("Cannot find config file!");
        }
        return this;
    }

    public boolean createConfig() throws IOException {
        boolean result;
        Properties lProperties = new Properties();
        result = this.configFile.createNewFile();
        OutputStream ioOut = new FileOutputStream(this.configFile);
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
