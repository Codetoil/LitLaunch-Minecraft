/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.commands.arguments;

public class ArgumentParserString implements IArgumentParser<String> {
    @Override
    public String parse(String input) {
        return input;
    }

    @Override
    public boolean isParsedInputANumber() {
        return false;
    }

    @Override
    public Class<?> getArgumentClass()
    {
        return String.class;
    }
}
