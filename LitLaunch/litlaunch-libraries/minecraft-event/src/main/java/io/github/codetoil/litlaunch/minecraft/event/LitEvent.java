/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.minecraft.event;


import io.github.codetoil.litlaunch.api.event.ILitEvent;
import io.github.codetoil.litlaunch.api.event.LitEventType;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class LitEvent extends EventObject implements ILitEvent
{
    private LitEventType type;
    private Map<String, Object> data;

    //Used for invoking with strings
    public LitEvent(Object source) {
        super(source);
    }

    public LitEvent(Object source, LitEventType type, Map<String, Object> data) {
        super(source);
        this.type = type;
        this.data = data;
    }

    @Deprecated
    public LitEvent(Object source, LitEventType type, Object... data) {
        super(source);
        this.type = type;
        this.data = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            this.data.put("element " + i, data);
        }
    }

    public LitEventType getType() {
        return this.type;
    }

    public void setType(LitEventType type) {
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
