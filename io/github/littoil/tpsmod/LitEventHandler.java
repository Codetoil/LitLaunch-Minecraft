package io.github.littoil.tpsmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.littoil.litlaunch.launchcommon.events.LitEvent;

public class LitEventHandler {
    static final Logger LOGGER = LogManager.getLogger();
    
    private TPSMod tpsmod;
    
    public LitEventHandler()
    {
    }
    
    public LitEventHandler(TPSMod tpsmod)
    {
    	LOGGER.info("Creating new Event Handler!");
    	this.tpsmod = tpsmod;
    }
    
	public void newEvent(LitEvent event)
	{
		LOGGER.info("Have new event \"" + event.name + "\"");
		switch (event.name)
		{
		case "PreInit":
			tpsmod.preInit();
			break;
		case "Init":
			tpsmod.Init();
			break;
		case "PostInit":
			tpsmod.postInit();
			break;
		case "ServerLoad":
			tpsmod.serverStart();
			break;
		default:
			LOGGER.error("Unknown event \"" + event.name + "\"");
			break;
		}
	}
}
