/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.dependency_loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LitClassLoader {
	private ClassLoader classLoader;
	private final Map<String, Class<?>> cache = new HashMap<>();

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
		boolean result = cache.containsKey(nameClass);
		if (!result) {
			if (classLoader == null) {
				throw new RuntimeException("Classloader of LitClassLoader has not been set to any value!");
			} else {
				try {
					cache.put(nameClass, classLoader.loadClass(nameClass));
					result = true;
				} catch (ClassNotFoundException ignored) {
				}
			}
		}
		return result;
	}

	public Class<?> getClass(String className) {
		if (classExists(className)) {
			return cache.get(className);
		} else {
			return null;
		}
	}

	public Method getMethodOfClass(Class<?> classIn, String methodName, Class<?>... paramTypes) {
		for (Method method : classIn.getMethods()) {
			if (methodName.equals(method.getName()) && areClassArraysEqual(method.getParameterTypes(), paramTypes)) {
				return method;
			}
		}
		return null;
	}

	private boolean areClassArraysEqual(Class<?>[] classList1, Class<?>[] classList2) {
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
			if (areClassArraysEqual(constructor.getParameterTypes(), paramTypes)) {
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