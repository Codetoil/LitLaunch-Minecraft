/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.minecraft.event;

import io.github.codetoil.litlaunch.api.event.ILitEvent;
import io.github.codetoil.litlaunch.api.event.LitEventHandler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;

public class EventPrinter implements LitEventHandler.ILitEventListener
{
    private final BufferedOutputStream outputStream;

    public EventPrinter() throws IOException {
        if (!new File("events.log").createNewFile()) throw new IOException("Cannot create new events.log file");
        FileOutputStream fileOutputStream = new FileOutputStream("events.log");
        outputStream = new BufferedOutputStream(fileOutputStream);
    }

    @Override
    public void receivedEvent(ILitEvent event, LitEventHandler handler) {
        ByteBuffer buffer = ByteBuffer.allocate(1028);
        String lineToPut = Instant.now() + ": " + handler + ": " + event + "\n";
        // LitLaunch.getLogger().info(lineToPut);
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
}
