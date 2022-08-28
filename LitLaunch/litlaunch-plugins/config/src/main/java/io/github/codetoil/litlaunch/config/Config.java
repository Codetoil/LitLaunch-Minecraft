/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.config;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

@SuppressWarnings("unused")
public abstract class Config
{
    protected final File configFile;

    public Config(File configFile)
    {
        this.configFile = configFile;
    }

    public abstract Config readConfig() throws IOException;

    public boolean configExists() {
        return this.configFile.exists();
    }
    public abstract boolean createConfig() throws IOException;
}
