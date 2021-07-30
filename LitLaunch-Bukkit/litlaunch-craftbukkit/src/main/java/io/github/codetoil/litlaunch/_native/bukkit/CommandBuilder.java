package io.github.codetoil.litlaunch._native.bukkit;

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
import org.bukkit.command.CommandSender;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandBuilder {
    /*public static CommandCallable getCommand(Command command) {
        return new CommandWrapper(command);
    }
*/
    public static class CommandWrapper extends org.bukkit.command.Command /*implements CommandCallable*/ {

        private final Command command;

        private CommandWrapper(Command command) {
            super("litlaunch_command");
            this.command = command;
        }
/*
        @Override
        public List<String> getSuggestions(CommandSource source, String arguments, @Nullable Location<World> targetPosition)
                throws CommandException {
            return Lists.newArrayList();
        }

        @Override
        public boolean testPermission(CommandSource source) {
            return true;
        }

        @Override
        public Optional<Text> getShortDescription(CommandSource source) {
            return Optional.of(Text.of(command.generateHelp()));
        }

        @Override
        public Optional<Text> getHelp(CommandSource source) {
            return Optional.of(Text.of(command.generateHelp()));
        }

        @Override
        public Text getUsage(CommandSource source) {
            return ;
        }
*/

        public List<ArgumentWrapper<?>> parseArgs(String[] argsIn) {
            LitLaunch.getLogger().verbose("argsIn: " + Arrays.asList(argsIn));
            List<ArgumentWrapper<?>> out = Lists.newArrayList();
            LitLaunch.getLogger().verbose("out: " + Arrays.asList(argsIn));
            OptionParser parser = new OptionParser();
            LitLaunch.getLogger().verbose("parser: " + parser);
            BiMap<ArgumentWrapper<?>, OptionSpec<?>> argToSpec = HashBiMap.create();
            LitLaunch.getLogger().verbose("argToSpec: " + argToSpec);
            command.args.forEach((name, arg) -> {
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
                //throw new com.mojang.brigadier.exceptions.CommandSyntaxException(new SimpleCommandExceptionType(new StringTextComponent(comm.generateHelp())),  new StringTextComponent(comm.generateHelp()));
                throw new RuntimeException(command.generateHelp());
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
        public boolean execute(CommandSender sender, String commandLabel, String[] arguments) {
            try {
                List<ArgumentWrapper<?>> args = parseArgs(arguments);
                command.methodToRun.accept(args);
            } catch (Throwable t) {
                t.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        public String getUsage() {
            return command.generateHelp();
        }
    }
}
