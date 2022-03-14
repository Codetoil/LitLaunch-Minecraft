/*
 * Copyright (c) 2022.
 */

package io.github.codetoil.litlaunch.api;

import io.github.codetoil.litlaunch.api.mods.IGetLitLaunchFields;
import io.github.codetoil.litlaunch.api.server.IMinecraftServer;

public class LitLaunch {
    private static LitLaunch instance = new LitLaunch();
    private IGetLitLaunchFields litLaunchFields;
    private IMinecraftServer minecraftFields;
    private PreformMinecraftAction preformMinecraftAction;

    public static LitLaunch getInstance() {
        return instance;
    }

    public static IGetLitLaunchFields getLitLaunchFields() {
        return instance.litLaunchFields;
    }

    public void setLitLaunchFields(IGetLitLaunchFields litLaunchFields) throws IllegalAccessException {
        if (this.litLaunchFields == null) {
            this.litLaunchFields = litLaunchFields;
        } else {
            throw new IllegalAccessException("Can't change the litlaunch fields getter!");
        }
    }

    public static IMinecraftServer getMinecraftFields() {
        return instance.minecraftFields;
    }

    public void setMinecraftFields(IMinecraftServer minecraftFields) throws IllegalAccessException {
        if (this.minecraftFields == null) {
            this.minecraftFields = minecraftFields;
        } else {
            throw new IllegalAccessException("Can't change the minecraft fields getter!");
        }
    }

    public static PreformMinecraftAction getPreformMinecraftAction() {
        return instance.preformMinecraftAction;
    }

    public void setPreformMinecraftAction(PreformMinecraftAction preformMinecraftAction) throws IllegalAccessException {
        if (this.preformMinecraftAction == null) {
            this.preformMinecraftAction = preformMinecraftAction;
        } else {
            throw new IllegalAccessException("Can't change the minecraft action preform!");
        }
    }
}
