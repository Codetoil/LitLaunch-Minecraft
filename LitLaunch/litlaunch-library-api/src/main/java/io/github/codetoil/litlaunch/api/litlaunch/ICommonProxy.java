/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch.api.litlaunch;

/**
 * The common for all versions common proxy!
 */
public interface ICommonProxy {
    void setGamePath();

    void construction();

    void preInit();

    void init();

    void postInit();

    void serverLoad();
}
