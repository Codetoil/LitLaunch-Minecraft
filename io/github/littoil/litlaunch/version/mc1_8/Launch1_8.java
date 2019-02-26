package io.github.littoil.litlaunch.version.mc1_8;

import io.github.littoil.litlaunch.launchcommon.LaunchCommon;
import io.github.littoil.litlaunch.launchcommon.proxy.CommonClientProxy;
import io.github.littoil.litlaunch.launchcommon.proxy.CommonServerProxy;
import io.github.littoil.litlaunch.launchforge.LaunchForge;
import io.github.littoil.tpsmod.TPSMod;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Launch1_8.MODID, version = Launch1_8.VERSION)
public class Launch1_8 extends LaunchForge<FMLPreInitializationEvent, FMLInitializationEvent, FMLPostInitializationEvent, FMLServerStartingEvent> {
    public static final String VERSION = "1.8-0.0.0.0";
    public static Launch1_8 INSTANCE = new Launch1_8();
    
    public Launch1_8()
    {
    	LaunchCommon.VERSION = VERSION;
    	Side side = FMLCommonHandler.instance().getSide();
    	switch (side)
    	{
    	case CLIENT:
    		ccproxy = new CommonClientProxy();
    		break;
    	case SERVER:
    		ccproxy = new CommonServerProxy();
    		break;
    	default:
    		LOGGER.error("FML is not sided(client vs server). This may not go well!");
    		break;
    	}
    	super.LaunchInit();
    }
    
	@EventHandler
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		if (FMLCommonHandler.instance().getSide().isClient())
		{
			if (tpsmod instanceof TPSMod)
			{
				TPSMod.commandList.forEach((command) -> {
					if (io.github.littoil.litlaunch.launchcommon.Command.Side.CLIENT.equals(command.side) || io.github.littoil.litlaunch.launchcommon.Command.Side.BOTH.equals(command.side))
							net.minecraftforge.client.ClientCommandHandler.instance.registerCommand(new CommandNew(command));
				});
			}
		}
	}

	@EventHandler
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@EventHandler
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	@EventHandler
	@Override
	public void serverLoad(FMLServerStartingEvent event) {
		super.serverLoad(event);
		if (tpsmod instanceof TPSMod)
		{
			TPSMod.commandList.forEach((command) -> {
				if (io.github.littoil.litlaunch.launchcommon.Command.Side.SERVER.equals(command.side) || io.github.littoil.litlaunch.launchcommon.Command.Side.BOTH.equals(command.side))
						event.registerServerCommand(new CommandNew(command));
			});
		}
	}

}
