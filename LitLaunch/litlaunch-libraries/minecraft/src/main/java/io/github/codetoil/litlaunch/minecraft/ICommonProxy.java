/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft;

/**
 * The common interface for LitLaunch versions for all versions of Minecraft!
 */
public interface ICommonProxy {
    void setGamePath();

    void construction();

    void preInit();

    void init();

    void postInit();

    void serverLoad();
}
