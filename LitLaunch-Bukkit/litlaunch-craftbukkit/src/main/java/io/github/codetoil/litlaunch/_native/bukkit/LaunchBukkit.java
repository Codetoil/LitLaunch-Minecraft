package io.github.codetoil.litlaunch._native.bukkit;

import io.github.codetoil.litlaunch._native.bukkit.proxy.ServerProxyBukkit;
import io.github.codetoil.litlaunch.api.Command;
import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.exceptions.FailedBootstrapException;
import io.github.codetoil.litlaunch.minecraft.INativeLaunch;
import io.github.codetoil.litlaunch.minecraft.LaunchCommon;
import io.github.codetoil.litlaunch.modloader.ModFinder;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaunchBukkit extends JavaPlugin implements INativeLaunch {
    public static final String VERSION = GetMinecraftFields.INSTANCE.minecraftVersion() + "-" + LaunchCommon.VERSIONF;
    //private Task onTick;
    public final Map<String, PluginCommand> commands = new HashMap<>();

    @Override
    public void onEnable() {
        LaunchCommon.setSide(Command.Side.SERVER);
        LaunchCommon.setGamePath(Bukkit.getWorldContainer().toPath().getParent());
        try {
            LitLaunch.getInstance().setPreformMinecraftAction(PreformMinecraftAction.INSTANCE);
            LitLaunch.getInstance().setMinecraftFields(GetMinecraftFields.INSTANCE);
            LaunchCommon.bootstrap(System.out, this, LoggerBukkit.getInstance());
        } catch (Throwable t) {
            FailedBootstrapException lFailedBootstrapException = new FailedBootstrapException();
            lFailedBootstrapException.initCause(t);
            throw lFailedBootstrapException;
        }
        LaunchCommon.preInit();
        LaunchCommon.init();
        LaunchCommon.postInit();
        super.onEnable();
    }

    public boolean setProxy() {
        boolean result;
        if (LaunchCommon.getCcproxy() != null) {
            LitLaunch.getLogger().error("Tried re-setting proxy!");
            result = false;
        } else {
            Command.Side side = LaunchCommon.getSide();
            switch (side) {
                case CLIENT:
                    LitLaunch.getLogger().error("Sponge Client? You've got to be joking!");
                    result = false;
                    break;
                case SERVER:
                    LaunchCommon.setCcproxy(new ServerProxyBukkit());
                    result = true;
                    break;
                default:
                    LitLaunch.getLogger().error("Sponge is not sided. This should not happen!");
                    result = false;
                    break;
            }
        }
        return result;
    }

    @Override
    public PluginCommand getCommand(String name) {
        String alias = name.toLowerCase(java.util.Locale.ENGLISH);
        PluginCommand command = getServer().getPluginCommand(alias);

        if (command == null || command.getPlugin() != this) {
            command = getServer().getPluginCommand(getDescription().getName().toLowerCase(java.util.Locale.ENGLISH) + ":" + alias);
        }

        if (command != null && command.getPlugin() == this) {
            return command;
        } else {
            return commands.getOrDefault(name, null);
        }
    }

    @Override
    public void onLoad() {
        LaunchCommon.serverLoad();
        //onTick = Sponge.getScheduler().createTaskBuilder().intervalTicks(1).execute(() -> {
        //    LitEventHandler.COMMON.post(new LitEvent(this, LitEvent.TYPE.SERVERTICK), true);
        //}).name("onTick").submit(this);
        addModCommands();
        super.onLoad();
    }

    private void addModCommands() {
        ModFinder.validMods.forEach((modClass) -> {
            try {
                Object oCommands = modClass.getField("commandList").get(null);
                List lCommands;
                if (oCommands instanceof List) {
                    lCommands = (List) oCommands;
                    Constructor<PluginCommand> pluginCommandConstructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
                    lCommands.forEach((command) -> {
                        if (command instanceof Command) {
                            if (Command.Side.SERVER.equals(((Command) command).side) || Command.Side.BOTH.equals(((Command) command).side)) {
                            }
                            {
                                PluginCommand command1;
                                try {
                                    command1 = pluginCommandConstructor.newInstance(((Command) command).name, this);
                                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                    return;
                                }
                                this.commands.put(((Command) command).name, command1);
                                //CommandCallable callable = CommandBuilder.getCommand((Command) command);
                                //Sponge.getCommandManager().register(this, callable, Lists.newArrayList(((Command) command).name));
                            }
                        }
                    });
                } else {
                    LitLaunch.getLogger().error("Mod " + modClass + " does not have a method named \"commandList\". This is neccesary for the api to work though. Skipping!");
                }

            } catch (Throwable pThrowable) {
                pThrowable.printStackTrace();
            }

        });
    }




}
