/*
 * Copyright (c) Codetoil 2022
 */

package io.github.codetoil.litlaunch.api.event;

import java.util.Map;

public interface ILitEvent {
    LitEventType getType();

    void setType(LitEventType type);

    Map<String, Object> getDataMap();

    Object[] getData();

    void setData(Map<String, Object> data);
}
