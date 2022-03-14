/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.event;

import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.mods.event.ILitEvent;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class LitEvent extends EventObject implements ILitEvent {
    public static void init() {
        try {
            LitLaunch.getConstructorMap().supply(ILitEvent.class, LitEvent.class, Object.class, TYPE.class, Map.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private ILitEvent.TYPE type;
    private Map<String, Object> data;

    //Used for invoking with strings
    public LitEvent(Object source) {
        super(source);
    }

    public LitEvent(Object source, ILitEvent.TYPE type, Map<String, Object> data) {
        super(source);
        this.type = type;
        this.data = data;
    }

    @Deprecated
    public LitEvent(Object source, ILitEvent.TYPE type, Object... data) {
        super(source);
        this.type = type;
        this.data = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            this.data.put("element " + i, data);
        }
    }

    public ILitEvent.TYPE getType() {
        return this.type;
    }

    public void setType(ILitEvent.TYPE type) {
        if (this.type == null) {
            this.type = type;
        } else {
            LitLaunch.getLogger().warn("Cannot set type of LitEvent again");
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
        } else {
            LitLaunch.getLogger().warn("Cannot set data of LitEvent again");
        }
    }

    @Override
    public String toString() {
        return "LitEvent{type=\"" + type + ",data=\"" + data + ",source=\"" + source + "\"}";
    }


}
