/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.minecraft.event.logger;

import io.github.codetoil.litlaunch.api.event.EventHandler;
import io.github.codetoil.litlaunch.api.event.IEvent;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.time.Instant;

@SuppressWarnings("unused")
public class EventPrinter implements EventHandler.IEventListener
{
    private final BufferedOutputStream outputStream;

    public EventPrinter() throws IOException {
        if (!new File("events.log").createNewFile()) throw new IOException("Cannot create new events.log file");
        FileOutputStream fileOutputStream = new FileOutputStream("events.log");
        outputStream = new BufferedOutputStream(fileOutputStream);
    }

    @Override
    public void receivedEvent(IEvent event, EventHandler handler) {
        ByteBuffer buffer = ByteBuffer.allocate(1028);
        String lineToPut = Instant.now() + ": " + handler + ": " + event + "\n";
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
