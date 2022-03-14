/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch.api.mods;

import io.github.codetoil.litlaunch.api.commands.ICommand;

import java.util.List;

public interface IMod {
    EnumModRequirementStatus onClient();

    EnumModRequirementStatus onServer();

    String getModID();

    String getVersion();

    List<ICommand> getCommandList();

    void construction();

    void preInit();

    void init();

    void postInit();

    void serverLoad();

    default IMod getModINSTANCE() {
        return this;
    }
}
