package bg.sofia.uni.fmi.mjt.eventbus.subscribers;

import bg.sofia.uni.fmi.mjt.eventbus.events.DataTransfer;
import bg.sofia.uni.fmi.mjt.eventbus.events.IntegerArrayPayload;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DeferredEventSubscriberTest {

    static DeferredEventSubscriber<DataTransfer> dataSubscriber = new DeferredEventSubscriber<>();
    static DeferredEventSubscriber<DataTransfer> dummy = new DeferredEventSubscriber<>();
    static Set<DataTransfer> dataSet = new HashSet<>();

    @BeforeAll
    static void setSubscribers() {
        DataTransfer transfer1 = new DataTransfer("Module1", new IntegerArrayPayload(new int[] {1, 2, 3}),
            1);
        DataTransfer transfer2 = new DataTransfer("Module2", new IntegerArrayPayload(new int[] {4, 5, 6}),
            2);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            i++;
            i--;
        }
        DataTransfer transfer3 = new DataTransfer("Module3", new IntegerArrayPayload(new int[] {7, 8, 9}),
            2);
        dataSet.add(transfer1);
        dataSet.add(transfer2);
        dataSet.add(transfer3);
    }

    @Test
    void testOnEventThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> dummy.onEvent(null),
            "Should throw exception because event null");
    }

    @Test
    void testIsEmptyExpectedBehavior() {
        dataSubscriber.onEvent(new DataTransfer("Module4", new IntegerArrayPayload(new int[] {1,2}), 4));
        assertFalse(dataSubscriber.isEmpty(), "There should be one event in the subscriber");
    }

    @Test
    void testIteratorExpectedBehavior() {
        dataSubscriber.setForTest(dataSet);
        Iterator<DataTransfer> it = dataSubscriber.iterator();
        DataTransfer data = null;
        while (it.hasNext()) {
            if (data == null) {
                data = it.next();
                continue;
            } else {
                DataTransfer current = it.next();
                boolean case2 = data.getPriority() == current.getPriority();
                if (case2) {
                    assertTrue(data.getTimestamp().compareTo(current.getTimestamp()) > 0, "There is a problem with the timestamps.");
                } else {
                    assertTrue(data.getPriority() < current.getPriority(), "A problem with the priority occurred.");
                }
            }
        }
    }
}
