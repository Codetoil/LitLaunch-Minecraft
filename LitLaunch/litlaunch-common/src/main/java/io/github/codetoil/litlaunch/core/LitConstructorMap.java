package io.github.codetoil.litlaunch.core;

import io.github.codetoil.litlaunch.api.IConstructorMap;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class LitConstructorMap implements IConstructorMap {

    private Map<Class, Constructor> map = new HashMap<>();

    @Override
    public Constructor apply(Class tClass) {
        return map.getOrDefault(tClass, null);
    }

    public void supply(Class tClass, Class applyClass, Class... paramClassList) throws NoSuchMethodException {
        map.put(tClass, applyClass.getDeclaredConstructor(paramClassList));
    }
}
