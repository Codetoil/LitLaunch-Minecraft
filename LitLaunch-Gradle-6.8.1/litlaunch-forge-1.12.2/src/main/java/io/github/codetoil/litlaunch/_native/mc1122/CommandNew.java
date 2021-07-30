/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1122;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import io.github.codetoil.litlaunch.api.Command;
import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.arguments.ArgumentWrapper;
import io.github.codetoil.litlaunch.api.arguments.IArgumentParser;
import io.github.codetoil.litlaunch.api.arguments.IArgumentValue;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandNew implements ICommand {

    private Command comm;

    public CommandNew() {
    }

    public CommandNew(Command comm) {
        this.comm = comm;
    }

    public List<ArgumentWrapper<?>> parseArgs(String[] argsIn, ICommandSender sender) throws WrongUsageException {
        LitLaunch.getLogger().verbose("argsIn: " + Arrays.asList(argsIn));
        List<ArgumentWrapper<?>> out = Lists.newArrayList();
        LitLaunch.getLogger().verbose("out: " + Arrays.asList(argsIn));
        OptionParser parser = new OptionParser();
        LitLaunch.getLogger().verbose("parser: " + parser);
        BiMap<ArgumentWrapper<?>, OptionSpec<?>> argToSpec = HashBiMap.create();
        LitLaunch.getLogger().verbose("argToSpec: " + argToSpec);
        comm.args.forEach((name, arg) -> {
            LitLaunch.getLogger().verbose("adding arg!");
            LitLaunch.getLogger().verbose("name: " + name);
            LitLaunch.getLogger().verbose("arg: " + arg);
            if (arg.isRequired()) {
                argToSpec.put(arg, parser.accepts(arg.name, arg.getDescription()).withRequiredArg());
            } else {
                argToSpec.put(arg, parser.accepts(arg.name, arg.getDescription()).withOptionalArg());
            }
        });
        LitLaunch.getLogger().verbose("argToSpec: " + argToSpec);
        OptionSet optionSet;
        try {
            optionSet = parser.parse(argsIn);
            LitLaunch.getLogger().verbose("optionSet: " + optionSet);
        } catch (OptionException e) {
            throw new WrongUsageException(getUsage(sender));
        }
        argToSpec.forEach((arg, spec) -> {
            Object value1 = optionSet.valueOf(spec);
            LitLaunch.getLogger().verbose("value1: " + value1);
            if (value1 instanceof String) {
                String value = (String) value1;
                LitLaunch.getLogger().verbose("value: " + value);
                IArgumentParser<? extends IArgumentValue<?>> parser_ = arg.getParser();
                LitLaunch.getLogger().verbose("parser: " + parser_);
                IArgumentValue val_ = parser_.parse(value);
                LitLaunch.getLogger().verbose("val_: " + val_);
                arg.setValue(val_);
                LitLaunch.getLogger().verbose("arg: " + arg);
                out.add(arg);
                LitLaunch.getLogger().verbose("out: " + out);
            }
        });
        return out;
    }

    @Override
    public String getName() {
        return comm.name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return comm.generateHelp();
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<>();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        try {
            comm.methodToRun.accept(parseArgs(args, sender));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return new ArrayList<>();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return this.comm.name.compareTo(o.getName());
    }

}
