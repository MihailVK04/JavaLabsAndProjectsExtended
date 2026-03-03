package bg.sofia.uni.fmi.mjt.eventbus.subscribers;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import bg.sofia.uni.fmi.mjt.eventbus.events.Event;

public class DeferredEventSubscriber<T extends Event<?>> implements Subscriber<T>, Iterable<T> {

    private Collection<T> unprocessedEventCollection;

    {
        unprocessedEventCollection = new HashSet<>();
    }

    /**
     * Store an event for processing at a later time.
     *
     * @param event the event to be processed
     * @throws IllegalArgumentException if the event is null
     */
    @Override
    public void onEvent(T event) {

        if (event == null) {
            throw new IllegalArgumentException("Event is null");
        }

        if (unprocessedEventCollection.isEmpty() || !unprocessedEventCollection.contains(event)) {
            unprocessedEventCollection.add(event);
        }
//        throw new UnsupportedOperationException("Still not implemented");
    }

    /**
     * Get an iterator for the unprocessed events. The iterator should provide the events sorted
     * by priority, with higher-priority events first (lower priority number = higher priority).
     * For events with equal priority, earlier events (by timestamp) come first.
     *
     * @return an iterator for the unprocessed events
     */
    @Override
    public Iterator<T> iterator() {
        Comparator<T> eventCompare = new EventsByPriorityAndTimestampComparator<>();
        Set<T> sortedSet = new TreeSet<>(eventCompare);
        sortedSet.addAll(unprocessedEventCollection);
        return sortedSet.iterator();
//        throw new UnsupportedOperationException("Still not implemented");
    }

    /**
     * Check if there are unprocessed events.
     *
     * @return true if there are no unprocessed events, false otherwise
     */
    public boolean isEmpty() {
        return unprocessedEventCollection.isEmpty();
//        throw new UnsupportedOperationException("Still not implemented");
    }

}

class EventsByPriorityAndTimestampComparator<T extends Event<?>> implements Comparator<T> {

    static final int COMPARE_EQUALS_VALUE = 0;
    static final int COMPARE_GREATER_VALUE = 1;

    @Override
    public int compare(T event1, T event2) {
        int timestampCompare = event1.getTimestamp().compareTo(event2.getTimestamp());
        int priorityCompare = Integer.compare(event1.getPriority(), event2.getPriority());
        int sourceCompare = event1.getSource().compareTo(event2.getSource());

        if (event1.getPayload().equals(event2.getPayload()) && timestampCompare == COMPARE_EQUALS_VALUE &&
            priorityCompare == COMPARE_EQUALS_VALUE && sourceCompare == COMPARE_EQUALS_VALUE) {
            return COMPARE_EQUALS_VALUE;
        } else if (priorityCompare != COMPARE_EQUALS_VALUE) {
            return priorityCompare;
        } else if (timestampCompare != COMPARE_EQUALS_VALUE) {
            return timestampCompare;
        } else if (sourceCompare != COMPARE_EQUALS_VALUE) {
            return sourceCompare;
        } else if (Integer.compare(event1.getPayload().getSize(), event2.getPayload().getSize())
            != COMPARE_EQUALS_VALUE) {
            return Integer.compare(event1.getPayload().getSize(), event2.getPayload().getSize());
        }
        return COMPARE_GREATER_VALUE;
    }
}