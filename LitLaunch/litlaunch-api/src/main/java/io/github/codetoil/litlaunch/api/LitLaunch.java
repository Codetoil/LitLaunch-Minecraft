package io.github.codetoil.litlaunch.api;

public class LitLaunch {
    private static LitLaunch instance = new LitLaunch();
    private IGetLitLaunchFields litLaunchFields;
    private IGetMinecraftFields minecraftFields;
    private IConstructorMap contructorMap;
    private IPreformMinecraftAction preformMinecraftAction;
    private ILogger logger;

    public static LitLaunch getInstance() {
        return instance;
    }

    public static IGetLitLaunchFields getLitLaunchFields() {
        return instance.litLaunchFields;
    }

    public void setLitLaunchFields(IGetLitLaunchFields litLaunchFields) throws IllegalAccessException {
        if (this.litLaunchFields == null) {
            this.litLaunchFields = litLaunchFields;
        } else {
            throw new IllegalAccessException("Can't change the litlaunch fields getter!");
        }
    }

    public static IGetMinecraftFields getMinecraftFields() {
        return instance.minecraftFields;
    }

    public void setMinecraftFields(IGetMinecraftFields minecraftFields) throws IllegalAccessException {
        if (this.minecraftFields == null) {
            this.minecraftFields = minecraftFields;
        } else {
            throw new IllegalAccessException("Can't change the minecraft fields getter!");
        }
    }

    public static IPreformMinecraftAction getPreformMinecraftAction() {
        return instance.preformMinecraftAction;
    }

    public void setPreformMinecraftAction(IPreformMinecraftAction preformMinecraftAction) throws IllegalAccessException {
        if (this.preformMinecraftAction == null) {
            this.preformMinecraftAction = preformMinecraftAction;
        } else {
            throw new IllegalAccessException("Can't change the minecraft action preform!");
        }
    }

    public static IConstructorMap getConstructorMap() {
        return instance.contructorMap;
    }

    public void setConstructorMap(IConstructorMap contructObjects) throws IllegalAccessException {
        if (this.contructorMap == null) {
            this.contructorMap = contructObjects;
        } else {
            throw new IllegalAccessException("Can't change the constructor map object!");
        }
    }

    public static ILogger getLogger() {
        return instance.logger;
    }

    public void setLogger(ILogger logger) throws IllegalAccessException {
        if (this.logger == null) {
            this.logger = logger;
        } else {
            throw new IllegalAccessException("Can't change the logger object!");
        }
    }

    public static ChainableMap<String, Object> emptyChainableMap() {
        return instance.litLaunchFields.emptyChainableMap();
    }
}
