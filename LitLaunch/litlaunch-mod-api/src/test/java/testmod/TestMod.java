/*
 * Copyright (c) 2022.
 */

package testmod;

import io.github.codetoil.litlaunch.api.commands.ICommand;
import io.github.codetoil.litlaunch.api.mods.EnumModRequirementStatus;
import io.github.codetoil.litlaunch.api.mods.IMod;

import java.util.ArrayList;
import java.util.List;

public class TestMod implements IMod {
    @Override
    public EnumModRequirementStatus onClient() {
        return EnumModRequirementStatus.COMPATIBLE;
    }

    @Override
    public EnumModRequirementStatus onServer() {
        return EnumModRequirementStatus.INCOMPATIBLE;
    }

    @Override
    public String getModID() {
        return "testmod";
    }

    @Override
    public String getVersion() {
        return "0.1.0+build.0";
    }

    @Override
    public List<ICommand> getCommandList() {
        return new ArrayList<>();
    }

    @Override
    public void construction() {

    }

    @Override
    public void preInit() {

    }

    @Override
    public void init() {

    }

    @Override
    public void postInit() {

    }

    @Override
    public void serverLoad() {

    }
}
