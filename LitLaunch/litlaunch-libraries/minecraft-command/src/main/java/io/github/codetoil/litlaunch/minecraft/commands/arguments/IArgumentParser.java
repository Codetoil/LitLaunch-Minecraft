/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.commands.arguments;

public interface IArgumentParser<T extends IArgumentValue<?>> {
    T parse(String input);

    boolean isNumber();

    Class<?> getClassOfT();
}
