package io.github.codetoil.litlaunch.core.event;

import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.event.ILitEvent;
import io.github.codetoil.litlaunch.api.event.ILitEventHandler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;

public class EventPrinter implements ILitEventHandler.IEventListener {
    private BufferedOutputStream outputStream;

    public EventPrinter() throws IOException {
        new File("events.log").createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream("events.log");
        outputStream = new BufferedOutputStream(fileOutputStream);
        LitEventHandler.COMMON.addListener(this);
        LitEventHandler.CLIENT.addListener(this);
        LitEventHandler.SERVER.addListener(this);
    }

    @Override
    public void receivedEvent(ILitEvent event, ILitEventHandler handler) {
        ByteBuffer buffer = ByteBuffer.allocate(1028);
        String lineToPut = Instant.now() + ": " + handler + ": " + event + "\n";
        LitLaunch.getLogger().info(lineToPut);
        char[] line = lineToPut.toCharArray();
        for (char c : line) {
            buffer.put((byte) c);
        }
        try {
            outputStream.write(buffer.array(), 0, buffer.position());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ILitEventHandler.IEventListener getListener() {
        return this;
    }
}
