package io.github.codetoil.litlaunch._native.mc1144;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.codetoil.litlaunch.api.Command;
import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.arguments.ArgumentParserListString;
import io.github.codetoil.litlaunch.api.arguments.ArgumentWrapper;
import io.github.codetoil.litlaunch.api.arguments.IArgumentParser;
import io.github.codetoil.litlaunch.api.arguments.IArgumentValue;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

import java.util.Arrays;
import java.util.List;

public class CommandNew {

    private Command comm;

    public CommandNew() {
    }

    public CommandNew(Command comm, CommandDispatcher<CommandSource> disp) {
        this.comm = comm;
        this.register(disp);
    }

    public void register(CommandDispatcher<CommandSource> disp) {
        LiteralArgumentBuilder<CommandSource> argbuilder = Commands.literal(comm.name);
        argbuilder.then(Commands.argument("args", new ArgumentParserWrapperType<>(new ArgumentParserListString())));
        argbuilder.executes((pCommandContext) -> {
            execute(pCommandContext);
            return 1;
        });
        disp.register(argbuilder);
    }

    public void execute(CommandContext<CommandSource> context) {
        LitLaunch.getLogger().verbose(comm);
        LitLaunch.getLogger().verbose(context);
        List<String> args = context.getArgument("args", List.class);
        try {
            LitLaunch.getLogger().verbose(comm);
            LitLaunch.getLogger().verbose(comm.methodToRun);
            comm.methodToRun.accept(parseArgs((String[]) args.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public String getUsage(ICommandSender sender) {
    //	return comm.usage;
    //}

    public List<ArgumentWrapper<?>> parseArgs(String[] argsIn) throws CommandSyntaxException {
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
            //throw new WrongUsageException(getUsage(sender));
            throw new com.mojang.brigadier.exceptions.CommandSyntaxException(new SimpleCommandExceptionType(new StringTextComponent(comm.generateHelp())), new StringTextComponent(comm.generateHelp()));
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

    public Command getComm() {
        return comm;
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }
}
