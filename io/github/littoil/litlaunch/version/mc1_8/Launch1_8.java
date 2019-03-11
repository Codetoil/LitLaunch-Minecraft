package io.github.littoil.litlaunch.version.mc1_8;

import io.github.littoil.litlaunch.launchcommon.Command;
import io.github.littoil.litlaunch.launchcommon.LaunchCommon;
import io.github.littoil.litlaunch.launchcommon.LaunchTPSMOD;
import io.github.littoil.litlaunch.launchforge.LaunchForge;
import io.github.littoil.litlaunch.version.mc1_8.proxy.ClientProxy1_8;
import io.github.littoil.litlaunch.version.mc1_8.proxy.ServerProxy1_8;
import io.github.littoil.tpsmod.TPSMod;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = LaunchForge.MODID, version = Launch1_8.VERSION)
public class Launch1_8{
	public static final String VERSION = "1.8-0.0.0.2";

	public Launch1_8()
	{
		LaunchTPSMOD.INSTANCE.LOGGER = new Logger1_8();
		if (!setProxy())
		{
			LaunchTPSMOD.INSTANCE.LOGGER.error("Proxies not set!");
		}
	}

	public boolean setProxy()
	{
		boolean result;
		if (LaunchForge.ccproxy != null)
		{
			LaunchTPSMOD.INSTANCE.LOGGER.error("Tried re-setting proxy!");
			result = false;
		}
		else
		{
			Side side = FMLCommonHandler.instance().getSide();
			switch (side) {
				case CLIENT:
					LaunchForge.ccproxy = new ClientProxy1_8();
					result = true;
					break;
				case SERVER:
					LaunchForge.ccproxy = new ServerProxy1_8();
					result = true;
					break;
				default:
					LaunchTPSMOD.INSTANCE.LOGGER.error("FML is not sided(client vs server). This may not go well!");
					result = false;
					break;
			}
		}
		return result;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LaunchForge.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		LaunchForge.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LaunchForge.postInit();
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		LaunchForge.serverLoad();
		TPSMod.commandList.forEach((command) -> {
			if (Command.Side.SERVER.equals(command.side) || Command.Side.BOTH.equals(command.side))
				event.registerServerCommand(new CommandNew(command));
		});
	}
}
