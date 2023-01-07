/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.dependency_loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassLoaderWrapper
{
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