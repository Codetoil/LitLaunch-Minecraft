package testmod;

import io.github.codetoil.litlaunch.api.Command;
import io.github.codetoil.litlaunch.api.EnumRequireOrNot;
import io.github.codetoil.litlaunch.api.IMod;
import io.github.codetoil.litlaunch.api.LitLaunch;

import java.util.ArrayList;
import java.util.List;

public class TestMod implements IMod {
    @Override
    public EnumRequireOrNot onClient() {
        return EnumRequireOrNot.COMPATIBLE;
    }

    @Override
    public EnumRequireOrNot onServer() {
        return EnumRequireOrNot.INCOMPATIBLE;
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
    public List<Command> getCommandList() {
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
