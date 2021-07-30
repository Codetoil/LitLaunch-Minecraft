package io.github.codetoil.litlaunch.api;

import java.lang.reflect.Constructor;
import java.util.function.Function;

public interface IConstructorMap extends Function<Class, Constructor> {
    Constructor apply(Class classObj);

    void supply(Class classObj, Class applyClass, Class... supplier) throws NoSuchMethodException;
}
