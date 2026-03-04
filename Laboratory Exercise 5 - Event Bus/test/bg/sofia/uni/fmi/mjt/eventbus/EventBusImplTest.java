package bg.sofia.uni.fmi.mjt.eventbus;

import bg.sofia.uni.fmi.mjt.eventbus.events.DataTransfer;
import bg.sofia.uni.fmi.mjt.eventbus.events.MessageTransfer;
import bg.sofia.uni.fmi.mjt.eventbus.exception.MissingSubscriptionException;
import bg.sofia.uni.fmi.mjt.eventbus.subscribers.DeferredEventSubscriber;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class EventBusImplTest {

    EventBus bus = new EventBusImpl();

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
     void testPublishThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bus.publish(null),
            "Should throw because event is null.");
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
    void testGetSubscribersForEventThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> bus.getSubscribersForEvent(null),
            "Should throw because event type is null.");

    }
}
