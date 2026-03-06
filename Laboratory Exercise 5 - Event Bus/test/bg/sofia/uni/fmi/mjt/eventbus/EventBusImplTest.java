package bg.sofia.uni.fmi.mjt.eventbus;

import bg.sofia.uni.fmi.mjt.eventbus.events.DataTransfer;
import bg.sofia.uni.fmi.mjt.eventbus.events.Event;
import bg.sofia.uni.fmi.mjt.eventbus.events.IntegerArrayPayload;
import bg.sofia.uni.fmi.mjt.eventbus.events.MessageTransfer;
import bg.sofia.uni.fmi.mjt.eventbus.events.StringPayload;
import bg.sofia.uni.fmi.mjt.eventbus.exception.MissingSubscriptionException;
import bg.sofia.uni.fmi.mjt.eventbus.subscribers.DeferredEventSubscriber;
import bg.sofia.uni.fmi.mjt.eventbus.subscribers.Subscriber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EventBusImplTest {

    EventBus bus = new EventBusImpl();
    static DeferredEventSubscriber<DataTransfer> dataTransferSub;
    static DataTransfer dataTransferEvent;
    static Instant before;

    @BeforeAll
    static void setUpEnvironment() {
        dataTransferSub = new DeferredEventSubscriber<>();
        dataTransferEvent = new DataTransfer("Module1", new IntegerArrayPayload(new int[] {1, 2, 3}), 1);
        before = Instant.now();
    }

    @Test
     void testSubscribeThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            bus.subscribe(null, new DeferredEventSubscriber< DataTransfer >()),
            "Should throw because event is null");
        assertThrows(IllegalArgumentException.class, () ->
                bus.subscribe(DataTransfer.class, null),
            "Should throw because event is null");
    }

    @Test
    void testSubscribeCorrectOutcome() {
        bus.subscribe(DataTransfer.class, dataTransferSub);
        Collection<Subscriber<?>> users = bus.getSubscribersForEvent(DataTransfer.class);
        assertTrue(users.contains(dataTransferSub), "The set should contain the user.");
        bus.clear();
    }

    @Test
    void testUnsubscribeThrowsException() {
        DeferredEventSubscriber<DataTransfer> sub1 = new DeferredEventSubscriber<>();
        DeferredEventSubscriber<DataTransfer> sub2 = new DeferredEventSubscriber<>();
        DeferredEventSubscriber<MessageTransfer> sub3 = new DeferredEventSubscriber<>();
        bus.subscribe(DataTransfer.class, sub1);
        assertThrows(IllegalArgumentException.class, () ->
                bus.unsubscribe(null, sub1),
            "Should throw because event is null");
        assertThrows(IllegalArgumentException.class, () ->
                bus.unsubscribe(DataTransfer.class, null),
            "Should throw because event is null");
        assertThrows(MissingSubscriptionException.class, () ->
            bus.unsubscribe(MessageTransfer.class, sub3),
            "Should throw because there isn't even Message transfer.");
        assertThrows(MissingSubscriptionException.class, () ->
                bus.unsubscribe(DataTransfer.class, sub2),
            "Should throw because the subscriber hadn't subscribed at first.");
        bus.clear();
    }

    @Test
    void testUnsubscribeCorrectOutcome() {
        bus.subscribe(DataTransfer.class, dataTransferSub);
        bus.unsubscribe(DataTransfer.class, dataTransferSub);
        Collection<Subscriber<?>> users = bus.getSubscribersForEvent(DataTransfer.class);
        assertFalse(users.contains(dataTransferSub), "The set should not contain the user.");
        bus.clear();
    }

    @Test
     void testPublishThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bus.publish(null),
            "Should throw because event is null.");
    }

    @Test
    void testPublishCorrectOutcome() {
        bus.subscribe(DataTransfer.class, dataTransferSub);
        bus.publish(dataTransferEvent);
        Iterator<DataTransfer> it = dataTransferSub.iterator();
        assertTrue(it.hasNext(), "There should be an event for user.");
        assertEquals(dataTransferEvent, it.next(), "The events should match");
        dataTransferSub.setForTest(new HashSet<>());
        bus.clear();
    }

    @Test
    void testGetEventLogsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bus.getEventLogs(null, Instant.now(), Instant.now()),
            "Should throw because event type is null.");
        assertThrows(IllegalArgumentException.class, () -> bus.getEventLogs(DataTransfer.class, null, Instant.now()),
            "Should throw because first timestamp is null.");
        assertThrows(IllegalArgumentException.class, () -> bus.getEventLogs(DataTransfer.class, Instant.now(), null),
            "Should throw because second timestamp is null.");
    }

    @Test
    void testGetEventLogsCorrectOutcome() {
        bus.publish(dataTransferEvent);
        Instant after = Instant.now();
        MessageTransfer messageTransferEvent = new MessageTransfer("Module2",
            new StringPayload("Data, data ..."), 1);
        bus.publish(messageTransferEvent);
        Collection<? extends Event<?>> events = bus.getEventLogs(DataTransfer.class, before, after);
        assertTrue(events.contains(dataTransferEvent),
            "The event should be in the collection for the given time.");
        assertFalse(events.contains(messageTransferEvent),
            "The event should not be in the event set (no in the time frame).");
        bus.clear();
    }

    @Test
    void testGetSubscribersForEventThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bus.getSubscribersForEvent(null),
            "Should throw because event type is null.");

    }

    @Test
    void testGetSubscribersForEventCorrectOutcome() {
        bus.subscribe(DataTransfer.class, dataTransferSub);
        Collection<Subscriber<?>> subs = bus.getSubscribersForEvent(DataTransfer.class);
        assertTrue(subs.contains(dataTransferSub), "The user should be in the set.");
        bus.clear();
    }
}
