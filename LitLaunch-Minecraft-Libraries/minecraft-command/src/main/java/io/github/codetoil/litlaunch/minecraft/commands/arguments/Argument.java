/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.minecraft.commands.arguments;

import io.github.codetoil.litlaunch.api.IComponent;

public class Argument<T> implements IComponent
{
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
