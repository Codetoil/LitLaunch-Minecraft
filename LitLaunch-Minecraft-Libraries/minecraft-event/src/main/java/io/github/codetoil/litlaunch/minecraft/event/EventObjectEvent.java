/*
 * Copyright (c) Codetoil 2019-2022
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>
 * Contact me on Discord: @Codetoil#7253, or by Email: ianthisawesomee@gmail.com
 */

package io.github.codetoil.litlaunch.minecraft.event;


import io.github.codetoil.litlaunch.api.event.EventType;
import io.github.codetoil.litlaunch.api.event.IEvent;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class EventObjectEvent extends EventObject implements IEvent
{
    private EventType type;
    private Map<String, Object> data;

    //Used for invoking with strings
    public EventObjectEvent(Object source) {
        super(source);
    }

    public EventObjectEvent(Object source, EventType type, Map<String, Object> data) {
        super(source);
        this.type = type;
        this.data = data;
    }

    @Deprecated
    public EventObjectEvent(Object source, EventType type, Object... data) {
        super(source);
        this.type = type;
        this.data = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            this.data.put("element " + i, data);
        }
    }

    public EventType getType() {
        return this.type;
    }

    public void setType(EventType type) {
        if (this.type == null) {
            this.type = type;
        }
    }

    public Map<String, Object> getDataMap() {
        return data;
    }

    @Deprecated
    public Object[] getData() {
        return data.values().toArray();
    }

    public void setData(Map<String, Object> data) {
        if (this.data == null) {
            this.data = data;
        }
    }

    @Override
    public String toString() {
        return "LitEvent{type=\"" + type + ",data=\"" + data + ",source=\"" + source + "\"}";
    }


}
