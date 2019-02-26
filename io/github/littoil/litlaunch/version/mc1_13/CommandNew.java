package io.github.littoil.litlaunch.version.mc1_13;

import java.lang.reflect.InvocationTargetException;

import com.mojang.brigadier.CommandDispatcher;

import io.github.littoil.litlaunch.launchcommon.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class CommandNew {

	Command comm;
	CommandDispatcher<CommandSource> disp;
	
	public CommandNew() {}
	
	public CommandNew(Command comm, CommandDispatcher<CommandSource> disp) {
		this.comm = comm;
		this.disp = disp;
		this.register(this.disp);
	}

	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

	//public String getUsage(ICommandSender sender) {
	//	return comm.usage;
	//}

	public void register(CommandDispatcher<CommandSource> disp) {
	      disp.register(Commands.literal(comm.name).executes((p_198726_0_) -> {
	         execute();
	         return 0;
	      }));
	   }
	
	public void execute() {
		try {
			comm.method.invoke(null);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
