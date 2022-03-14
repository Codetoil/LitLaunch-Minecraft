/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.commands.arguments;

public interface IArgumentValue<T> {
    IArgumentParser<? extends IArgumentValue> getParser();

    T getValue();
}
