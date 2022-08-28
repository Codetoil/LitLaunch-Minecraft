/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.api;

public interface ILogger {
    void debug(Object obj);

    void info(Object obj);

    void warn(Object obj);

    void error(Object obj);

    void fatal(Object obj);

    void trace(Object obj);

    /**
     * Only prints if the "verbose" flag is enabled
     */
    void verbose(Object obj);

    Object getInternalLogger();

    void setInternalLogger(Object logger);
}
