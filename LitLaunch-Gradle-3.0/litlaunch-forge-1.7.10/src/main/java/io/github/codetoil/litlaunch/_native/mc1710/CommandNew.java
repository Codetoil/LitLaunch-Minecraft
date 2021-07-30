/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch._native.mc1710;

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
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;

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

    @Override
    public String getCommandName() {
        return comm.name;
    }

    @Override
    public List getCommandAliases() {
        return new ArrayList<>();
    }

    @Override
    public void processCommand(ICommandSender p_71515_1_, String[] p_71515_2_) {
        try {
            comm.methodToRun.accept(parseArgs(p_71515_2_, p_71515_1_));
        } catch (Throwable t) {
            t.printStackTrace();
        }
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
            throw new WrongUsageException(getCommandUsage(sender));
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
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return comm.generateHelp();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_) {
        return true;
    }


    @Override
    public List addTabCompletionOptions(ICommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return o instanceof ICommand ? this.comm.name.compareTo(((ICommand) o).getCommandName()) : 0;
    }

}
