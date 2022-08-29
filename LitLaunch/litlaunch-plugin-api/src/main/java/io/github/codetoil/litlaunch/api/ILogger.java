/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.api;

public interface ILogger {
    void debug(Object obj);

    void info(Object obj);

    void warn(Object obj);

    void error(Object obj);

    void fatal(Object obj);

    void trace(Object obj);

    /**
     * Only prints if the "verbose" flag is enabled
     */
    void verbose(Object obj);

    Object getInternalLogger();

    void setInternalLogger(Object logger);
}
