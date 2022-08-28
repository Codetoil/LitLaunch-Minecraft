/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.commands.arguments;

public class Argument<T> {
    public final String name;
    private final IArgumentParser<T> parser;
    private final boolean isRequired;
    private final boolean isNumber;
    private String description;

    public Argument(String name, IArgumentParser<T> parser) {
        this(name, parser, name, true);
    }

    public Argument(String name, IArgumentParser<T> parser, String pDescription, boolean pIsRequired) {
        this.name = name;
        this.parser = parser;
        this.description = pDescription;
        this.isRequired = pIsRequired;
        this.isNumber = parser.isParsedInputANumber();
    }

    public Argument(String name, IArgumentParser<T> parser, boolean pIsRequired) {
        this(name, parser, name, pIsRequired);
    }

    public Argument(String name, IArgumentParser<T> parser, String pDescription) {
        this(name, parser, pDescription, true);
    }

    public IArgumentParser<T> getParser() {
        return parser;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getDescription() {
        return description;
    }

    public Argument<T> setDescription(String pDescription) {
        description = pDescription;
        return this;
    }

    public boolean isNumber() {
        return isNumber;
    }

    @Override
    public String toString() {
        return "Argument{name=" + name + " + , description=" + description + ", parser=" + parser + ", isRequired=" + isRequired + "}";
    }
}
