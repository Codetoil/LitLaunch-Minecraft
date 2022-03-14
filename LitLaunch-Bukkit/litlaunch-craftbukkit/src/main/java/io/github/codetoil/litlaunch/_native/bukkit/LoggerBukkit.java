package io.github.codetoil.litlaunch._native.bukkit;

import io.github.codetoil.litlaunch.api.ILogger;
import io.github.codetoil.litlaunch.minecraft.LaunchCommon;

import java.io.PrintStream;

public class LoggerBukkit implements ILogger {

    private final static ILogger Instance = new LoggerBukkit();
    private PrintStream LOGGER;

    private LoggerBukkit() {
    }

    public static ILogger getInstance() {
        return Instance;
    }

    @Override
    public void debug(Object obj) {
        this.LOGGER.println("Debug> " + obj.toString());
    }

    @Override
    public void info(Object obj) {
        this.LOGGER.println("Info> " + obj.toString());
    }

    @Override
    public void warn(Object obj) {
        this.LOGGER.println("Warn> " + obj.toString());
    }

    @Override
    public void error(Object obj) {
        this.LOGGER.println("Error> " + obj.toString());
    }

    @Override
    public void fatal(Object obj) {
        this.LOGGER.println("Fatal> " + obj);
    }

    @Override
    public void trace(Object obj) {
        this.LOGGER.println("Trace> " + obj);
    }

    @Override
    public void verbose(Object obj) {
        if (LaunchCommon.isVerbose()) {
            this.LOGGER.println("Verbose> " + obj);
        }
    }

    @Override
    public Object getInternalLogger() {
        return LOGGER;
    }

    public void setInternalLogger(Object logger) {
        if (this.LOGGER == null) {
            this.LOGGER = (PrintStream) logger;
        }
    }
}
