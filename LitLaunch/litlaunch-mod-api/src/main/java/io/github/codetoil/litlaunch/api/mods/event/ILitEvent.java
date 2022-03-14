/*
 * Copyright (c) 2022.
 */

package io.github.codetoil.litlaunch.api.mods.event;

import java.util.HashMap;
import java.util.Map;

public interface ILitEvent {
    TYPE getType();

    void setType(TYPE type);

    Map<String, Object> getDataMap();

    Object[] getData();

    void setData(Map<String, Object> data);

    class TYPE {
        private static Map<String, TYPE> types = new HashMap<>();
        public static final TYPE DISCONNECT = getEnumFromString("Disconnect");
        public static final TYPE CONSTRUCTION = getEnumFromString("Contruction");
        public static final TYPE PREINIT = getEnumFromString("PreInit");
        public static final TYPE INIT = getEnumFromString("Init");
        public static final TYPE POSTINIT = getEnumFromString("PostInit");
        public static final TYPE TESTSPAM = getEnumFromString("TestSpam");
        public static final TYPE SERVERLOAD = getEnumFromString("ServerLoad");
        public static final TYPE SERVERCONNECT = getEnumFromString("ServerConnect");
        public static final TYPE CLIENTTICK = getEnumFromString("clientTick");
        public static final TYPE SERVERTICK = getEnumFromString("serverTick");
        public static final TYPE ONPACKET = getEnumFromString("onPacket");
        private final String name;

        private TYPE(String name) {
            this.name = name;
        }

        public static TYPE getEnumFromString(String name) {
            if (types == null) {
                types = new HashMap<>();
            }
            TYPE type = types.get(name);
            if (type == null) {
                type = new TYPE(name);
                types.put(name, type);
            }
            return type;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "enum TYPE{name=" + name + "}";
        }
    }
}
