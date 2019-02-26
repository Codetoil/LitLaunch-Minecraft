package io.github.littoil.litlaunch.launchcommon;

public interface ILaunch {
	
	public void preInit();
	
	public void init();

	public void postInit();
	
	public void serverLoad();
	
	public void sendEvent(String eventName);
}
