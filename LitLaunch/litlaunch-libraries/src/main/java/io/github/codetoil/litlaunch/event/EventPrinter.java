/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.event;

import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.mods.event.ILitEvent;
import io.github.codetoil.litlaunch.api.mods.event.ILitEventHandler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;

public class EventPrinter implements ILitEventHandler.IEventListener {
    private BufferedOutputStream outputStream;

    public EventPrinter() throws IOException {
        if (!new File("events.log").createNewFile()) throw new IOException("Cannot create new events.log file");
        FileOutputStream fileOutputStream = new FileOutputStream("events.log");
        outputStream = new BufferedOutputStream(fileOutputStream);
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
