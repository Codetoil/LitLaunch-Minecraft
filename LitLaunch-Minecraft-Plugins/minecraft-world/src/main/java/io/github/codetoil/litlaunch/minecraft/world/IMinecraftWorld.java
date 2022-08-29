/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.minecraft.world;

import io.github.codetoil.litlaunch.api.IComponent;
import io.github.codetoil.litlaunch.minecraft.chat.ChatStyle;
import io.github.codetoil.litlaunch.minecraft.dimensions.IDimensionType;

public interface IMinecraftWorld extends IComponent
{
	IDimensionType getDimensionsRunning();

	IDimensionType[] getDimensionsThatExist();

	void sendChatMessage(String message);

	void sendGeneralMessage(String message);

	void sendGeneralMessage(String message, ChatStyle pChatStyle);
}
