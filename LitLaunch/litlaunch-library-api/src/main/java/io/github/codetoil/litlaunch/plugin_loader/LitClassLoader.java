/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.plugin_loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LitClassLoader {
	private ClassLoader classLoader;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		if (this.classLoader == null) {
			this.classLoader = classLoader;
		} else {
			throw new RuntimeException("Classloader is final");
		}
	}

	public boolean classExists(String nameClass) {
		boolean result;
		if (classLoader == null) {
			throw new RuntimeException("Classloader of LitClassLoader has not been set to any value!");
		}
		try {
			classLoader.loadClass(nameClass);
			result = true;
		} catch (ClassNotFoundException e) {
			result = false;
		}
		return result;
	}

	public Class<?> getClass(String className) {
		Class<?> classOut;
		if (classLoader == null) {
			throw new RuntimeException("Classloader of LitClassLoader has not been set to any value!");
		}
		try {
			classOut = classLoader.loadClass(className);
		} catch (Throwable e) {
			e.printStackTrace();
			classOut = null;
		}
		return classOut;
	}

	public Method getMethodOfClass(Class<?> classIn, String methodName, Class<?>... paramTypes) {
		for (Method method : classIn.getMethods()) {
			if (methodName.equals(method.getName()) && areClassListsEqual(method.getParameterTypes(), paramTypes)) {
				return method;
			}
		}
		return null;
	}

	public boolean areClassListsEqual(Class<?>[] classList1, Class<?>[] classList2) {
		boolean result = true;
		if (classList1.length == classList2.length) {
			for (int i = 0; i < classList1.length; i++) {
				result = result && (classList1[i].getName().equals(classList2[i].getName()));
			}
		} else {
			result = false;
		}
		return result;
	}

	public Constructor<?> getConstructorOfClass(Class<?> classIn, Class<?>... paramTypes) {
		for (Constructor<?> constructor : classIn.getConstructors()) {
			if (areClassListsEqual(constructor.getParameterTypes(), paramTypes)) {
				return constructor;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "LitClassLoader{classLoader=" + classLoader + "}";
	}
}