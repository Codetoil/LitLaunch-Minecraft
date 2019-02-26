package io.github.littoil.tpsmod.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandHandler {
    static final Logger LOGGER = LogManager.getLogger();
    
	public static void execute()
	{
		LOGGER.info("test!");
	}
}
