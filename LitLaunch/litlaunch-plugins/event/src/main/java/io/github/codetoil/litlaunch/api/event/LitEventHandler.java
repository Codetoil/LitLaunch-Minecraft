/*
 * Copyright (c) Codetoil 2019-2022
 */

package io.github.codetoil.litlaunch.api.event;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LitEventHandler
{
    public final String NAME;
    private final Queue<ILitEventListener> eventListenersToAdd = new ArrayDeque<>();
    private final Queue<ILitEventListener> eventListenersToRemove = new ArrayDeque<>();
    private final List<ILitEventListener> _listeners = new ArrayList<>();

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
            ILitEventListener listener = eventListenersToAdd.poll();
            _listeners.add(listener);
        }
        while (!eventListenersToRemove.isEmpty()) {
            ILitEventListener listener = eventListenersToRemove.poll();
            _listeners.remove(listener);
        }
    }

    public synchronized void addListener(ILitEventListener e) {
        eventListenersToAdd.offer(e);
    }

    public synchronized void removeListener(ILitEventListener e) {
        eventListenersToRemove.offer(e);
    }

    public synchronized void post(ILitEvent pEvent) {
        post(pEvent, false);
    }

    public synchronized void post(ILitEvent event, boolean isSpammy) {
        updateListenersFromQueue();
        for (ILitEventListener listener : this._listeners) {
            listener.receivedEvent(event, this);
        }
    }

    public interface ILitEventListener
    {
        void receivedEvent(ILitEvent event, LitEventHandler handler);
    }
}
