/*
 * Copyright (c) Codetoil 2019
 */

package io.github.codetoil.litlaunch.core.event;

import io.github.codetoil.litlaunch.api.LitLaunch;
import io.github.codetoil.litlaunch.api.event.ILitEvent;
import io.github.codetoil.litlaunch.api.event.ILitEventHandler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LitEventHandler implements ILitEventHandler {
    public final static LitEventHandler COMMON = new LitEventHandler("Common");
    public final static LitEventHandler CLIENT = new LitEventHandler("Client");
    public final static LitEventHandler SERVER = new LitEventHandler("Server");

    public static void init() {
        try {
            LitLaunch.getConstructorMap().supply(ILitEventHandler.class, LitEventHandler.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public final String NAME;
    private final Queue<IEventListener> eventListenersToAdd = new ArrayDeque<>();
    private final Queue<IEventListener> eventListenersToRemove = new ArrayDeque<>();
    private List<IEventListener> _listeners = new ArrayList<>();

    public LitEventHandler(String name) {
        this.NAME = name;
        updateListenersFromQueue();
    }

    @Override
    public String toString() {
        return NAME + "{" + eventListenersToAdd + "," + eventListenersToRemove + "," + _listeners + "}";
    }

    private synchronized void updateListenersFromQueue() {
        while (!eventListenersToAdd.isEmpty()) {
            IEventListener listener = eventListenersToAdd.poll();
            LitLaunch.getLogger().info("Adding: " + listener.toString());
            _listeners.add(listener);
        }
        while (!eventListenersToRemove.isEmpty()) {
            IEventListener listener = eventListenersToRemove.poll();
            LitLaunch.getLogger().info("Removing: " + listener.toString());
            _listeners.remove(listener);
        }
    }

    public synchronized void addListener(IEventListener e) {
        eventListenersToAdd.offer(e);
        //_listeners.add(e);
    }

    public synchronized void removeListener(IEventListener e) {
        eventListenersToRemove.offer(e);
    }

    public synchronized void post(ILitEvent pEvent) {
        post(pEvent, false);
    }

    public synchronized void post(ILitEvent event, boolean isSpammy) {
        updateListenersFromQueue();
        logEVENT(event, isSpammy);
        for (IEventListener listener : this._listeners) {
            LitLaunch.getLogger().verbose("Sending event to: " + listener);
            listener.receivedEvent(event, this);
        }
    }

    private void logEVENT(ILitEvent event, boolean isSpammy) {
        if (!isSpammy) {
            LitLaunch.getLogger().trace("Sent Event: " + event.toString());
        } else {
            LitLaunch.getLogger().verbose("Sent Event (Verbose): " + event.toString());
        }
    }


}
