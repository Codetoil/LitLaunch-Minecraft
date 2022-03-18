/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.commands.arguments;

import com.google.common.collect.Lists;

import java.util.List;

public class ArgumentParserListString implements IArgumentParser<List<String>> {
    @Override
    public List<String> parse(String input) {
        return Lists.newArrayList(input.split(" "));
    }

    @Override
    public boolean isParsedInputANumber() {
        return false;
    }

    @Override
    public Class<?> getArgumentClass()
    {
        return List.class;
    }
}
