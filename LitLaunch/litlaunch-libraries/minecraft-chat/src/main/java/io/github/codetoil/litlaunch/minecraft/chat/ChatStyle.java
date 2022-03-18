/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.chat;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;

@Immutable
public class ChatStyle
{
	private final IChatColor chatColor;
	private final ImmutableList<IChatStylation> chatStylations;

	public ChatStyle(IChatColor chatColor, IChatStylation... chatStylations)
	{
		this.chatColor = chatColor;
		this.chatStylations = ImmutableList.<IChatStylation>builder()
				.add(chatStylations).build();
	}

	public IChatColor getChatColor()
	{
		return chatColor;
	}

	public ImmutableList<IChatStylation> getChatStylations()
	{
		return chatStylations;
	}
}