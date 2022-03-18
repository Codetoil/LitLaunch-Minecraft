/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.commands.arguments;

public class ArgumentParserInteger implements IArgumentParser<Integer> {
    @Override
    public Integer parse(String input) {
        if (input.startsWith("#")) {
            input = input.substring(1);
        }

        return Integer.parseInt(input);
    }

    @Override
    public boolean isParsedInputANumber() {
        return true;
    }

    @Override
    public Class<?> getArgumentClass()
    {
        return Integer.class;
    }
}