/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft;

import io.github.codetoil.litlaunch.api.litlaunch.IConstructorMap;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class MinecraftConstructorMap implements IConstructorMap {
    public static final IConstructorMap MAP = new MinecraftConstructorMap();
    private Map<Class, Constructor> map = new HashMap<>();

    @Override
    public Constructor apply(Class tClass) {
        return map.getOrDefault(tClass, null);
    }

    public void supply(Class tClass, Class applyClass, Class... paramClassList) throws NoSuchMethodException {
        map.put(tClass, applyClass.getDeclaredConstructor(paramClassList));
    }
}
