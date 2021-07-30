package io.github.codetoil.litlaunch.api.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public interface ILitClassLoader {
    ClassLoader getClassLoader();

    void setClassLoader(ClassLoader classLoader);

    boolean classExists(String nameClass);

    Class<?> getClass(String className);

    Method getMethodOfClass(Class<?> classIn, String methodName, Class<?>... paramTypes);

    boolean areClassListsEqual(Class<?>[] classList1, Class<?>[] classList2);

    Constructor<?> getConstructorOfClass(Class<?> classIn, Class<?>... paramTypes);
}
