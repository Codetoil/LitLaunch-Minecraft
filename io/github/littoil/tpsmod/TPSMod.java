package io.github.littoil.tpsmod;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.littoil.litlaunch.launchcommon.Command;
import io.github.littoil.tpsmod.commands.CommandHandler;

public class TPSMod {
	public static final String VERSION = "1.2.1";
	
	protected static final Logger LOGGER = LogManager.getLogger();
	
	public static LitEventHandler eventhandler;
	public static ArrayList<Command> commandList = new ArrayList<Command>();
	
	public TPSMod()
	{
		eventhandler = new LitEventHandler(this);
		try {
			commandList.add(new Command("/tps", "message.tpsmod.tps.help", CommandHandler.class.getMethod("execute"), Command.Side.BOTH));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public void preInit()
	{
		LOGGER.info("TPSMod v" + VERSION + " preinitializing");
	}
	
	public void Init()
	{
		LOGGER.info("TPSMod v" + VERSION + " initializing");
	}
	
	public void postInit()
	{
		LOGGER.info("TPSMod v" + VERSION + " postinitializing");
	}
	public void serverStart()
	{
		LOGGER.info("TPSMod v" + VERSION + " starting server");
	}
}
