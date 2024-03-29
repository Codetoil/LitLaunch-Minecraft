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
