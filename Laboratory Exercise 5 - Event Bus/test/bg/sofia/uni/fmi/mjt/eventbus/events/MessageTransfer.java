package bg.sofia.uni.fmi.mjt.eventbus.events;

import bg.sofia.uni.fmi.mjt.eventbus.exception.PayloadException;

import java.time.Instant;

public class MessageTransfer implements Event<StringPayload>{

    private final String source;
    private final StringPayload data;
    private final int priority;
    private final Instant createdOn;

    /**
     * Constructor for data transfer events.
     * @throws IllegalArgumentException if source is null or empty, or if priority is not in bounds [1 - 10].
     * @throws PayloadException if the transfer payload is null.
     * */
    public MessageTransfer(String source, StringPayload data, int priority) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source is null.");
        } else if (data == null) {
            throw new PayloadException("Payload is null.");
        } else if (priority <= 0 || priority > 10) {
            throw new IllegalArgumentException("Priority is not in bounds[1-10].");
        }
        this.source = source;
        this.data = data;
        this.priority = priority;
        this.createdOn = Instant.now();
    }

    @Override
    public Instant getTimestamp() {
        return createdOn;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public StringPayload getPayload() {
        return data;
    }
}
