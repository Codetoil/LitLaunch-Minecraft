package io.github.codetoil.litlaunch.api.event;

public interface ILitEventHandler {
    void addListener(IEventListener e);

    void removeListener(IEventListener e);

    void post(ILitEvent pEvent);

    void post(ILitEvent event, boolean isSpammy);

    interface IEventListener {
        void receivedEvent(ILitEvent event, ILitEventHandler handler);

        IEventListener getListener();
    }
}
