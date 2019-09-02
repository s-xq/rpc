package com.sxq.rpc.transport.event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by s-xq on 2019-09-01.
 */

public class EventBus {

    private ConcurrentHashMap<Class<?>, CopyOnWriteArraySet<EventListener>> eventListenMap = new ConcurrentHashMap<>();

    /**
     * register
     *
     * @param eventType
     * @param eventListener
     */
    public void register(Class<?> eventType, EventListener eventListener) {
        CopyOnWriteArraySet<EventListener> set = eventListenMap.get(eventType);
        if (set == null) {
            set = new CopyOnWriteArraySet<EventListener>();
            CopyOnWriteArraySet<EventListener> old = eventListenMap.putIfAbsent(eventType, set);
            if (old != null) {
                set = old;
            }
        }
        set.add(eventListener);
    }

    /**
     * unregister
     *
     * @param eventType
     * @param eventListener
     */
    public void unregister(Class<?> eventType, EventListener eventListener) {
        CopyOnWriteArraySet<EventListener> set = eventListenMap.getOrDefault(eventType, null);
        if (set != null) {
            set.remove(eventListener);
        }

    }

}
