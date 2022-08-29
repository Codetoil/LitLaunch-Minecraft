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

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class DependencyFileLoader
{
	private final ClassLoaderWrapper litClassLoader = new ClassLoaderWrapper();
	private final List<Class<?>> validDependencyClasses = new ArrayList<>(); // Cache the result, this operation is heavy.

	public List<Class<?>> getValidDependencyClasses(Path dependencyDirectory) {
		if (this.validDependencyClasses.isEmpty()) {
			if (dependencyDirectory.toFile().isDirectory()) {
				File[] potentialDependencyFiles = dependencyDirectory.toFile().listFiles();
				assert potentialDependencyFiles != null;
				for (File potentialDependencyFile :
						potentialDependencyFiles
				) {
					if (potentialDependencyFile.isFile() && potentialDependencyFile.getName().endsWith(".jar")) {
						this.validDependencyClasses.addAll(this.getValidDependencyClassesFromFile(potentialDependencyFile));
					}
				}
			}
		}

		return this.validDependencyClasses;
	}

	private List<Class<?>> getValidDependencyClassesFromFile(File plugin) {
		List<Class<?>> result = new ArrayList<>();

		try {
			JarFile jarDependency = new JarFile(plugin);
			Enumeration<JarEntry> files = jarDependency.entries();
			URL[] urls = {new URL("jar:file:" + plugin.getAbsolutePath() + "!/")};
			URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls, DependencyFileLoader.class.getClassLoader());
			while (files.hasMoreElements()) {
				JarEntry entry = files.nextElement();
				if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
					continue;
				}
				try {
					Class<?> potentiallyValidDependency = returnClassIfItIsValidDependency(entry, urlClassLoader);
					if (potentiallyValidDependency != null) result.add(potentiallyValidDependency);
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return result;
	}

	protected abstract Class<?> returnClassIfItIsValidDependency(JarEntry entry, ClassLoader classLoader) throws ClassNotFoundException;

	public void invalidateDependencyListCache() {
		this.validDependencyClasses.clear();
	}

	/*
	private static boolean testIfModClass(JarEntry pEntry, ClassLoader cl) throws ClassNotFoundException {
        String className = pEntry.getName().substring(0, pEntry.getName().length() - 6);
        className = className.replace('/', '.');
        SoftReference<Class> c = new SoftReference<>(cl.loadClass(className));
        if (c.get() != null) {
            Class[] interfaces = Objects.requireNonNull(c.get()).getInterfaces();
            boolean isMod = false;
            boolean isListener = false;
            for (Class interface_ :
                    interfaces) {
                LitLaunch.getLogger().debug(interface_);
                if (interface_.getName().equals(IMod.class.getName())) {
                    LitLaunch.getLogger().trace("Found Class That Implements IMod!!! :D");
                    isMod = true;
                }
                if (interface_.getName().equals(ILitEventHandler.IEventListener.class.getName())) {
                    LitLaunch.getLogger().trace("Found Listener Class!");
                    isListener = true;
                }
            }
            if (isMod && isListener) {
                LitLaunch.getLogger().trace("Mod " + c + " is a LitLaunch Mod!!");
                ModFinder.possibilityList.add(c.get());
                LitLaunch.getLogger().trace("Added to possibility list");
                return true;
            }
        }
        return false;
    }

	public boolean validatePlugins() {
		try {
			for (Class<?> possibility : possiblyValidPluginList) {
				try {
					// Goal : Validate Mod
					// Step 1 : Get some random instance in order to get main instance (Can't force a static method in java, so the method to get the main instance has to be nonstatic)
					Object randomINSTANCE = possibility.newInstance();
					// Step 2 : Get the main instance as a listener of the mod!
					Method getListener = litClassLoader.getMethodOfClass(possibility, "getListener");
					Method getModINSTANCE = litClassLoader.getMethodOfClass(possibility, "getModINSTANCE");
					if (getListener != null && getModINSTANCE != null) {
						Object oListener = getListener.invoke(randomINSTANCE);
						if (oListener instanceof ILitEventHandler.IEventListener) {
							if (!verifySide(getModINSTANCE)) {
								LitLaunch.getLogger().trace("Mod \"" + possibility.getName() + "\" is valid! Adding to valid mod list and adding listener!");
								validPluginMap.add(possibility);
								// Step 2a : Convert oListener to EventListener class
								ILitEventHandler.IEventListener lListener = (ILitEventHandler.IEventListener) oListener;
								// Step 3 : Add the event listener!
								LitEventHandler.COMMON.addListener(lListener);
							} else {
								LitLaunch.getLogger().trace("Mod \"" + possibility.getName() + "\" does not work on the given side, and will not load! Skipping!");
							}
						} else {
							LitLaunch.getLogger().error("Mod \"" + possibility.getName() + "\" does not implement LitEventHandler.EventListener, and such cannot be loaded. Skipping Mod!");
						}
					} else {
						LitLaunch.getLogger().error("Failed initializing Mod \"" + possibility.getName() + "\" because required methods cannot be located. Skipping it!");
					}
				} catch (InstantiationException | NoSuchMethodError t) {
					LitLaunch.getLogger().error("Failed initializing Mod \"" + possibility.getName() + "\" due to an InstantationException or NoSuchMethodError! Skipping Mod!");
				}

			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new FailedBootstrapException("Throwable thrown in bootstrap!!!", e);
		}
		return true;
	}

	private static boolean verifySide(Object object) {
		if (object instanceof IMod) {
			IMod mod = (IMod) object;
			switch (LaunchCommon.getFrontEnd().getSide()) {
				case BOTH:
				case CLIENT:
					return !mod.onClient().equals(EnumRequireOrNot.INCOMPATIBLE);
				case SERVER:
					return !mod.onServer().equals(EnumRequireOrNot.INCOMPATIBLE);
			}
		}
		return false;
	}
	*/
}