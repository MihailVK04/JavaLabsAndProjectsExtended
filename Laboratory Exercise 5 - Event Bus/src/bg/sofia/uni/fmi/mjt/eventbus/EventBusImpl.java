package bg.sofia.uni.fmi.mjt.eventbus;

import bg.sofia.uni.fmi.mjt.eventbus.events.Event;
import bg.sofia.uni.fmi.mjt.eventbus.exception.MissingSubscriptionException;
import bg.sofia.uni.fmi.mjt.eventbus.subscribers.Subscriber;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventBusImpl implements EventBus {

    private Map<Class<? extends Event<?>>, Set<Subscriber<?>>> eventTypeSubscriberMap;
    private Map<Class<? extends Event<?>>, Set<Event<?>>> eventTypeEventMap;

    {
        eventTypeSubscriberMap = new HashMap<>();
        eventTypeEventMap = new HashMap<>();
    }

    @Override
    public <T extends Event<?>> void subscribe(Class<T> eventType, Subscriber<? super T> subscriber) {

        if (eventType == null) {
            throw new IllegalArgumentException("Event type is null");
        } else if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber is null");
        }

        if (eventTypeSubscriberMap.get(eventType) == null) {
            eventTypeSubscriberMap.put(eventType, new HashSet<>());
        }

        if (eventTypeSubscriberMap.get(eventType).isEmpty() ||
            !eventTypeSubscriberMap.get(eventType).contains(subscriber)) {
            Set<Subscriber<?>> temporary = eventTypeSubscriberMap.get(eventType);
            eventTypeSubscriberMap.remove(eventType, temporary);

            if (temporary == null) {
                temporary = new HashSet<>();
            }

            temporary.add(subscriber);
            eventTypeSubscriberMap.put(eventType, temporary);
        }
    }

    @Override
    public <T extends Event<?>> void unsubscribe(Class<T> eventType, Subscriber<? super T> subscriber)
        throws MissingSubscriptionException {

        if (eventType == null) {
            throw new IllegalArgumentException("Event type is null");
        } else if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber is null");
        } else if (eventTypeSubscriberMap.get(eventType) == null) {
            throw new MissingSubscriptionException("No subscribers to this event at all");
        } else if (!eventTypeSubscriberMap.get(eventType).contains(subscriber)) {
            throw new MissingSubscriptionException("Subscriber is not subscribed to the event");
        }

        Set<Subscriber<?>> temporary = eventTypeSubscriberMap.get(eventType);
        eventTypeSubscriberMap.remove(eventType, temporary);
        temporary.remove(subscriber);
        eventTypeSubscriberMap.put(eventType, temporary);
    }

    @Override
    public <T extends Event<?>> void publish(T event) {

        if (event == null) {
            throw new IllegalArgumentException("Event is null");
        }

        Class<? extends Event<?>> eventType = (Class<? extends Event<?>>) event.getClass();
        //needed to get the reference
        Set<Event<?>> eventSet;
        if (eventTypeEventMap.get(eventType) == null) {
            eventSet = new HashSet<>();
        } else {
            eventSet = eventTypeEventMap.get(eventType);
            eventTypeEventMap.remove(eventType, eventSet);
        }
        eventSet.add(event);
        eventTypeEventMap.put(eventType, eventSet);

        if (eventTypeSubscriberMap.get(eventType) != null) {
            Set<Subscriber<?>> temporary = eventTypeSubscriberMap.get(eventType);

            for (Subscriber<?> s : temporary) {
                Subscriber<T> subscriber = (Subscriber<T>)s; // needed for calling onEvent method
                subscriber.onEvent(event);
            }
        }
    }

    @Override
    public void clear() {
        eventTypeEventMap = new HashMap<>();
        eventTypeSubscriberMap = new HashMap<>();
    }

    @Override
    public Collection<? extends Event<?>> getEventLogs(Class<? extends Event<?>> eventType, Instant from, Instant to) {

        if (eventType == null) {
            throw new IllegalArgumentException("Event type is null");
        } else if (from == null) {
            throw new IllegalArgumentException("Start timestamp is null");
        } else if (to == null) {
            throw new IllegalArgumentException("End timestamp is null");
        }

        if (from.isAfter(to) && from.equals(to)) {
            return Set.of();
        } else {
            Set<Event<?>> resultSet = new HashSet<>();
            if (eventTypeEventMap.containsKey(eventType) && eventTypeEventMap.get(eventType) != null) {
                for (Event<?> event : eventTypeEventMap.get(eventType)) {
                    if (event.getTimestamp().isAfter(from) && event.getTimestamp().isBefore(to)) {
                        resultSet.add(event);
                    } else if (event.getTimestamp().equals(from)) {
                        resultSet.add(event);
                    }
                }
            }
            return Set.copyOf(resultSet);
        }
    }

    @Override
    public <T extends Event<?>> Collection<Subscriber<?>> getSubscribersForEvent(Class<T> eventType) {

        if (eventType == null) {
            throw new IllegalArgumentException("Event type is null");
        }

        if (eventTypeSubscriberMap.containsKey(eventType)) {
            Set<Subscriber<?>> resultSet = new HashSet<>(eventTypeSubscriberMap.get(eventType));
            return Set.copyOf(resultSet);
        } else {
            return Set.of();
        }
    }
}
