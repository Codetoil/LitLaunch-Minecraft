/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.api.event;

public class LitEventType
{
	private final String name;

	public LitEventType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return "enum LitEventType{name=" + name + "}";
	}
}
