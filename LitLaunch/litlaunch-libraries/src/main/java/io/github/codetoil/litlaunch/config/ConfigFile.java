/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.config;

import io.github.codetoil.litlaunch.api.mods.FailedBootstrapException;
import io.github.codetoil.litlaunch.minecraft.LitLaunch;

import java.io.*;
import java.util.Properties;

public class ConfigFile {
    public static boolean readConfig(File configFile) throws IOException {
        io.github.codetoil.litlaunch.api.LitLaunch.getLogger().trace("Reading config file!");
        boolean result = false;
        if (configFile.exists()) {
            Properties lProperties = new Properties();
            InputStream ioInp = new FileInputStream(configFile);
            lProperties.load(ioInp);
            String verbose = lProperties.getProperty("verbose");
            io.github.codetoil.litlaunch.api.LitLaunch.getLogger().trace("Verbose option: " + verbose);
            boolean lV = Boolean.parseBoolean(verbose);
            io.github.codetoil.litlaunch.api.LitLaunch.getLogger().trace("Verbose boolean: " + lV);
            LitLaunch.setVerbose(lV);
            io.github.codetoil.litlaunch.api.LitLaunch.getLogger().trace("LaunchCommon verbose: " + LitLaunch.isVerbose());
        } else {
            io.github.codetoil.litlaunch.api.LitLaunch.getLogger().error("No config file! Creating new config file!");
            setDefaultConfigFile(configFile);
        }
        return result;
    }

    private static void setDefaultConfigFile(File configFile) throws IOException {
        Properties lProperties = new Properties();
        configFile.createNewFile();
        OutputStream ioOut = new FileOutputStream(configFile);
        lProperties.setProperty("verbose", "false");
        lProperties.store(ioOut, " This is a config file for litlaunch. This used to be where you specified what mods are to be loaded, but not anymore.\n Mods are now dragged-and-dropped into the \"mods_litlaunch\" directory.\n The verbose options prints a lot of info into the console, but is very spammy.");
        if (configFile.exists()) {
            readConfig(configFile);
        } else {
            throw new FailedBootstrapException("Couldn't create config file!");
        }

    }
}
